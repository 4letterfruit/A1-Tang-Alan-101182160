package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.StringWriter;

class GameTest {
    Game testGame = new Game();
    @Test
    void RESP_1_TEST_1(){
        // reset decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // check decks created
        assertNotNull(testGame.adventureDeck);
        assertNotNull(testGame.eventDeck);

        // check card counts
        assertEquals(100, testGame.adventureDeck.size());
        assertEquals(17, testGame.eventDeck.size());
    }

    @Test
    void RESP_1_TEST_2(){
        // reset decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // check card details, count how many of each card exists in the adventure deck
        assertEquals(8, testGame.adventureDeck.cardCount("F5"));
        assertEquals(7, testGame.adventureDeck.cardCount("F10"));
        assertEquals(8, testGame.adventureDeck.cardCount("F15"));
        assertEquals(7, testGame.adventureDeck.cardCount("F20"));
        assertEquals(7, testGame.adventureDeck.cardCount("F25"));
        assertEquals(4, testGame.adventureDeck.cardCount("F30"));
        assertEquals(4, testGame.adventureDeck.cardCount("F35"));
        assertEquals(2, testGame.adventureDeck.cardCount("F40"));
        assertEquals(2, testGame.adventureDeck.cardCount("F50"));
        assertEquals(1, testGame.adventureDeck.cardCount("F70"));

        assertEquals(6,  testGame.adventureDeck.cardCount("D5"));
        assertEquals(12, testGame.adventureDeck.cardCount("H10"));
        assertEquals(16, testGame.adventureDeck.cardCount("S10"));
        assertEquals(8,  testGame.adventureDeck.cardCount("B15"));
        assertEquals(6,  testGame.adventureDeck.cardCount("L20"));
        assertEquals(2,  testGame.adventureDeck.cardCount("E30"));
    }

    @Test
    void RESP_1_TEST_3(){
        // reset decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // check how many of each card exists in the event deck
        assertEquals(3,  testGame.eventDeck.cardCount("Q2"));
        assertEquals(4,  testGame.eventDeck.cardCount("Q3"));
        assertEquals(3,  testGame.eventDeck.cardCount("Q4"));
        assertEquals(2,  testGame.eventDeck.cardCount("Q5"));

        assertEquals(1,  testGame.eventDeck.cardCount("E-Plague"));
        assertEquals(2,  testGame.eventDeck.cardCount("E-Queen's Favor"));
        assertEquals(2,  testGame.eventDeck.cardCount("E-Prosperity"));
    }

    @Test
    void RESP_2_TEST_1(){
        // reset deck
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");

        // check Deck swap function
        assertEquals("F5", testGame.adventureDeck.get(0));

        testGame.adventureDeck.swap(0, 10);
        assertEquals("F10", testGame.adventureDeck.get(0));
        assertEquals("F5", testGame.adventureDeck.get(10));

        testGame.adventureDeck.swap(0, 20);
        assertEquals("F15", testGame.adventureDeck.get(0));
        assertEquals("F10", testGame.adventureDeck.get(20));

        testGame.adventureDeck.swap(0, 50);
        assertEquals("D5", testGame.adventureDeck.get(0));
        assertEquals("F15", testGame.adventureDeck.get(50));
    }

    @Test
    void RESP_2_TEST_2(){
        // reset the adventure deck
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        Deck defaultDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");

        assertArrayEquals(defaultDeck.cardList.toArray(), testGame.adventureDeck.cardList.toArray());

        // test shuffling at random
        // arrays will have very low probability of being equal
        int fails = 0;
        for (int i = 0; i < 10; i++) {
            testGame.adventureDeck.shuffle();
            if (Arrays.equals(defaultDeck.cardList.toArray(), testGame.adventureDeck.cardList.toArray())){
                fails++;
            }
        }
        // if over half of the shuffles failed then the shuffle function is likely faulty
        assertFalse(fails>5);
    }

