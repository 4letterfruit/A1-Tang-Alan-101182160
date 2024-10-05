package org.example;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    int id;
    ArrayList<String> hand;

    public Player(int id){
        hand = new ArrayList<String>();
        this.id = id;
    }

    public void add(String item){
        hand.add(item);
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
}
