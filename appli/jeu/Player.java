package appli.jeu;

import java.util.Collections;

public class Player {
    private String name;
    private Deck deck;
    private Hand hand;

    /**
     *
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.deck = new Deck();
        this.deck.initialize();
        this.hand = new Hand();
    }

    public void draw(int number) {
        for (int i = 0; i < number; i++) {
            hand.add(deck.get(i));
            deck.remove(i);
        }
        Collections.sort(this.hand);
    }

    public boolean checkIfCanPlay(Hand hand, Board board, Player player) {
        boolean canPlay = true;
        int playableCards = 0;

        if (hand.size() == 1) {
            canPlay = false;
        }

        for (int card : hand) {
            if (Rules.isPlayable(card, board, player)) {
                playableCards++;
            }
            if (playableCards >= 2) {
                return canPlay;
            }
        }
        return canPlay;
    }

    public boolean checkIfFinished(Hand hand, Deck deck) {
        return hand.size() == 0 && deck.size() == 0;
    }

    public void playCard(int cardValue, Pile target) {
        int cardIndex = hand.indexOf(cardValue);
        hand.remove(cardIndex);
        target.setActualValue(cardValue);
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Hand getHand() {
        return this.hand;
    }

    public void setHand(Hand hand){
        this.hand = hand;
    }

    public String getName() {
        return this.name;
    }
}