    @Test
    void RESP_2_TEST_3(){
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");

        testGame.adventureDeck.shuffle();
        // confirm the number of each card remains the same

        assertEquals(100, testGame.adventureDeck.size());

        assertEquals(8, testGame.adventureDeck.cardCount("F5"));
        assertEquals(7, testGame.adventureDeck.cardCount("F10"));
        assertEquals(8, testGame.adventureDeck.cardCount("F15"));
        assertEquals(7, testGame.adventureDeck.cardCount("F20"));
        assertEquals(7, testGame.adventureDeck.cardCount("F25"));
        assertEquals(4, testGame.adventureDeck.cardCount("F30"));
        assertEquals(4, testGame.adventureDeck.cardCount("F35"));
        assertEquals(2, testGame.adventureDeck.cardCount("F40"));
        assertEquals(2, testGame.adventureDeck.cardCount("F50"));
        assertEquals(1, testGame.adventureDeck.cardCount("F70"));

        assertEquals(6,  testGame.adventureDeck.cardCount("D5"));
        assertEquals(12, testGame.adventureDeck.cardCount("H10"));
        assertEquals(16, testGame.adventureDeck.cardCount("S10"));
        assertEquals(8,  testGame.adventureDeck.cardCount("B15"));
        assertEquals(6,  testGame.adventureDeck.cardCount("L20"));
        assertEquals(2,  testGame.adventureDeck.cardCount("E30"));
    }

    @Test
    void RESP_3_TEST_1(){
        // reset the decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // test removing the cards
        testGame.adventureDeck.swap(0, testGame.adventureDeck.size()-1);
        assertEquals("F5", testGame.adventureDeck.draw());
        assertEquals("E30", testGame.adventureDeck.draw());

        assertEquals("E-Prosperity", testGame.eventDeck.draw());
        assertEquals("E-Prosperity", testGame.eventDeck.draw());
        assertEquals("E-Queen's Favor", testGame.eventDeck.draw());
    }

    @Test
    void RESP_3_TEST_2(){
        // reset the decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // check number of cards after drawing
        testGame.adventureDeck.swap(0, testGame.adventureDeck.size()-1);
        testGame.adventureDeck.draw();
        assertEquals(99, testGame.adventureDeck.size());

        testGame.adventureDeck.draw();
        assertEquals(98, testGame.adventureDeck.size());

        assertEquals(1,  testGame.adventureDeck.cardCount("E30"));
        assertEquals(7, testGame.adventureDeck.cardCount("F5"));

        assertEquals(2, testGame.eventDeck.cardCount("E-Prosperity"));
        testGame.eventDeck.draw();
        testGame.eventDeck.draw();
        assertEquals(15, testGame.eventDeck.size());
        assertEquals(0, testGame.eventDeck.cardCount("E-Prosperity"));
    }

    @Test
    void RESP_4_TEST_1(){
        // confirm players are created
        assertNotNull(testGame.PLAYER_1);
        assertNotNull(testGame.PLAYER_2);
        assertNotNull(testGame.PLAYER_3);
        assertNotNull(testGame.PLAYER_4);
    }

    @Test
    void RESP_4_TEST_2(){
        // verify player ids 1-4
        assertEquals(1, testGame.PLAYER_1.id);
        assertEquals(2, testGame.PLAYER_2.id);
        assertEquals(3, testGame.PLAYER_3.id);
        assertEquals(4, testGame.PLAYER_4.id);
    }

    @Test
    void RESP_5_TEST_1(){
        // reset decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // reset players
        testGame.PLAYER_1 = new Player(1);
        testGame.PLAYER_2 = new Player(2);
        testGame.PLAYER_3 = new Player(3);
        testGame.PLAYER_4 = new Player(4);

        assertEquals(100, testGame.adventureDeck.size());

        // distribute 12 cards per player, verify player hand size and deck size
        testGame.distributeCards();
        assertEquals(12, testGame.PLAYER_1.handSize());
        assertEquals(12, testGame.PLAYER_2.handSize());
        assertEquals(12, testGame.PLAYER_3.handSize());
        assertEquals(12, testGame.PLAYER_4.handSize());
        assertEquals(52, testGame.adventureDeck.size());
    }

