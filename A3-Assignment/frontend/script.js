const apiBaseUrl = "http://localhost:8080";


let eventPlayer = 1;
let mainText;
let handDisplay;
let promptSpace;

function nextPlayer(p) {
    return (p%4) + 1;
}

async function start() {
    // remove some of the current elements
    deleteButtons();
    document.getElementById("welcome").remove();

    // generate new space for game
    let stages = document.createElement("div");
    stages.setAttribute("id", "stageDisplay");
    handDisplay = document.createElement("div");
    handDisplay.setAttribute("id", "handDisplay");
    promptSpace = document.createElement("div");
    promptSpace.setAttribute("id", "promptSpace");

    mainText = document.createElement("h2");
    mainText.setAttribute("id", "mainText");

    let output = document.getElementById("output");
    output.appendChild(mainText);
    output.appendChild(stages);
    output.appendChild(handDisplay);
    output.appendChild(promptSpace);

    // start game loop
    await updateScoreboard();
    //loop
    // prompt player -> player hits a button
    promptPlayer(eventPlayer, `drawEvent(${eventPlayer})`)
    // player draws a card
    // do something based on information from the card
    // check if there are winners
}

async function promptPlayer(player, nextAction) {
    // delete button that triggered this
    deleteButtons();
    // create prompt for player
    document.getElementById("mainText").innerHTML = "Player " + player

    let nextButton = document.createElement("button");
    nextButton.classList.add("action-button");
    nextButton.classList.add("next");
    nextButton.innerHTML = "Continue";

    let cards = countCards(player);

    if (cards > 12) {
        nextButton.setAttribute("onclick", `trim(${player}, '${nextAction}')`);
    } else {
        nextButton.setAttribute("onclick", `${nextAction}`);
    }


    document.getElementById("promptSpace").appendChild(nextButton);
    // prompt button has onclick -> check trim -> nextAction

}

async function drawEvent(player) {
    // delete the button that triggered this
    deleteButtons();
    // display info
    const response = await fetch(`${apiBaseUrl}/drawEvent?p=${player}`);
    const text = await response.text();
    let promptSpace = document.getElementById("promptSpace");
    document.getElementById("mainText").innerHTML = "Event Drawn: " + text

    // create a button that triggers the next course of action

    // plague, queen's favor, prosperity, quest
    switch (text) {
        case "E-Plague":
        case "E-Queen's Favor":
        case "E-Prosperity":
            doEvent();
            break;
    // quest should run promptQuest(currentPlayer, size)
        default:
            let size = text.substring(1);
            let button = makeActionButton();
            button.setAttribute("onclick", `promptQuest(${player}, ${size})`);
            button.classList.add("next");
            promptSpace.appendChild(button);

    }

}

function makeActionButton() {
    let button = document.createElement("button");
    button.classList.add("action-button");
    button.innerHTML = "Next";
    return button;
}

async function doEvent() {
    // delete the button that triggered this
    deleteButtons();

    // backend did the work on draw
    // update scoreboard
    await updateScoreboard();

    // button to prompt next player
    let button = makeActionButton();
    let cards = countCards(eventPlayer);
    let temp = eventPlayer;
    eventPlayer = nextPlayer(parseInt(eventPlayer));

    if (cards > 12) {
        button.setAttribute("onclick", `trim(${temp}, 'promptPlayer(${eventPlayer}, \\'drawEvent(${eventPlayer})\\')')`);
    } else {
        eventPlayer = nextPlayer(parseInt(eventPlayer));
        button.setAttribute("onclick", `promptPlayer(${eventPlayer}, 'drawEvent(${eventPlayer})')`);
    }
    button.classList.add("next");
    document.getElementById("promptSpace").appendChild(button);


}

function countCards(player) {
    let classString = ".player-score.player-"+player;
    let playerDiv = document.querySelectorAll(classString)[0]; // only one such element
    let cards = parseInt(playerDiv.querySelectorAll(".card-count")[0].innerHTML);
    return cards;
}

async function trim(player, nextAction) {
    deleteButtons();
    let handArray = await getHandArray(player);

    // get number of cards to discard

    mainText.innerHTML = "Discard " + (handArray.length - 12);

    // display the hand with buttons
    handArray.forEach((card) => {
        let button = makeActionButton();
        button.innerHTML = card;
        button.setAttribute("onclick", "selectButtonClass(this)");
        handDisplay.appendChild(button);
    });

    // generate submit button to do next action
    let button = makeActionButton();
    button.setAttribute("onclick", `doTrim(${player}, "${nextAction}")`);
    button.classList.add("next");

    promptSpace.appendChild(button);
}

