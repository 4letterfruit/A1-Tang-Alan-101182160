const apiBaseUrl = "http://localhost:8080";


let eventPlayer = 1;
let mainText;
let handDisplay;
let promptSpace;
let questSponsor = 0;

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
    nextButton.setAttribute("id", "next");
    nextButton.innerHTML = "Continue";

    let array = await getHandArray(player);
    let cards = array.length;

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
            button.setAttribute("id", "next");
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
    let arr = await getHandArray(eventPlayer);
    let cards = arr.length;
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
        button.classList.add(card);
        handDisplay.appendChild(button);
    });

    // generate submit button to do next action
    let button = makeActionButton();
    button.setAttribute("onclick", `doTrim(${player}, "${nextAction}")`);
    button.classList.add("next");
    button.setAttribute("id", "next");

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
    try{
        let r = await fetch(`${apiBaseUrl}/trim?p=${player}&s=${trimString}`, {
            method: 'POST'
        });
        // update scoreboard
        await updateScoreboard();
        deleteButtons();
        mainText.innerHTML = " ";

        eval(nextAction);
    } catch (e) {
        console.error(e);
    }

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
        eventPlayer = nextPlayer (eventPlayer);
        noButton.setAttribute("onclick", `promptPlayer(${eventPlayer}, 'drawEvent(${eventPlayer})')`);
    }
    yesButton.setAttribute("id", "yes");
    noButton.setAttribute("id", "no");
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
    eligible.setAttribute("id", "eligible-list");
    let q = ""
    while (np != player) {
        q += np;
        eligible.innerHTML += np + " "
        eligible.classList.add(np);
        np = nextPlayer(np);
    }
    eligible.setAttribute("participants", q);

    stageDisplay.appendChild(eligible);
    questSponsor = player;
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

        return promptPlayer(np, `promptAttack(${np}, 1, ${totalStages})`);
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
        button.classList.add(card);


        handDisplay.appendChild(button);
    });

    // create a submit button, onclick submitStage(player, prevValue, currentStage)
    let button = makeActionButton();
    button.innerHTML = "submit";
    button.setAttribute("id", "next");

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

    await updateScoreboard();

    // add cards to a stage display
    let stageBlock = document.createElement("p");
    stageBlock.innerHTML = cardString;
    stageBlock.classList.add("stage");
    stageBlock.classList.add("temp");
    stageBlock.setAttribute("size", query.length);
    stageDisplay.appendChild(stageBlock);

    // create next stage
    startCreateStage(player, sum, currentStage+1, totalStages);
}

async function promptAttack(player, stageIndex, totalStages) {
    // remove relevant buttons
    deleteButtons();

    // if stageIndex > stage count, resolveQuest()
    if (stageIndex > totalStages) {
        return resolveQuest(totalStages);
    }

    // prompt player
    mainText.innerHTML = "Would you like to attack stage " + stageIndex + "?";

    // request and display player hand
    let handArray = await getHandArray(player);
    handArray.forEach((card) => {
        let button = makeActionButton();
        button.innerHTML = card;
        button.classList.add(card.charAt(0));
        button.classList.add(card);

        handDisplay.appendChild(button);
    });
    disableButtonClass(".F");

    // create a button, onclick=startAttack(player, stageIndex, totalStages)
    yesButton = makeActionButton();
    yesButton.innerHTML = "yes";
    yesButton.setAttribute("onclick", `startAttack(${player}, ${stageIndex}, ${totalStages})`);
    yesButton.setAttribute("id", "yes");
    promptSpace.appendChild(yesButton);

    // create a button, onclick=refuse(player, stageIndex, totalStages)
    noButton = makeActionButton();
    noButton.innerHTML = "no";
    noButton.setAttribute("onclick", `refuse(${player}, ${stageIndex}, ${totalStages})`);
    noButton.setAttribute("id", "no");
    promptSpace.appendChild(noButton);
}