    @Test
    void RESP_5_TEST_2(){
        // reset decks
        testGame.adventureDeck = new Deck("F5/8,F10/7,F15/8,F20/7,F25/7,F30/4,F35/4,F40/2,F50/2,F70/1,D5/6,H10/12,S10/16,B15/8,L20/6,E30/2");
        testGame.eventDeck = new Deck("Q2/3,Q3/4,Q4/3,Q5/2,E-Plague/1,E-Queen's Favor/2,E-Prosperity/2");

        // reset players
        testGame.PLAYER_1 = new Player(1);
        testGame.PLAYER_2 = new Player(2);
        testGame.PLAYER_3 = new Player(3);
        testGame.PLAYER_4 = new Player(4);


        // check that total number of each card has not changed
        testGame.distributeCards();
        assertEquals(8, testGame.adventureDeck.cardCount("F5") + testGame.PLAYER_1.cardCount("F5") + testGame.PLAYER_2.cardCount("F5") + testGame.PLAYER_3.cardCount("F5") + testGame.PLAYER_4.cardCount("F5"));
        assertEquals(7, testGame.adventureDeck.cardCount("F10") + testGame.PLAYER_1.cardCount("F10") + testGame.PLAYER_2.cardCount("F10") + testGame.PLAYER_3.cardCount("F10") + testGame.PLAYER_4.cardCount("F10"));
        assertEquals(8, testGame.adventureDeck.cardCount("F15") + testGame.PLAYER_1.cardCount("F15") + testGame.PLAYER_2.cardCount("F15") + testGame.PLAYER_3.cardCount("F15") + testGame.PLAYER_4.cardCount("F15"));
        assertEquals(7, testGame.adventureDeck.cardCount("F20") + testGame.PLAYER_1.cardCount("F20") + testGame.PLAYER_2.cardCount("F20") + testGame.PLAYER_3.cardCount("F20") + testGame.PLAYER_4.cardCount("F20"));
        assertEquals(7, testGame.adventureDeck.cardCount("F25") + testGame.PLAYER_1.cardCount("F25") + testGame.PLAYER_2.cardCount("F25") + testGame.PLAYER_3.cardCount("F25") + testGame.PLAYER_4.cardCount("F25"));
        assertEquals(4, testGame.adventureDeck.cardCount("F30") + testGame.PLAYER_1.cardCount("F30") + testGame.PLAYER_2.cardCount("F30") + testGame.PLAYER_3.cardCount("F30") + testGame.PLAYER_4.cardCount("F30"));
        assertEquals(4, testGame.adventureDeck.cardCount("F35") + testGame.PLAYER_1.cardCount("F35") + testGame.PLAYER_2.cardCount("F35") + testGame.PLAYER_3.cardCount("F35") + testGame.PLAYER_4.cardCount("F35"));
        assertEquals(2, testGame.adventureDeck.cardCount("F40") + testGame.PLAYER_1.cardCount("F40") + testGame.PLAYER_2.cardCount("F40") + testGame.PLAYER_3.cardCount("F40") + testGame.PLAYER_4.cardCount("F40"));
        assertEquals(2, testGame.adventureDeck.cardCount("F50") + testGame.PLAYER_1.cardCount("F50") + testGame.PLAYER_2.cardCount("F50") + testGame.PLAYER_3.cardCount("F50") + testGame.PLAYER_4.cardCount("F50"));
        assertEquals(1, testGame.adventureDeck.cardCount("F70") + testGame.PLAYER_1.cardCount("F70") + testGame.PLAYER_2.cardCount("F70") + testGame.PLAYER_3.cardCount("F70") + testGame.PLAYER_4.cardCount("F70"));
        assertEquals(6,  testGame.adventureDeck.cardCount("D5") + testGame.PLAYER_1.cardCount("D5") + testGame.PLAYER_2.cardCount("D5") + testGame.PLAYER_3.cardCount("D5") + testGame.PLAYER_4.cardCount("D5"));
        assertEquals(12, testGame.adventureDeck.cardCount("H10") + testGame.PLAYER_1.cardCount("H10") + testGame.PLAYER_2.cardCount("H10") + testGame.PLAYER_3.cardCount("H10") + testGame.PLAYER_4.cardCount("H10"));
        assertEquals(16, testGame.adventureDeck.cardCount("S10") + testGame.PLAYER_1.cardCount("S10") + testGame.PLAYER_2.cardCount("S10") + testGame.PLAYER_3.cardCount("S10") + testGame.PLAYER_4.cardCount("S10"));
        assertEquals(8,  testGame.adventureDeck.cardCount("B15") + testGame.PLAYER_1.cardCount("B15") + testGame.PLAYER_2.cardCount("B15") + testGame.PLAYER_3.cardCount("B15") + testGame.PLAYER_4.cardCount("B15"));
        assertEquals(6,  testGame.adventureDeck.cardCount("L20") + testGame.PLAYER_1.cardCount("L20") + testGame.PLAYER_2.cardCount("L20") + testGame.PLAYER_3.cardCount("L20") + testGame.PLAYER_4.cardCount("L20"));
        assertEquals(2,  testGame.adventureDeck.cardCount("E30") + testGame.PLAYER_1.cardCount("E30") + testGame.PLAYER_2.cardCount("E30") + testGame.PLAYER_3.cardCount("E30") + testGame.PLAYER_4.cardCount("E30"));
    }