async function doTrim(player, nextAction) {
    // do check on card count
    let selected = document.querySelectorAll(".selected");
    let textArray = mainText.innerHTML.split(" ");
    let req = parseInt(textArray[textArray.length-1]);

    if (selected.length != req) {
        mainText.innerHTML = "Error: discard " + req;
        return;
    }
    // submit trim to backend
    let trimString = "";
    selected.forEach((element) => {
        trimString = trimString + element.innerHTML + " ";
    });
    trimString = trimString.substring(0, trimString.length-1);

    let r = await fetch(`${apiBaseUrl}/trim?p=${player}&s=${trimString}`, {
        method: 'POST'
    });
    // update scoreboard
    await updateScoreboard();
    deleteButtons();
    mainText.innerHTML = " ";

    eval(nextAction);
}

async function promptQuest(player, size) {
    // delete the button that triggered this
    deleteButtons();
    // display hand
    let handArray = await getHandArray(player);

    handArray.forEach((card) => {
        let button = makeActionButton();
        button.innerHTML = card;
        handDisplay.appendChild(button);
    });

    // two buttons -> sponsor yes/no
    mainText.innerHTML = "Would you like to sponsor Q" + size;

    let yesButton = makeActionButton();
    let noButton = makeActionButton();

    yesButton.innerHTML = "yes";
    noButton.innerHTML = "no";

    // yes has onclick = sponsor(player, quest)
    yesButton.setAttribute("onclick", `sponsor(${player}, ${size})`);

    // if not last player, no has onclick = promptPlayer(nextPlayer, promptQuest(nextPlayer, size)
    let np = nextPlayer(player);
    noButton.setAttribute("onclick", `promptPlayer(${np}, 'promptQuest(${np}, ${size})')`);

    // if last player, no has onclick = promptPlayer(nextPlayer, drawEvent(nextPlayer))
    if (np == eventPlayer) {
        let next = nextPlayer (eventPlayer);
        noButton.setAttribute("onclick", `promptPlayer(${next}, 'drawEvent(${next})')`);
    }

    promptSpace.appendChild(yesButton);
    promptSpace.appendChild(noButton);
}

async function sponsor(player, size) {
    // delete relevant buttons
    deleteButtons();

    // create a div for quest size + each stage
    let desc = document.createElement("p");
    desc.innerHTML = "Quest Q" + size;
    desc.classList.add("temp");
    desc.setAttribute("size", size);

    stageDisplay.appendChild(desc);

    // eligible attackers are all except sponsor, starting at player 1/2
    let eligible = document.createElement("p");
    let np = nextPlayer(player);
    eligible.innerHTML = "Eligible Attackers: ";
    eligible.classList.add("temp");
    while (np != player) {
        eligible.innerHTML += np + " "
        eligible.classList.add(np);
        np = nextPlayer(np);
    }
    stageDisplay.appendChild(eligible);
     startCreateStage (player, 0, 1, size)
}

async function startCreateStage(player, prevValue, currentStage, totalStages) {
    // delete relevant buttons
    deleteButtons();

    mainText.innerHTML = "Select cards for stage " + currentStage;


    // if currentStage > totalStages -> remove the card's values in the quest div and promptPlayer(first eligible, promptAttack(first eligible, 1))
    if (currentStage > totalStages) {
        let np = nextPlayer(player);
        let stageList = document.querySelectorAll(".stage");
        stageList.forEach((stage) => {
            stage.innerHTML = "Stage size: " + stage.getAttribute("size");
        });

        return promptPlayer(np, `promptAttack(${np}, 1)`);
    }

    // else
    // request player hand
    let hand = await getHandArray(player);
    // populate the screen with the player's hand as buttons
    hand.forEach((card) => {
        // buttons have property onclick -> add/remove a tag for them
        // additional property: foe/weapon tag, no duplicate tags

        let button = makeActionButton();
        button.innerHTML = card;
        button.setAttribute("onclick", "selectButtonClass(this)");
        button.setAttribute("item-type", card.charAt(0));
        button.classList.add(card.charAt(0));

        handDisplay.appendChild(button);
    });

    // create a submit button, onclick submitStage(player, prevValue, currentStage)
    let button = makeActionButton();
    button.innerHTML = "submit";
    button.setAttribute("onclick", `submitStage(${player}, ${prevValue}, ${currentStage}, ${totalStages})`);
    promptSpace.appendChild(button);
}

