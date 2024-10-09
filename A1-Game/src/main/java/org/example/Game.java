package org.example;
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
        output.println("Active Player: " + activePlayer.id);
        output.flush();
        activePlayer.sortHand();
        output.println(activePlayer.hand.toString());
        output.flush();
    }

    // draw an event card
    // identify the event card and its effect
    public void drawEvent(Scanner input, PrintWriter output){
        output.println("Drawing event...");
        String event = eventDeck.draw();

        switch (event){
            case "Q2":
            case "Q3":
            case "Q4":
            case "Q5":
                output.println("Sponsor a quest: " + event);
                break;

            case "E-Plague":
                output.println("E-Plague: Lose 2 shields");
                break;
            case "E-Queen's Favor":
                output.println("E-Queen's Favor: Draw 2 adventure cards");
                break;
            case "E-Prosperity":
                output.println("E-Prosperity: All players draw 2 adventure cards");
                break;
        }
        output.flush();
    }

    public void clearScreen(){
        for (int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public static void main(String[] args){
        Game game = new Game();
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);
        game.startGame(input, output);

        // game loop
        while(true){
            game.startTurn(input, output, game.activePlayer);

            game.drawEvent(input, output);
            break;
        }
    }
}