    @Test
    void RESP_6_TEST_1(){
        // confirm cards are shuffled and distributed on game launch
        testGame = new Game();
        Game game2 = new Game();

        assertEquals(52, testGame.adventureDeck.size());
        assertEquals(12, testGame.PLAYER_1.handSize());
        assertEquals(12, testGame.PLAYER_2.handSize());
        assertEquals(12, testGame.PLAYER_3.handSize());
        assertEquals(12, testGame.PLAYER_4.handSize());


        int fails = 0;
        if (Arrays.equals(game2.adventureDeck.cardList.toArray(), testGame.adventureDeck.cardList.toArray())){
            fails++;
        }
        if (Arrays.equals(game2.PLAYER_1.hand.toArray(), testGame.PLAYER_1.hand.toArray())){
            fails++;
        }
        if (Arrays.equals(game2.PLAYER_2.hand.toArray(), testGame.PLAYER_2.hand.toArray())){
            fails++;
        }
        if (Arrays.equals(game2.PLAYER_3.hand.toArray(), testGame.PLAYER_3.hand.toArray())){
            fails++;
        }
        if (Arrays.equals(game2.PLAYER_4.hand.toArray(), testGame.PLAYER_4.hand.toArray())){
            fails++;
        }

        // if card arrays are all the same, deck was not shuffled
        assertNotEquals(5, fails);
    }

    @Test
    void RESP_6_TEST_2(){
        testGame = new Game();

        // verify the prompt to start the game
        String input = "\n";
        StringWriter output = new StringWriter();
        testGame.startGame(new Scanner(input), new PrintWriter(output));

        assertTrue(output.toString().contains("Starting game"));
    }

    @Test
    void RESP_7_TEST_1(){
        testGame = new Game();

        // confirm Player 1 starting
        assertEquals(testGame.PLAYER_1, testGame.activePlayer);
    }

