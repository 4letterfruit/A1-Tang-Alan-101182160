package org.example;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Scanner;

public class GameSteps {
    Game game = new Game();
    String eventCard;
    String adventureCard;
    Player sponsor;
    ArrayList<ArrayList<String>> overview;

    @Given("the game has started")
    public void Start_Game() {
        game.initializePlayers();
        game.initializeDecks();
    }

    @Given("Player {int}'s hand is [{string}]")
    public void Player_Hand_Is(int p, String h){
        Player player = game.getPlayerById(p);
        String[] g = h.split(" ");
        for (String s : g) {
            game.adventureDeck.swap(game.adventureDeck.cardList.indexOf(s), game.adventureDeck.size() - 1);
            player.add(game.adventureDeck.draw());
        }

        // if this is false, the given scenario is not met
        assertArrayEquals(g, player.hand.toArray());
    }

    @Then("Player {int}'s hand is now [{string}]")
    public void Player_Hand_Is_Now(int p, String h)
    {
        Player player = game.getPlayerById(p);
        assertArrayEquals(h.split(" "), player.hand.toArray());
    }

    @When("{string} is drawn by Player {int}")
    public void Quest_Is_Drawn(String event, int p){
        Player player = game.getPlayerById(p);
        while (game.activePlayer != player){
            game.nextPlayer();
        }
        game.eventDeck.swap(game.eventDeck.cardList.indexOf(event), game.eventDeck.size()-1);

        eventCard = game.drawEvent(new Scanner("\n\n\n\n\n"), new PrintWriter(System.out));
    }

    @Then("Player {int} sponsors the {string} and builds {string}")
    public void Player_Build_Q4(int p, String quest, String stages){
        // construct the input string, player p should accept
        int skips = (4 + (p - game.activePlayer.id))%4;
        String input = "";

        // skip until specified player
        for (int i = 0; i < skips; i++){
            input += "\nn\n";
        }
        // specified player accepts
        input += "\ny\n";

        sponsor = game.triggerQuest(new Scanner(input), new PrintWriter(System.out), "Q4");
        assertEquals(game.getPlayerById(p), sponsor);

        // set up inputs for stages
        input = "";
        String[] stageList = stages.split(",");
        ArrayList<String> storage = new ArrayList<String>();
        for (int i = 0; i < stageList.length; i++){
            stageList[i] = stageList[i].substring(1, stageList[i].length()-1);
            String[] stageCards = stageList[i].split(" ");

            for (String s : stageCards){
                storage.add(s);
                int index = sponsor.hand.indexOf(s)+1;
                input += index + "\n";
                sponsor.hand.remove(index-1);
            }

            // close stage
            input += "quit\n";
        }

        for (String s : storage){
            sponsor.add(s);
        }

        overview = game.sponsorQuest(new Scanner(input), new PrintWriter(System.out), quest, sponsor);

        // assert resulting stages
        for (int i = 0; i < stageList.length; i++){
            String[] stageCards = stageList[i].split(" ");
            assertArrayEquals(stageCards, overview.get(i).toArray());
        }
    }

    @Then("Player {int} builds attack [{string}] for stage {int} and wins")
    public void Player_Builds_Attack_Win(int p, String attack, int stage){
        boolean win = Player_Build_Attack(p, attack, stage);
        assertTrue(win);

        // beat the last stage
        if (stage >= overview.size()){
            Player player = game.getPlayerById(p);
            player.addShields(overview.size());
        }
    }

    @Then("Player {int} builds attack [{string}] for stage {int} and loses")
    public void Player_Builds_Attack_Lose(int p, String attack, int stage){
        boolean win = Player_Build_Attack(p, attack, stage);
        assertFalse(win);
    }

