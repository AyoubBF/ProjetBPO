package test;

import static org.junit.Assert.*;

import game.Board;
import game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestPlayer {
    @Test
    public void testCheckIfCanPlay(){
        Player player1 = new Player("NORD");
        Player player2 = new Player("SUD");
        Board board = new Board(player1, player2);
        ArrayList<Integer> hand = player1.getHand();
        hand.add(12);
        hand.add(30);
        assertTrue(player1.checkIfCanPlay(hand, board, player1));
        hand.remove(1);
        assertFalse(player1.checkIfCanPlay(hand, board, player1));
    }

    @Test
    public void testCheckIfFinished(){
        Player player = new Player("NORD");
        ArrayList<Integer> hand = new ArrayList<Integer>();
        ArrayList<Integer> deck = new ArrayList<Integer>();
        hand.add(12);
        deck.add(15);
        assertFalse(player.checkIfFinished());
        hand.remove(0);
        assertFalse(player.checkIfFinished());
        deck.remove(0);
        assertTrue(player.checkIfFinished());
    }
}
