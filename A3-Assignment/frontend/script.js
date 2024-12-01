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

async function promptPlayer(player) {
    // delete button that triggered this
    // create prompt
    // prompt button has onclick -> check trim -> draw an event card

}

async function drawEvent(player) {
    // delete the button that triggered this
    // display info
    // create a button that triggers the next course of action

    // plague, queen's favor, prosperity, quest

    // quest should run promptplayer(currentPlayer, size)
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
    // trim current player if necessary
    // button to prompt player
}

async function prosperity() {
    // delete the button that triggered this
    // tell backend to trigger prosperity
    // update scoreboard
    // trim current player if necessary, next action is promptPlayer(nextPlayer)
}

async function trim(nextAction) {
    // if handsize too big, request the player's hand from backend
    // display the hand with buttons
    // generate the trim input based on buttons pressed
    // send trim request + inputs to do
    // update scoreboard

    // create button with `onClick="${nextAction}"`
}

async function promptQuest(player, size) {
    // delete the button that triggered this
    // display hand
    // two buttons -> sponsor yes/no

    // yes has onclick = sponsor(player, quest)
    // if not last player, no has onclick = promptPlayer(promptQuest(nextPlayer, size)
    // if last player, no has onclick = promptPlayer(drawEvent(nextPlayer))
}

async function sponsor(player, quest) {
    // delete relevant buttons


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
