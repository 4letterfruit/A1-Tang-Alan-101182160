package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;

public class Game {
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

    public void initializeDecks(){
        adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");
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

            String in = input.nextLine();
            if (in.equalsIgnoreCase("quit")){
                prevValue = isValidStage(output, stage, prevValue, hasFoe);

                if (prevValue > 0){
                    // valid input, start updating next stages
                    overview.add(new ArrayList<String>(stage));
                    stage = new ArrayList<String>();
                    i++;
                    hasFoe = false;
                    continue;
                }else{
                    // quit was input but invalid stage, return null and rerun sponsor from gameloop
                    // return cards from stage setup to player
                    for (String s : stage){
                        player.add(s);
                    }
                    for (ArrayList<String> a : overview){
                        for (String s : a){
                            player.add(s);
                        }
                    }
                    return null;
                }
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
                }else if(stage.contains(card)){
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

    public int isValidStage(PrintWriter output, ArrayList<String> stage, int prevValue, boolean hasFoe){
        // check empty
        if (stage.isEmpty()){
            output.println("Invalid: A stage cannot be empty");
            output.flush();
            return 0;
        }

        if (!hasFoe){
            output.println("Invalid: Stage requires a foe card");
            output.flush();
            return 0;
        }

        // go through submitted stage and update checked values
        int value = 0;
        for (String s : stage){
            value += Integer.parseInt(s.substring(1));
        }

        if (value <= prevValue){
            output.println("Invalid: Insufficient value for this stage");
            output.flush();
            return 0;
        }

        return value;
    }

    public ArrayList<Integer> getEligibleAttackers(Scanner input, PrintWriter output, ArrayList<String> stage, Player sponsor){
        return null;
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

    // primarily used for testing.
    public void nextPlayer(){
        activePlayer = activePlayer.nextPlayer;
    }

    public void gameLoop(){
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);
// game loop
        while(true){
            startTurn(input, output, activePlayer);
            trim(input, output, activePlayer);
            String quest = drawEvent(input, output);
            if (quest != null){
                Player sponsor = triggerQuest(input, output, quest);
                if (sponsor != null){
                    while(true){
                        ArrayList<ArrayList<String>> overview = sponsorQuest(input, output, quest, sponsor);
                        if (overview != null){
                            break;
                        }
                    }

                }
            }
            trim(input, output, activePlayer);
            nextPlayer();
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);
        game.startGame(input, output);
        game.gameLoop();
    }
}