async function refuse(player, stageIndex, totalStages) {
    // remove relevant buttons
    deleteButtons();
    // remove player from the eligible attackers in the DOM
    let eligible = document.getElementById("eligible-list");
    eligible.classList.remove(player);
    eligible.innerHTML = "Eligible Attackers: ";
    eligible.classList.forEach((p) => {
        if (p != "temp") {
            eligible.innerHTML += p + " ";
        }
    })

    // if this is the last player in the participants, then stageIndex++
    let participants = eligible.getAttribute("participants");
    let np;
    if (participants.charAt(participants.length-1) == player) {
        // if this is the only player in the participants, resolve quest
        if (participants.length == 1) {
            eligible.setAttribute("participants", "");
            return resolveQuest(totalStages);
        } else {
            eligible.setAttribute("participants", participants.substring(0, participants.length-1));
        }

        // go to head of the participants
        np = participants.charAt(0);
        stageIndex++;
    } else {
        // there are more players
        // get the index of the player, delete it, so next player is at current index
        np = participants.indexOf(player);

        participants = participants.substring(0, np) + participants.substring(np+1);
        eligible.setAttribute("participants", participants);
        np = participants.charAt(np);
    }

    console.log(np);
    if (stageIndex > totalStages) {
        resolveQuest(totalStages);
    } else {
        promptPlayer(np, `promptAttack(${np}, ${stageIndex}, ${totalStages})`);
    }
}

async function startAttack(player, stageIndex, totalStages) {
    // delete relevant buttons
    deleteButtons();

    // draw a card for the player
    let response = await fetch(`${apiBaseUrl}/draw?p=${player}`, {
            method: 'POST'
        });
    let handSize = await response.text();
    await updateScoreboard();

    // trim if needed nextAction -> doAttack
    if (handSize > 12){
        trim(player, `doAttack(${player}, ${stageIndex}, ${totalStages})`);
    } else {
        doAttack(player, stageIndex, totalStages);
    }
}

async function doAttack(player, stageIndex, totalStages) {
    // delete relevant buttons
    deleteButtons();

    mainText.innerHTML = "Select your weapons"

    // retrieve cards for player and display as buttons
    handArray = await getHandArray(player);
    handArray.forEach((card) => {
        let button = makeActionButton();
        button.innerHTML = card;
        button.setAttribute("onclick", "selectButtonClass(this)");
        button.classList.add(card.charAt(0));
        button.classList.add(card);
        handDisplay.appendChild(button);
        button.setAttribute("item-type", card.charAt(0));
    });
    disableButtonClass(".F");

    // create a submit button that triggers submitAttack(player, stageIndex, totalStages)
    let button = makeActionButton();
    button.setAttribute("onclick", `submitAttack(${player}, ${stageIndex}, ${totalStages})`);
    button.setAttribute("id", "next");

    promptSpace.appendChild(button);
}

async function submitAttack(player, stageIndex, totalStages) {
    // generate card string for attack
    let query = document.querySelectorAll(".selected");
    cardString = ""
    query.forEach((element) => {
        cardString += element.innerHTML + " ";
    });
    cardString = cardString.substring(0, cardString.length-1);

    // no repeated weapons confirmed by button inputs
    // post submitAttack(player, cardString, stageIndex) to backend
    let response = await fetch(`${apiBaseUrl}/submitAttack?p=${player}&c=${cardString}&i=${stageIndex}`, {
        method: 'POST'
    });
    await updateScoreboard();
    let result = await response.text();

    // response is a win or loss
    deleteButtons();
    button = makeActionButton();
    if (result == "success") {
        mainText.innerHTML = "Attack Successful!";
        // if this was last of eligible player, stageIndex++
        let participants = document.getElementById("eligible-list").getAttribute("participants");
        let np;
        if (participants.charAt(participants.length-1) == player) {
            // if this is the only player in the participants, resolve quest
            if (participants.length == 1) {
                return resolveQuest(totalStages);
            }

            // go to head of the participants
            np = participants.charAt(0);
            stageIndex++;

        } else {
            // there are more players behind in the list, get next
            np = participants.charAt(participants.indexOf(player)+1);
        }
        if (stageIndex > totalStages) {
            button.setAttribute("onclick", `resolveQuest(${totalStages})`);
        } else {
            button.setAttribute("onclick", `promptPlayer(${np}, 'promptAttack(${np}, ${stageIndex}, ${totalStages})')`);
        }

    } else {
        mainText.innerHTML = "Attack Failed";

        // refuse has the end result of removing player from the eligible attackers
        button.setAttribute("onclick", `refuse(${player}, ${stageIndex}, ${totalStages})`);
    }


    button.setAttribute("id", "next");
    promptSpace.appendChild(button);
}

