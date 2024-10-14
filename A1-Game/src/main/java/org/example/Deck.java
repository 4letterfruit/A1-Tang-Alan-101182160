package org.example;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<String> cardList;
    ArrayList<String> discardPile;
    // receive a list of cards in form of "Card/Count", separated by commas
    public Deck(String cardString){
        cardList = new ArrayList<String>();
        discardPile = new ArrayList<String>();

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

    // get the element of cardList at index i
    public String get(int i){
        return cardList.get(i);
    }

    // swap 2 elements within cardList
    public void swap(int a, int b){
        String temp = get(a);
        cardList.set(a, get(b));
        cardList.set(b, temp);
    }

    // randomly swap elements within cardList 100 times
    public void shuffle(){
        for(int i = 0; i < 100; i++){
            int a = (int) (Math.random()*size());
            int b = (int) (Math.random()*size());

            swap(a, b);
        }
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

    // retrieve the card at the top of the stack
    public String draw(){
        if (size() == 0){
            reshuffle();
        }
        return cardList.removeLast();
    }

    public void reshuffle(){
        while (!discardPile.isEmpty()){
            cardList.add(discardPile.removeLast());
        }
        shuffle();
    }

    public void discard(String card){
        discardPile.add(card);
    }
}