async function submitStage(player, prevValue, currentStage, totalStages) {
    // submit button was pressed, generate cardstring and do basic checks
    let query = document.querySelectorAll(".selected");
    let cardString = "";
    let sum = 0;
    let hasFoe = false;

    query.forEach((element) => {
        cardString += element.innerHTML + " ";
        sum += parseInt(element.innerHTML.substring(1));
        if (element.getAttribute("item-type") == "F") {
            hasFoe = true;
        }
    });
    cardString = cardString.substring(0, cardString.length - 1);

    // does this submission have one foe/no duplicate weapons, done with button config
    // valid stage checks
    if (query.length == 0)
    {
        mainText.innerHTML = "Error: Stage cannot be empty";
        return;
    }

    if (hasFoe == false) {
        mainText.innerHTML = "Stage requires a foe";
        return;
    }

    console.log("sum: " + sum);
    if (sum <= prevValue) {
        mainText.innerHTML = "Insufficient value";
        return;
    }

    // sends to backend /createStage with cards and player
    let response = await fetch(`${apiBaseUrl}/setStage?cards=${cardString}&p=${player}`, {
        method: 'POST'
    });

    // add cards to a stage display
    let stageBlock = document.createElement("p");
    stageBlock.innerHTML = cardString;
    stageBlock.classList.add("stage");
    stageBlock.setAttribute("size", query.length);
    stageDisplay.appendChild(stageBlock);

    // create next stage
    startCreateStage(player, sum, currentStage+1, totalStages);
}

async function promptAttack(player, stageIndex) {
    // remove relevant buttons
    // if stageIndex >= stage count (from DOM) then resolveQuest()

    // request and display player hand
    // create a button, onclick=doAttack(player, stageIndex)
    // create a button, onclick=refuse(player, stageIndex)
    // the quest board will show how many cards are on the stage
}

async function refuse(player, stageIndex) {
    // remove relevant buttons
    // remove player from the eligible attackers in the DOM
    //
    // if next player loops to front of queue, then stageIndex++
    // promptPlayer(nextPlayer, promptAttack(nextPlayer, stageIndex))
}

async function startAttack(player, stageIndex) {
    // delete relevant buttons
    // draw a card for the player
    // trim if needed nextAction -> doAttack

}

async function doAttack(player, stageIndex) {
    // delete relevant buttons
    // retrieve cards for player and display as buttons
    // buttons have a tag with type (foe/weapon), foe buttons should be disabled, weapons should disable other weapons on click
    // create a submit button that triggers submitAttack(player)
}

async function submitAttack(player, stageIndex) {
    // generate card string for attack
    // do check for no repeated weapons
    // post submitAttack(player, cardString, stageIndex) to backend

    // response is a win or loss
    // on loss remove from eligible attackers in DOM

    // if this was last of eligible player, stageIndex++
    // promptPlayer(nextPlayer, promptAttack(nextPlayer, stageIndex)
}

async function resolveQuest() {
    // lookup the remaining eligible players from the DOM
    // lookup the sponsor from the DOM
    // lookup the quest size from the DOM
    // award eligible players with shields
    // request replenishCards(player) from the backend (it should access the overview and do the rest)
    // check winners

    // nextPlayer = person that drew event +1
    // no winners, check trim -> next action promptPlayer(nextPlayer, drawEvent)
}


// used when selecting foe/weapon for stages
function selectButtonClass(item) {
    let buttonClass = "." + item.getAttribute("item-type");
    if (item.classList.contains("disabled")) {
        return;
    }

    // toggle + enable/disable button class
    if (item.classList.contains("selected")) {
        enableButtonClass(buttonClass);
        item.classList.remove("selected");
    } else {
        disableButtonClass(buttonClass);
        item.classList.remove("disabled");
        item.classList.add("selected");
    }
}

function disableButtonClass(buttonClass) {
    let list = document.querySelectorAll(buttonClass);
    console.log(buttonClass);
    list.forEach((element) => element.classList.add("disabled"));
}

function enableButtonClass(buttonClass) {
    let list = document.querySelectorAll(buttonClass);
    list.forEach((element) => element.classList.remove("disabled"));
}

function deleteButtons() {
    buttons = document.querySelectorAll(".action-button");
    buttons.forEach((element) => element.remove());
}

function deleteTemp() {
    buttons = document.querySelectorAll(".temp");
    buttons.forEach((element) => element.remove());
}

async function getHandArray(player) {
    try{
        const response = await fetch(`${apiBaseUrl}/getHand?p=${player}`);
        let array = await response.text();
        array = array.split(", ")
        return array;
    } catch (error) {
        console.error("error in getHandArray:", error);
    }
}

async function updateScoreboard() {
    try {
        const response = await fetch(`${apiBaseUrl}/scoreboard`);
            let scoreboard = await response.text();
            scoreboard = scoreboard.split(" ");
            for (let i = 1; i <= 4; i++){
                setPlayerScore(i, scoreboard[2*(i-1)], scoreboard[2*(i-1)+1]);
        }
    } catch (error) {
        console.error("error in updateScoreboard")
    }

}

function setPlayerScore(p, cards, shields) {
    let classString = ".player-score.player-"+p;
    let player = document.querySelectorAll(classString)[0]; // only one such element

    player.querySelector(".card-count").innerHTML = cards;
    player.querySelector(".shield-count").innerHTML = shields;
}
