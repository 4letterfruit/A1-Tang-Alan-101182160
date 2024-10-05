package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class GameTest {
    Game testGame = new Game();
    @Test
    void RESP_1_TEST_1(){
        // check decks created
        assertNotNull(testGame.adventureDeck);
        assertNotNull(testGame.eventDeck);

        // check card counts
        assertEquals(100, testGame.adventureDeck.size());
        assertEquals(17, testGame.eventDeck.size());
    }

    @Test
    void RESP_1_TEST_2(){
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
        assertFalse(fails>2);
    }

    @Test
    void RESP_2_TEST_3(){
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
}