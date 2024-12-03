const apiBaseUrl = "http://localhost:8080";

async function start() {
    // remove some of the current elements

    // generate new space for game

    // start game loop

    //loop
    // prompt player -> player hits a button

    // player draws a card

    // do something based on information from the card

    // check if there are winners
}

async function promptPlayer(player, nextAction) {
    // delete button that triggered this
    // create prompt for player
    // prompt button has onclick -> check trim -> nextAction

}

async function drawEvent(player) {
    // delete the button that triggered this
    // display info
    // create a button that triggers the next course of action

    // plague, queen's favor, prosperity, quest

    // quest should run promptQuest(currentPlayer, size)
}

async function plague() {
    // delete the button that triggered this
    // tell backend to trigger plague
    // update scoreboard
    // button to prompt player
}

async function favor() {
    // delete the button that triggered this
    // tell backend to trigger favor
    // update scoreboard
    // trim current player if necessary -> next action is promptPlayer(nextPlayer, drawEvent)
}

async function prosperity() {
    // delete the button that triggered this
    // tell backend to trigger prosperity
    // update scoreboard
    // trim current player if necessary -> next action is promptPlayer(nextPlayer, drawEvent)
}

async function trim(player, nextAction) {
    // if handsize too big, request the player's hand from backend
    // display the hand with buttons
    // generate submit button

}

async function doTrim(player, nextAction) {
    // generate card list
    // do check on card count

    // submit trim to backend
    // update scoreboard

    // create button with `onClick="${nextAction}"`, innerHTML = next
}

async function promptQuest(player, size) {
    // delete the button that triggered this
    // display hand
    // two buttons -> sponsor yes/no

    // yes has onclick = sponsor(player, quest)
    // if not last player, no has onclick = promptPlayer(nextPlayer, promptQuest(nextPlayer, size)
    // if last player, no has onclick = promptPlayer(nextPlayer, drawEvent(nextPlayer))
}

async function sponsor(player, quest) {
    // delete relevant buttons
    // create a div for quest size + each stage
    // eligible attackers are all except sponsor, starting at player 1/2

    // startCreateStage (player, prevValue, currentStage)

}

async function startCreateStage(player, prevValue, currentStage) {
    // delete relevant buttons
    // if currentStage > totalStages -> remove the card's values in the quest div and promptPlayer(first eligible, promptAttack(first eligible, 1))

    // else
    // request player hand
    // populate the screen with the player's hand as buttons
    // buttons have property onclick -> add/remove a tag for them
    // additional property: foe/weapon tag, no duplicate tags
    // create a submit button, onclick submitStage(player, prevValue, currentStage)
}

async function submitStage(player, prevValue, currentStage) {
    // submit button was pressed, generate cardstring and do basic checks
    // is this stage empty
    // does this submission have one foe/no duplicate weapons
    // sends to backend /createStage with player, cards, prevval, hasfoe

    // based on response
    // startCreateStage(player, prevValue, currentStage)
    // or
    // add cards to a stage display
    // startCreateStage(player, prevValue, currentStage+1)
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
    let class = item.getAttribute("item-type");

    // toggle + enable/disable button class
    if (item.classList.contains("selected")) {
        enableButtonClass(class);
        item.classList.remove(selected);
    } else {
        disableButtonClass(class);
        item.classList.remove("disabled");
        item.classList.add("selected");
    }
}

function disableButtonClass(class) {
    let list = document.querySelectorAll(class);
    list.forEach((element) => element.classList.add("disabled"));
}

function enableButtonClass(class) {
    let list = document.querySelectorAll(class);
    list.forEach((element) => element.classList.remove("disabled"));
}

function deleteButtons() {
    buttons = document.querySelectorAll(".action-button");
    buttons.forEach((element) => element.remove());
}

async function getHandArray(player) {
    try{
        const response = await fetch(`${apiBaseUrl}/getHand?p=${player}`);
        const array = response.text.split(", ")
    } catch (error) {
        console.error("error in getHandArray:", error);
    }
}

function setScoreboard(scoreboard) {
console.log(scoreboard);
    for (let i = 1; i <= 4; i++){
        setPlayerScore(i, scoreboard[2*(i-1)], scoreboard[2*(i-1)+1]);
    }
}

function setPlayerScore(p, cards, shields) {
    let classString = ".player-score.player-"+p;
    let player = document.querySelectorAll(classString)[0]; // only one such element

    player.querySelector(".card-count").innerHTML = cards;
    player.querySelector(".shield-count").innerHTML = shields;
}
