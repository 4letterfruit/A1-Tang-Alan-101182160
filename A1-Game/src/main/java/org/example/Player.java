package org.example;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    int id;
    ArrayList<String> hand;
    int shields;

    HashMap<String, Integer> cardValue;

    public Player(int id){
        hand = new ArrayList<String>();
        this.id = id;

        cardValue = new HashMap<String, Integer>();
        cardValue.put("F5", 5);
        cardValue.put("F10", 10);
        cardValue.put("F15", 15);
        cardValue.put("F20", 20);
        cardValue.put("F25", 25);
        cardValue.put("F30", 30);
        cardValue.put("F35", 35);
        cardValue.put("F40", 40);
        cardValue.put("F50", 50);
        cardValue.put("F70", 70);

        cardValue.put("D5", 105);
        cardValue.put("S10", 110);
        cardValue.put("H10", 111);
        cardValue.put("B15", 115);
        cardValue.put("L20", 120);
        cardValue.put("E30", 130);

    }

    public void add(String item){
        hand.add(item);
        sortHand();
    }

    public String remove(int i){
        if (i < 1 || i > hand.size()){
            return null;
        }
        return hand.remove(i-1);
    }

    public int handSize(){
        return hand.size();
    }

    public int cardCount(String name){
        int count = 0;
        for (String s : hand){
            if (s.equals(name)){
                count++;
            }
        }

        return count;
    }

    // simple selection sort
    public void sortHand(){
        for (int i = 0; i < hand.size(); i++){
            int min = i;
            for (int j = i; j < hand.size(); j++){
                if (cardValue.get(hand.get(j)) < cardValue.get(hand.get(min))){
                    min = j;
                }
            }

            // index i gets lowest value from i - hand.size()
            String temp = hand.get(i);
            hand.set(i, hand.get(min));
            hand.set(min, temp);
        }
    }

    public void addShields(int gain){
        shields += gain;
    }

    public void decreaseShields(int dec){
        shields -= dec;
        if (shields < 0){
            shields = 0;
        }
    }

    public int getShields(){
        return shields;
    }
}
