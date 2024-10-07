package org.example;
import java.util.Scanner;
import java.io.PrintWriter;

public class Game {
    Deck adventureDeck = null;
    Deck eventDeck = null;

    Player PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4;

    // Game constructor
    public Game(){
        initializeDecks();
        initializePlayers();

        adventureDeck.shuffle();
        eventDeck.shuffle();

        distributeCards();
    }

    private void initializeDecks(){
        adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");
    }

    private void initializePlayers(){
        PLAYER_1 = new Player(1);
        PLAYER_2 = new Player(2);
        PLAYER_3 = new Player(3);
        PLAYER_4 = new Player(4);
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
}




