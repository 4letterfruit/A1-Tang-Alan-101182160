package org.example;

import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class Game {
    ArrayList<ArrayList<String>> webOverview = new ArrayList<ArrayList<String>>();
    Deck adventureDeck = null;
    Deck eventDeck = null;

    Player PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4;
    Player activePlayer;

    // Game constructor
    public Game(){
        initializeDecks();
        initializePlayers();

        adventureDeck.shuffle();
        eventDeck.shuffle();

        distributeCards();
    }

    @GetMapping("/start")
    public String start(){
        initializeDecks();
        initializePlayers();

        adventureDeck.shuffle();
        eventDeck.shuffle();

        distributeCards();
        // returning format P1-handsize P1-shields P2-handsize P2-shields P3-handsize P3-shields P4-handsize P4-shields
        return currentScoreboard();
    }

    public String currentScoreboard(){
        return String.format("%d %d %d %d %d %d %d %d",
                PLAYER_1.handSize(), PLAYER_1.getShields(), PLAYER_2.handSize(), PLAYER_2.getShields(),
                PLAYER_3.handSize(), PLAYER_3.getShields(), PLAYER_4.handSize(), PLAYER_4.getShields());
    }

    public void initializeDecks(){
        adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");
    }

    @GetMapping("getHand")
    public String getPlayerHand(@RequestParam String p){
        Player player = getPlayerById(Integer.parseInt(p));

        return player.hand.toString().substring(1,player.hand.toString().length()-1);
    }

    public void initializePlayers(){
        PLAYER_1 = new Player(1);
        PLAYER_2 = new Player(2);
        PLAYER_3 = new Player(3);
        PLAYER_4 = new Player(4);

        PLAYER_1.setNextPlayer(PLAYER_2);
        PLAYER_2.setNextPlayer(PLAYER_3);
        PLAYER_3.setNextPlayer(PLAYER_4);
        PLAYER_4.setNextPlayer(PLAYER_1);

        activePlayer = PLAYER_1;
    }

    public void distributeCards(){
        // draws a card for each player 12 times
        for(int i = 0; i < 12; i++){
            PLAYER_1.add(adventureDeck.draw());
            PLAYER_2.add(adventureDeck.draw());
            PLAYER_3.add(adventureDeck.draw());
            PLAYER_4.add(adventureDeck.draw());
        }
    }

    public void startGame(Scanner input, PrintWriter output){
        output.println("Press Enter to begin");
        output.flush();

        input.nextLine();
        output.println("Starting game");
        output.flush();
    }

    public void startTurn(Scanner input, PrintWriter output, Player player){
        // clear the screen
        clearScreen();
        output.println("Next Active Player: " + activePlayer.id);
        output.flush();
        input.nextLine();
        activePlayer.sortHand();
        output.println(activePlayer.hand.toString());
        output.flush();
    }

    // draw an event card
    // identify the event card and its effect
    public String drawEvent(Scanner input, PrintWriter output){
        output.println("Drawing event...");
        output.flush();
        input.nextLine();
        String event = eventDeck.draw();

        switch (event){
            case "Q2":
            case "Q3":
            case "Q4":
            case "Q5":
                output.println("Sponsor a quest: " + event);
                output.flush();
                input.nextLine();
                return event;

            case "E-Plague":
                output.println("E-Plague: Lose 2 shields");
                activePlayer.decreaseShields(2);
                break;
            case "E-Queen's Favor":
                output.println("E-Queen's Favor: Draw 2 adventure cards");
                activePlayer.add(adventureDeck.draw());
                activePlayer.add(adventureDeck.draw());
                break;
            case "E-Prosperity":
                output.println("E-Prosperity: All players draw 2 adventure cards");
                for(int i = 0; i < 2; i++){
                    PLAYER_1.add(adventureDeck.draw());
                    PLAYER_2.add(adventureDeck.draw());
                    PLAYER_3.add(adventureDeck.draw());
                    PLAYER_4.add(adventureDeck.draw());
                }
                break;
        }
        output.flush();
        input.nextLine();
        return null;
    }

    public Player triggerQuest(Scanner input, PrintWriter output, String quest){
        Player candidate = activePlayer;
        for (int i = 0; i < 4;){
            clearScreen();
            output.println(String.format("Player %d", candidate.id));
            output.flush();
            input.nextLine();
            output.println(candidate.hand.toString());
            output.println(String.format("Will Player %d sponsor %s? (Y/N)", candidate.id, quest));
            output.flush();
            String text = input.nextLine();

            if (text.equalsIgnoreCase("y")){
                // sponsor quest
                output.println(String.format("Quest %s sponsored by Player %d", quest, candidate.id));
                return candidate;
            }else if (text.equalsIgnoreCase("n")){
                candidate = candidate.nextPlayer;
                i++;
            }
            // else invalid input, redo for current candidate
        }

        // loop ended
        output.println(String.format("Quest %s declined. End turn.", quest));
        return null;
    }

    public ArrayList<ArrayList<String>> sponsorQuest(Scanner input, PrintWriter output, String quest, Player player){
        // fix hand size first
        trim(input, output, player);

        ArrayList<String> stage = new ArrayList<String>();
        ArrayList<ArrayList<String>> overview = new ArrayList<ArrayList<String>>();

        int stageCount = Integer.parseInt(quest.substring(1));

        int i = 0;
        int prevValue = 0;
        boolean hasFoe = false;
        while(i < stageCount){
            printOverview(output, overview);
            output.println(String.format("\nCurrent stage %d: %s", i+1, stage));
            output.println(String.format("Select the next card for stage %d", i+1));
            output.println(player.hand);
            output.flush();

            System.out.println(prevValue);
            String in = input.nextLine();
            if (in.equalsIgnoreCase("end_test")){
                return null;
            }
            if (in.equalsIgnoreCase("quit")){
                int stageValue = isValidStage(output, stage, prevValue, hasFoe);

                if (stageValue > 0){
                    // valid input, start updating next stages
                    prevValue = stageValue;
                    overview.add(new ArrayList<String>(stage));
                    stage = new ArrayList<String>();
                    i++;
                }else{
                    // quit was input but invalid stage, reset the cards for the current stage
                    // return cards from stage setup to player
                    while (!stage.isEmpty()){
                        player.add(stage.remove(stage.size()-1));
                    }
                    stage = new ArrayList<String>();
                }
                hasFoe = false;
                continue;
            }
            try{
                int selectIndex = Integer.parseInt(in);
                String card = player.remove(selectIndex);
                if (card == null){
                    continue;
                }

                if (card.charAt(0) == 'F'){
                    // multiple foes is declined, check foe before anything else
                    if (hasFoe){
                        output.println("Must have exactly 1 Foe card");
                        output.flush();
                        player.add(card);
                        continue;
                    }else{
                        hasFoe = true;
                    }
                }else if (stage.contains(card)){
                    // repeated weapon is declined
                    output.println("Cannot repeat weapon cards");
                    output.flush();
                    player.add(card);
                    continue;
                }

                stage.add(card);
            }catch (Exception e){
                continue;
            }
        }

        output.println("Stages successfully set");
        printOverview(output, overview);
        return overview;
    }

    @PostMapping("/createStage")
    public String createStage(String p, String cardString, String prevValString, boolean hasFoe){
        Player player = getPlayerById(Integer.parseInt(p));
        String[] cardArray = cardString.split(",");
        ArrayList<String> stageList = new ArrayList<>(Arrays.asList(cardArray));
        StringWriter output = new StringWriter();

        switch(isValidStage(new PrintWriter(output), stageList, Integer.parseInt(prevValString), hasFoe))
        {
            case -1:
                return "";
            case -2:
                return "";
            case -3:
                return "";
            default:
                return "";
                // if successful, start removing cards from player hand and adding to webOverview
        }
    }

    @PostMapping("/AwardShields")
    public String awardShields(String p, String shields) {
        Player player = getPlayerById(Integer.parseInt(p));
        player.addShields(Integer.parseInt(shields));

        return "0";
    }

    public int isValidStage(PrintWriter output, ArrayList<String> stage, int prevValue, boolean hasFoe){
        // check empty
        if (stage.isEmpty()){
            output.println("Invalid: A stage cannot be empty");
            output.flush();
            return -1;
        }

        if (!hasFoe){
            output.println("Invalid: Stage requires a foe card");
            output.flush();
            return -2;
        }

        // go through submitted stage and update checked values
        int value = 0;
        for (String s : stage){
            value += Integer.parseInt(s.substring(1));
        }

        if (value <= prevValue){
            output.println("Invalid: Insufficient value for this stage");
            output.flush();
            return -3;
        }

        return value;
    }

    public HashSet<Integer> getEligibleAttackers(HashSet<Integer> eligible, HashSet<Integer> ineligible){
        for (Integer i : ineligible){
            eligible.remove(i);
        }

        return eligible;
    }

    public void printOverview(PrintWriter output, ArrayList<ArrayList<String>> overview){
        for(int i = 0; i < overview.size(); i++){
            output.println(String.format("Stage %d: %s", i+1, overview.get(i).toString()));
        }
        output.flush();
    }

    public void clearScreen(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public void trim(Scanner input, PrintWriter output, Player p){
        if (p.handSize() > 12){
            int excess = p.handSize() - 12;
            output.println(String.format("Player %d Hand size too large, cards to discard: %d", p.id, excess));
            output.flush();
        }

        while (p.handSize() > 12){
            output.println("Player Hand: " + p.hand);
            output.println(String.format("Remove a card [1 - %d]", p.handSize()));
            output.flush();

            int index = 0;
            try{
                index = Integer.parseInt(input.nextLine());
            }catch (Exception e){
                continue;
            }

            String removed = p.remove(index);
            if (removed == null){
                continue;
            }

            adventureDeck.discard(removed);
        }
    }

    public HashSet<Integer> checkWinners(){
        HashSet<Integer> winners = new HashSet<Integer>();

        if (PLAYER_1.shields >= 7) winners.add(1);
        if (PLAYER_2.shields >= 7) winners.add(2);
        if (PLAYER_3.shields >= 7) winners.add(3);
        if (PLAYER_4.shields >= 7) winners.add(4);

        return winners;
    }

    public void declareWinners(PrintWriter output, HashSet<Integer> winners){
        output.println("The game has ended!");
        String winnerString = "";
        for (Integer i : winners){
            winnerString += String.format("Player %d ", i);
        }
        output.println(winnerString + " have won the game!");
        output.flush();
    }
    public boolean resolveAttack(Scanner input, PrintWriter output, ArrayList<String> stage, ArrayList<String> attack){
        // compute stage value
        int stageValue = 0;
        for (String s : stage){
            stageValue += Integer.parseInt(s.substring(1));
        }

        // compute attack value
        int attackValue = 0;
        for (String s : attack){
            attackValue += Integer.parseInt(s.substring(1));
            adventureDeck.discard(s); // add the card to the discard pile
        }

        // return attack >= stage value
        return attackValue >= stageValue;
    }

    public boolean setAttack(Scanner input, PrintWriter output, ArrayList<String> stage, Player player){
        clearScreen();
        output.println(String.format("Player %d", player.id));
        output.println("Cards in this stage: " + stage.size());
        output.flush();
        input.nextLine();

        // set attack
        ArrayList<String> attack = new ArrayList<String>();
        while (true){
            output.println(player.hand);
            output.println("Current attack: " + attack);
            output.println("Select a card for the attack");
            output.flush();
            String in = input.nextLine();

            if (in.equalsIgnoreCase("quit")){
                output.println(String.format("Attacking with %s", attack));
                output.flush();
                // resolve attack
                boolean result = resolveAttack(input, output, stage, attack);
                if (result == true){
                    output.println("Attack succeeded!");
                }else{
                    output.println("Attack failed");
                }
                output.flush();
                input.nextLine();
                return result;
            }

            try{
                String card = player.remove(Integer.parseInt(in));

                if (card.charAt(0) == 'F'){
                    player.add(card);
                    output.println("You may not use a foe card");
                    output.flush();
                    continue;
                }

                // repeated weapon card
                if (attack.contains(card)){
                    player.add(card);
                    output.println("You may not repeat weapon cards");
                    output.flush();
                    continue;
                }

                attack.add(card);
            }catch (Exception e){
                continue;
            }
        }
    }

    // primarily used for testing.
    public void nextPlayer(){
        activePlayer = activePlayer.nextPlayer;
    }

    public boolean promptAttack(Scanner input, PrintWriter output, int stageSize, Player player){
        clearScreen();
        output.println(String.format("Player %d", player.id));
        output.flush();
        input.nextLine();

        output.println(String.format("# Cards in current stage: %d", stageSize));
        output.println(player.hand.toString());
        output.println(String.format("Player %d, would you like to tackle this stage? (Y/N)", player.id));
        output.flush();

        // first prompt for an attack
        while(true){
            String in = input.nextLine();
            if (in.equalsIgnoreCase("y")){
                String drawnCard = adventureDeck.draw();
                output.println(drawnCard + " was drawn");
                player.add(drawnCard);
                trim(input, output, player);
                return true;
            }
            if (in.equalsIgnoreCase("n")){
                return false;
            }
        }
    }

    public void gameLoop(Scanner input, PrintWriter output){
// game loop
        while(true){
            startTurn(input, output, activePlayer);
            trim(input, output, activePlayer);
            String quest = drawEvent(input, output);
            if (quest != null){
                Player sponsor = triggerQuest(input, output, quest);
                ArrayList<ArrayList<String>> overview = null;
                if (sponsor != null){
                    while(overview == null){
                        overview = sponsorQuest(input, output, quest, sponsor);
                    }

                    HashSet<Integer> eligible = new HashSet<Integer>();
                    HashSet<Integer> ineligible = new HashSet<Integer>();
                    Player p = sponsor.nextPlayer;
                    for (int i = 0; i < 3; i++){
                        eligible.add(p.id);
                        p = p.nextPlayer;
                    }
                    for (ArrayList<String> stage : overview){
                        for (Integer e : eligible){
                            Player player = getPlayerById(e);

                            if (!promptAttack(input, output, stage.size(), player)){
                                ineligible.add(e);
                            }

                        }
                        eligible = getEligibleAttackers(eligible, ineligible);

                        for (Integer e : eligible){
                            Player player = getPlayerById(e);
                            if (!setAttack(input, output, stage, player)){
                                ineligible.add(e);
                            }
                        }
                        eligible = getEligibleAttackers(eligible, ineligible);

                    }

                    // anyone still "eligible" won every stage
                    int reward = Integer.parseInt(quest.substring(1));
                    for (Integer e : eligible){
                        getPlayerById(e).addShields(reward);
                    }

                    input.nextLine();

                    replenishCards(input, output, sponsor, overview);
                }
            }

            if (!checkWinners().isEmpty()){
                declareWinners(output, checkWinners());
                System.exit(0);
            }
            trim(input, output, activePlayer);
            nextPlayer();
        }
    }

    public void replenishCards(Scanner input, PrintWriter output, Player player, ArrayList<ArrayList<String>> overview){
        clearScreen();
        for (ArrayList<String> stage : overview){
            for (String s : stage){
                adventureDeck.discard(s);
                player.add(adventureDeck.draw());
            }
            player.add(adventureDeck.draw());
        }

        trim(input, output, player);
    }

    public Player getPlayerById(int i){
        return switch (i) {
            case 1 -> PLAYER_1;
            case 2 -> PLAYER_2;
            case 3 -> PLAYER_3;
            case 4 -> PLAYER_4;
            default -> null;
        };
    }

    public static void main(String[] args){
        Game game = new Game();
//        Scanner input = new Scanner(System.in);
//        PrintWriter output = new PrintWriter(System.out);
//        game.startGame(input, output);
//        game.gameLoop(input, output);
    }
}