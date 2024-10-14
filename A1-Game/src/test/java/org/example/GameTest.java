package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.ArrayList;
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

        Scanner input = new Scanner("\n\n\n\n\n");
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

        Scanner input = new Scanner("\n\n\n\n\n");
        StringWriter output = new StringWriter();
        testGame.drawEvent(input, new PrintWriter(output));

        // Confirm Q2 is drawn and declared
        assertTrue(output.toString().contains("Sponsor a quest: Q2"));
    }

    @Test
    void RESP_9_TEST_1(){
        testGame = new Game();

        testGame.initializeDecks();
        Scanner input = new Scanner("\n\n\n\n\n\n\n\n");
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
        Scanner input = new Scanner("\n\n\n\n");
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
        Scanner input = new Scanner("\n\n\n\n");
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
        testGame.trim(input, new PrintWriter(output), testGame.activePlayer);
        assertTrue(output.toString().contains("2"));

        testGame = new Game();

        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());

        input = new Scanner("1\n1\n1\n1\n");
        testGame.trim(input, new PrintWriter(output), testGame.activePlayer);
        assertTrue(output.toString().contains("4"));
    }

    @Test
    void RESP_10_TEST_2(){
        testGame = new Game();

        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();

        // test no need to trim, nothing is printed to output
        testGame.trim(input, new PrintWriter(output), testGame.activePlayer);

        testGame.trim(input, new PrintWriter(output), testGame.activePlayer);

        assertTrue(output.toString().isBlank());
    }

    @Test
    void RESP_11_TEST_1(){
        testGame = new Game();
        StringWriter output = new StringWriter();

        // test hand size is correct after trimming

        // test need to trim 2 cards
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());
        testGame.PLAYER_1.add(testGame.adventureDeck.draw());

        assertEquals(15, testGame.activePlayer.handSize());
        Scanner input = new Scanner("1\n1\n1\n");
        testGame.trim(input, new PrintWriter(output), testGame.activePlayer);
        assertEquals(12, testGame.activePlayer.handSize());

        assertEquals(3, testGame.adventureDeck.discardPile.size());
    }

    @Test
    void RESP_11_TEST_2(){
        testGame = new Game();

        // reset values to be predictable
        testGame.initializeDecks();
        testGame.initializePlayers();
        testGame.distributeCards();

        StringWriter output = new StringWriter();

        // test  correct cards are being discarded
        testGame.activePlayer.add(testGame.adventureDeck.draw());
        testGame.activePlayer.add(testGame.adventureDeck.draw());
        testGame.activePlayer.add(testGame.adventureDeck.draw());
        testGame.activePlayer.add(testGame.adventureDeck.draw());

        Scanner input = new Scanner("1\n3\n5\n10\n");
        testGame.activePlayer.sortHand();
        System.out.println(testGame.activePlayer.hand);

        String[] testArray = {"F50", "F70", "D5", "D5", "D5", "S10", "S10", "S10", "S10", "H10", "H10", "H10", "B15", "B15", "L20", "E30"};
        String[] endArray = {"F70", "D5", "D5", "S10", "S10", "S10", "H10", "H10", "H10", "B15", "L20", "E30"};
        assertArrayEquals(testArray, testGame.activePlayer.hand.toArray());
        testGame.trim(input, new PrintWriter(output), testGame.activePlayer);
        assertArrayEquals(endArray, testGame.activePlayer.hand.toArray());

    }

    @Test
    void RESP_12_TEST_1(){
        testGame = new Game();

        // confirm "next player" works
        assertEquals(testGame.PLAYER_1, testGame.activePlayer);
        testGame.nextPlayer();
        assertEquals(testGame.PLAYER_2, testGame.activePlayer);
    }

    @Test
    void RESP_12_TEST_2(){
        testGame = new Game();

        // confirm loops back to player 1
        testGame.activePlayer = testGame.PLAYER_4;
        testGame.nextPlayer();
        assertEquals(testGame.PLAYER_1, testGame.activePlayer);
    }

    @Test
    void RESP_13_TEST_1(){
        testGame = new Game();

        // quest is accepted by player 3
        Scanner input = new Scanner("\nn\nn\ny\n");
        StringWriter output = new StringWriter();
        testGame.triggerQuest(input, new PrintWriter(output), "Q2");
        assertTrue(output.toString().contains("sponsored by Player 3"));
    }

    @Test
    void RESP_13_TEST_2(){
        testGame = new Game();

        // quest is declined by all
        Scanner input = new Scanner("\nn\nn\nn\nn\n");
        StringWriter output = new StringWriter();
        testGame.triggerQuest(input, new PrintWriter(output), "Q2");
        assertTrue(output.toString().contains("declined. End turn."));
    }

    @Test
    void RESP_14_TEST_1(){
        testGame = new Game();
        testGame.initializePlayers();
        testGame.initializeDecks();

        testGame.adventureDeck.swap(0, 99);
        testGame.adventureDeck.swap(20, 95);
        testGame.distributeCards();

        // set up player 1's hand
        Scanner input = new Scanner("1\n2\nquit\n1\n1\nquit\n");
        StringWriter output = new StringWriter();

        testGame.sponsorQuest(input, new PrintWriter(output), "Q2", testGame.PLAYER_1);

        assertTrue(output.toString().contains("Stages successfully set"));
        assertTrue(output.toString().contains("F5"));
        assertTrue(output.toString().contains("D5"));
        assertTrue(output.toString().contains("F15"));
        assertTrue(output.toString().contains("S10"));
    }

    @Test
    void RESP_15_TEST_1(){
        testGame = new Game();
        testGame.initializePlayers();
        testGame.initializeDecks();

        testGame.adventureDeck.swap(0, 99);
        testGame.adventureDeck.swap(20, 95);
        testGame.distributeCards();

        // set up player 1's hand
        // test invalid input (no foes)
        Scanner input = new Scanner("3\nquit\n1\nquit\n1\nquit\n");
        StringWriter output = new StringWriter();

        testGame.sponsorQuest(input, new PrintWriter(output), "Q2", testGame.PLAYER_1);
        assertTrue(output.toString().contains("Stage requires a foe card"));
    }

    @Test
    void RESP_15_TEST_2(){
        testGame = new Game();
        testGame.initializePlayers();
        testGame.initializeDecks();

        testGame.adventureDeck.swap(0, 99);
        testGame.adventureDeck.swap(20, 95);
        testGame.distributeCards();

        // set up player 1's hand
        // test invalid input (multiple foes)
        Scanner input = new Scanner("1\n1\nquit\n1\nquit\n");
        StringWriter output = new StringWriter();

        testGame.sponsorQuest(input, new PrintWriter(output), "Q2", testGame.PLAYER_1);
        assertTrue(output.toString().contains("Must have exactly 1 Foe card"));
    }

    @Test
    void RESP_15_TEST_3(){
        testGame = new Game();
        testGame.initializePlayers();
        testGame.initializeDecks();

        testGame.adventureDeck.swap(0, 99);
        testGame.adventureDeck.swap(20, 95);
        testGame.distributeCards();

        // set up player 1's hand
        // test invalid input (value < previous stage)
        Scanner input = new Scanner("2\nquit\n1\nquit\n1\nquit\n1\nquit\n");
        StringWriter output = new StringWriter();

        testGame.sponsorQuest(input, new PrintWriter(output), "Q2", testGame.PLAYER_1);
        assertTrue(output.toString().contains("Insufficient value for this stage"));
    }

    @Test
    void RESP_15_TEST_4(){
        testGame = new Game();
        testGame.initializePlayers();
        testGame.initializeDecks();

        testGame.adventureDeck.swap(0, 99);
        testGame.adventureDeck.swap(20, 95);
        testGame.distributeCards();

        // set up player 1's hand
        // test invalid input (repeated weapon cards)
        Scanner input = new Scanner("1\n3\n3\nquit\n1\n5\nquit\n");
        StringWriter output = new StringWriter();

        testGame.sponsorQuest(input, new PrintWriter(output), "Q2", testGame.PLAYER_1);
        assertTrue(output.toString().contains("Cannot repeat weapon cards"));
    }

    @Test
    void RESP_15_TEST_5(){
        testGame = new Game();
        testGame.initializePlayers();
        testGame.initializeDecks();

        testGame.adventureDeck.swap(0, 99);
        testGame.adventureDeck.swap(20, 95);
        testGame.distributeCards();

        // set up player 1's hand
        // test invalid input (repeated weapon cards)
        Scanner input = new Scanner("quit\n1\nquit\n1\nquit\n");
        StringWriter output = new StringWriter();

        testGame.sponsorQuest(input, new PrintWriter(output), "Q2", testGame.PLAYER_1);
        assertTrue(output.toString().contains("A stage cannot be empty"));
    }

    @Test
    void RESP_16_TEST_1(){
        testGame = new Game();
        testGame.initializePlayers();
        // not interacting with deck

        Scanner input = new Scanner("\n");
        StringWriter output = new StringWriter();

        // set up player hands, treat it as though player 1 was sponsoring the quest
        // Player 2 has 1x F70 and 1x D5 (available weapon power is 5)
        testGame.PLAYER_2.add("F70");
        testGame.PLAYER_2.add("D5");
        // Player 3 has S10 and E30 (available weapon power is 40)
        testGame.PLAYER_3.add("S10");
        testGame.PLAYER_3.add("E30");
        // Player 4 has 6x S10 and 6x H10 (available weapon power is 20)
        for(int i = 0; i < 6; i++){
            testGame.PLAYER_4.add("S10");
            testGame.PLAYER_4.add("H10");
        }

        // set up as though the current stage has [F20, S10, H10], total value is 40
        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        ArrayList<Integer> eligible = testGame.getEligibleAttackers(input, new PrintWriter(output), stage, testGame.PLAYER_1);
        assertEquals(1, eligible.size());
        assertTrue(eligible.contains(3));
    }

    @Test
    void RESP_17_TEST_1(){
        testGame = new Game();

        testGame.initializePlayers();
        testGame.initializeDecks();
        testGame.distributeCards();

        Scanner input = new Scanner("y\n1\n1\n5\nquit\n");
        StringWriter output = new StringWriter();

        // game prompts player 2 to attack. They accept and select D10, S10, H10
        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        testGame.setAttack(input, new PrintWriter(output), stage, testGame.PLAYER_2);

        assertTrue(output.toString().contains("Attacking with [D5, S10, H10]"));
    }

    @Test
    void RESP_18_TEST_1(){
        testGame = new Game();

        testGame.initializePlayers();
        testGame.initializeDecks();
        testGame.distributeCards();

        // test invalid input (repeated weapon)
        Scanner input = new Scanner("y\n2\n2\nquit\n");
        StringWriter output = new StringWriter();

        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        testGame.setAttack(input, new PrintWriter(output), stage, testGame.PLAYER_2);

        assertTrue(output.toString().contains("You may not repeat weapon cards"));
        assertTrue(output.toString().contains("Attacking with [S10]"));
    }

    @Test
    void RESP_18_TEST_2(){
        testGame = new Game();

        testGame.initializePlayers();
        testGame.initializeDecks();
        testGame.adventureDeck.swap(0, 98);
        testGame.distributeCards();

        // test invalid input (foe card)
        Scanner input = new Scanner("y\n1\n3\nquit\n");
        StringWriter output = new StringWriter();

        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        testGame.setAttack(input, new PrintWriter(output), stage, testGame.PLAYER_2);

        assertTrue(output.toString().contains("You may not use a foe card"));
        assertTrue(output.toString().contains("Attacking with [S10]"));
    }

    @Test
    void RESP_19_TEST_1(){
        testGame = new Game();

        testGame.initializePlayers();
        testGame.initializeDecks();
        testGame.distributeCards();

        // test successful attack
        Scanner input = new Scanner("y\n1\n1\n4\n6\n8\nquit\n");
        StringWriter output = new StringWriter();

        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        // attack with
        assertTrue(testGame.setAttack(input, new PrintWriter(output), stage, testGame.PLAYER_2));
    }

    @Test
    void RESP_19_TEST_2(){
        testGame = new Game();

        testGame.initializePlayers();
        testGame.initializeDecks();
        testGame.distributeCards();

        // test empty (unsuccessful attack)
        Scanner input = new Scanner("y\nquit\n");
        StringWriter output = new StringWriter();

        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        // attack with
        assertFalse(testGame.setAttack(input, new PrintWriter(output), stage, testGame.PLAYER_2));
    }

    @Test
    void RESP_19_TEST_3(){
        testGame = new Game();

        testGame.initializePlayers();
        testGame.initializeDecks();
        testGame.distributeCards();

        // test insufficient attack
        Scanner input = new Scanner("y\n1\nquit\n");
        StringWriter output = new StringWriter();

        ArrayList<String> stage = new ArrayList<String>();
        stage.add("F20");
        stage.add("S10");
        stage.add("H10");
        // attack with
        assertFalse(testGame.setAttack(input, new PrintWriter(output), stage, testGame.PLAYER_2));
    }
}