    // specifying a card to be drawn
    @Then("Player {int} participates in stage {int} - draws a {string}")
    public void Player_Participates_Draws_Card(int p, int stage, String drawn) {
        Player player = game.getPlayerById(p);
        // promptAttack will only read y unless a discard is required
        String input = "\ny\n1\n";
        StringWriter output = new StringWriter();

        // fix drawn card
        game.adventureDeck.swap(game.adventureDeck.cardList.indexOf(drawn), game.adventureDeck.cardList.size()-1);

        // input to discard
        game.promptAttack(new Scanner(input), new PrintWriter(output), overview.get(stage-1).size(), player);
        System.out.println(output);
        assertTrue(output.toString().contains(drawn + " was drawn"));
    }

    // any card can be drawn. use this in larger tests that might run out of a specific card through random draws
    @Then("Player {int} participates in stage {int}")
    public void Player_Participates(int p, int stage){
        Player player = game.getPlayerById(p);
        // promptAttack will only read y unless a discard is required
        String input = "\ny\n1\n";
        StringWriter output = new StringWriter();

        game.promptAttack(new Scanner(input), new PrintWriter(output), overview.get(stage-1).size(), player);
        System.out.println(output);
    }

    @Then("Player {int} declines to participate in stage {int}")
    public void Player_Declines(int p, int stage){
        String input = "\nn\n";

        game.promptAttack(new Scanner(input), new PrintWriter(System.out), overview.get(stage-1).size(), game.getPlayerById(p));
    }

    @Then("Player {int} participates in stage {int} - draws a {string} - discards a {string}")
    public void Player_Participates_Draws_Discards(int p, int stage, String drawn, String discard){
        Player player = game.getPlayerById(p);
        String input = "\ny\n";
        StringWriter output = new StringWriter();

        // fix drawn card
        game.adventureDeck.swap(game.adventureDeck.cardList.indexOf(drawn), game.adventureDeck.cardList.size()-1);

        // input to discard
        input += (player.hand.indexOf(discard)+1) + "\n";
        game.promptAttack(new Scanner(input), new PrintWriter(output), overview.get(stage).size(), player);
        System.out.println(output.toString());
        assertTrue(output.toString().contains(drawn + " was drawn"));

    }

    @Then("Player {int} replenishes {int} + {int} cards and trims hand")
    public void Player_Replenish_And_Trim(int p, int e, int q) {
        Player player = game.getPlayerById(p);
        // trimming without caring about the cards
        String input = "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n";
        game.replenishCards(new Scanner(input), new PrintWriter(System.out), player, overview);

        assertTrue(player.handSize() <= 12);
    }

    @Then("Player {int} trims hand")
    public void Player_Trim(int p) {
        Player player = game.getPlayerById(p);
        String input = "1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n";
        game.trim(new Scanner(input), new PrintWriter(System.out), player);

        assertTrue(player.handSize() <= 12);
    }

    // helper function
    public boolean Player_Build_Attack(int p, String attack, int stage){
        Player player = game.getPlayerById(p);

        // input string
        String input = "\n";
        ArrayList<String> attackCards = new ArrayList<String>(Arrays.asList(attack.split(" ")));
        for (String s : attackCards){
            int index = player.hand.indexOf(s)+1;
            input += index + "\n";
            player.hand.remove(index-1);
        }
        input += "quit\n\n";

        for (String s : attackCards){
            player.add(s);
        }

        return game.setAttack(new Scanner(input), new PrintWriter(System.out), overview.get(stage-1), player);
    }

    @Then("Player {int} has {int} shields")
    public void Player_Has_Shields(int p, int shields) {
        assertEquals(shields, game.getPlayerById(p).shields);
    }

    @Then("Player {string} wins")
    public void Player_Wins(String players){
        // collect the players from the feature
        String[] p = players.split(" ");
        HashSet<Integer> expected = new HashSet<Integer>();
        for (String s : p){
            expected.add(Integer.parseInt(s));
        }
        HashSet<Integer> actual = game.checkWinners();
        assertEquals(expected, actual);

        game.declareWinners(new PrintWriter(System.out), actual);
    }

    @Then("Player {int} has {int} cards")
    public void Player_Card_Count(int p, int cards){
        Player player = game.getPlayerById(p);
        assertEquals(cards, player.handSize());
    }
}