    @Test
    void RESP_7_TEST_2(){
        testGame = new Game();

        // reset players and cards
        testGame.initializePlayers();
        testGame.initializeDecks();

        // get some foe cards in player 1's hand
        testGame.adventureDeck.swap(0, 87);
        testGame.adventureDeck.swap(10, 83);


        testGame.distributeCards();

        // verify the player's hand is displayed in sorted order
        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();
        testGame.startTurn(input, new PrintWriter(output), testGame.activePlayer);

        assertTrue(output.toString().contains(testGame.PLAYER_1.hand.toString()));
        assertTrue(output.toString().contains("F5, F10, D5, S10, S10, S10, H10, H10, H10, B15, L20, E30"));
    }

    @Test
    void RESP_8_TEST_1(){
        testGame = new Game();

        testGame.initializeDecks();

        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();
        testGame.drawEvent(input, new PrintWriter(output));

        // Confirm prosperity is drawn and declared
        assertTrue(output.toString().contains("E-Prosperity"));
        assertTrue(output.toString().contains("All players draw 2 adventure cards"));
    }

    @Test
    void RESP_8_TEST_2(){
        testGame = new Game();

        testGame.initializeDecks();
        testGame.eventDeck.swap(0, 16);

        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();
        testGame.drawEvent(input, new PrintWriter(output));

        // Confirm Q2 is drawn and declared
        assertTrue(output.toString().contains("Sponsor a quest: Q2"));
    }

    @Test
    void RESP_9_TEST_1(){
        testGame = new Game();

        testGame.initializeDecks();
        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();

        // test e-plague
        testGame.PLAYER_1.addShields(2);
        testGame.eventDeck.swap(12, 16);
        testGame.drawEvent(input, new PrintWriter(output));
        assertEquals(0, testGame.PLAYER_1.getShields());

        testGame.initializeDecks();
        testGame.eventDeck.swap(12, 16);
        testGame.PLAYER_1.addShields(1);
        testGame.drawEvent(input, new PrintWriter(output));
        assertEquals(0, testGame.PLAYER_1.getShields());

        testGame.initializeDecks();
        testGame.eventDeck.swap(12, 16);
        testGame.PLAYER_1.addShields(5);
        testGame.drawEvent(input, new PrintWriter(output));
        assertEquals(3, testGame.PLAYER_1.getShields());


    }

    @Test
    void RESP_9_TEST_2(){
        testGame = new Game();

        testGame.initializeDecks();
        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();

        // test e-queen's favor
        testGame.eventDeck.swap(13, 16);
        testGame.drawEvent(input, new PrintWriter(output));

        assertEquals(14, testGame.PLAYER_1.handSize());
        assertEquals(12, testGame.PLAYER_2.handSize());
        assertEquals(12, testGame.PLAYER_3.handSize());
        assertEquals(12, testGame.PLAYER_4.handSize());
    }

    @Test
    void RESP_9_TEST_3(){
        testGame = new Game();

        testGame.initializeDecks();
        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();

        // test e-prosperity, each player has 12 + 2 cards
        testGame.drawEvent(input, new PrintWriter(output));

        assertEquals(14, testGame.PLAYER_1.handSize());
        assertEquals(14, testGame.PLAYER_2.handSize());
        assertEquals(14, testGame.PLAYER_3.handSize());
        assertEquals(14, testGame.PLAYER_4.handSize());
    }

    @Test
    void RESP_10_TEST_1(){
        testGame = new Game();

        StringWriter output = new StringWriter();

        // test need to trim, number of cards to delete is printed to output
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());

        Scanner input = new Scanner("1\n1\n");
        testGame.trim(input, new PrintWriter(output));
        assertTrue(output.toString().contains("2"));

        testGame = new Game();

        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());

        input = new Scanner("1\n1\n1\n1\n");
        testGame.trim(input, new PrintWriter(output));
        assertTrue(output.toString().contains("4"));
    }

    @Test
    void RESP_10_TEST_2(){
        testGame = new Game();

        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();

        // test no need to trim, nothing is printed to output
        testGame.trim(input, new PrintWriter(output));

        testGame.trim(input, new PrintWriter(output));

        assertTrue(output.toString().isBlank());
    }
}