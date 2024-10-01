package org.example;
import java.util.ArrayList;
public class Deck {
    ArrayList<String> cardList;

    // receive a list of cards in form of "Card/Count", separated by commas
    public Deck(String cardString){
        cardList = new ArrayList<String>();

        String[] splitString = cardString.split(",");

        for (String s : splitString) {
            String[] temp = s.split("/");
            String card = temp[0];
            int count = Integer.parseInt(temp[1]);

            for (int i = 0; i < count; i++){
                cardList.add(card);
            }
        }
    }

    // number of cards in the deck
    public int size(){
        return cardList.size();
    }

    // number of cards with specified name
    public int cardCount(String name){
        int count = 0;
        for (String s : cardList){
            if (s.equals(name)){
                count++;
            }
        }

        return count;
    }
}
