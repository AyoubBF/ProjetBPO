package appli.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private String name;
    private Deck deck;
    private Hand hand;

    public Player(String name){
        this.name = name;
        this.deck = new Deck();
        this.deck.initialize();
        this.hand = new Hand();
    }

    public void draw(int number){
        for( int i = 0; i < number; i++) {
            hand.add(deck.get(i));
            deck.remove(i);
        }
        Collections.sort(this.hand);
    }

    public void playCard(int cardValue, Pile target){
        int cardIndex = hand.indexOf(cardValue);
        hand.remove(cardIndex);
        target.setActualValue(cardValue);
    }

    public Deck getDeck(){
        return this.deck;
    }

    public Hand getHand(){
        return this.hand;
    }

    public String getName(){
        return this.name;
    }
}