async function resolveQuest(size) {
    deleteButtons();
    // lookup the remaining eligible players
    // award eligible players with shields
    let participants = document.getElementById("eligible-list").getAttribute("participants");
    let pString = "";
    for (let i = 0; i < participants.length; i++) {
        pString += participants.charAt(i) + " ";
    }
    pString = pString.substring(0, pString.length-1);
    if (pString.length == 0) {
        pString = "none";
    }

    mainText.innerHTML = "Quest ended, awarding Player " + pString + ": " + size + " shields";

    try{
        // award shields
        await fetch(`${apiBaseUrl}/awardShields?plist=${pString}&s=${size}`, {
            method: 'POST'
        });
        await updateScoreboard();

        // request replenishCards(player) from the backend (it should access the overview and do the rest)
        let response = await fetch (`${apiBaseUrl}/replenish?p=${questSponsor}`, {
            method: 'POST'
        });
        await updateScoreboard();
        let handSize = await response.text();

        let button = makeActionButton();
        if (handSize > 12) {
            // promptplayer trims
            button.setAttribute("onclick", `promptPlayer( ${questSponsor}, 'checkWinners()' )`);
        } else {
            button.setAttribute("onclick", 'checkWinners()');
        }
        button.setAttribute("id", "next");
        promptSpace.appendChild(button);
    } catch (e) {
        console.error(e);
    }
}

async function checkWinners() {
    deleteButtons();
    deleteTemp();

    try{
        let response = await fetch(`${apiBaseUrl}/winners`);
        let winners = await response.text();

        if (winners != '0') {
            mainText.innerHTML = "Congratulations to our Winners: " + winners;
        } else {
            // no winners, prompt next player to draw event
            eventPlayer = nextPlayer(eventPlayer);
            promptPlayer(eventPlayer, `drawEvent(${eventPlayer})`)
        }
    } catch (e) {
        console.error(e);
    }
}










// HELPERS
// used when selecting foe/weapon for stages
async function rig(i) {
    switch (i) {
        case 1:
            await fetch(`${apiBaseUrl}/rig1`, {method: 'POST'});
            return;
        case 2:
            await fetch(`${apiBaseUrl}/rig2`, {method: 'POST'});
            return;
        case 3:
            await fetch(`${apiBaseUrl}/rig3`, {method: 'POST'});
            return;
        case 4:
            await fetch(`${apiBaseUrl}/rig4`, {method: 'POST'});
            return;
    }
}

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

async function showHands() {
    let p1Hand = await getHandArray(1);
    let p2Hand = await getHandArray(2);
    let p3Hand = await getHandArray(3);
    let p4Hand = await getHandArray(4);

    let p1 = document.createElement("p");
    p1.setAttribute("id", "p1-hand");
    p1.innerHTML = "P1: " + p1Hand;

    let p2 = document.createElement("p");
    p2.setAttribute("id", "p2-hand");
    p2.innerHTML = "P2: " + p2Hand;

    let p3 = document.createElement("p");
    p3.setAttribute("id", "p3-hand");
    p3.innerHTML = "P3: " + p3Hand;

    let p4 = document.createElement("p");
    p4.setAttribute("id", "p4-hand");
    p4.innerHTML = "P4: " + p4Hand;




    handDisplay.appendChild(p1);
    handDisplay.appendChild(p2);
    handDisplay.appendChild(p3);
    handDisplay.appendChild(p4);

}

async function restart() {
    location.reload()
}