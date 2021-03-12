package appli.Test;

import static org.junit.Assert.*;

import appli.jeu.Board;
import appli.jeu.Deck;
import appli.jeu.Hand;
import appli.jeu.Player;
import org.junit.jupiter.api.Test;

public class TestPlayer {
    @Test
    public void testCheckIfCanPlay(){
        Player player1 = new Player("NORD");
        Player player2 = new Player("SUD");
        Board board = new Board(player1, player2);
        Hand hand = player1.getHand();
        hand.add(12);
        hand.add(30);
        assertTrue(player1.checkIfCanPlay(hand, board, player1));
        hand.remove(1);
        assertFalse(player1.checkIfCanPlay(hand, board, player1));
    }

    @Test
    public void testCheckIfFinished(){
        Player player = new Player("NORD");
        Hand hand = new Hand();
        Deck deck = new Deck();
        hand.add(12);
        deck.add(15);
        assertFalse(player.checkIfFinished(hand, deck));
        hand.remove(0);
        assertFalse(player.checkIfFinished(hand, deck));
        deck.remove(0);
        assertTrue(player.checkIfFinished(hand, deck));
    }
}
