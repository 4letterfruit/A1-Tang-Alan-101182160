package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
}