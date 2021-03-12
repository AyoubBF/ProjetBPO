package appli.Test;

import static org.junit.Assert.*;

import appli.jeu.*;
import org.junit.Test;

public class TestRules {
    @Test
    public void testCheckIfValid() {
        int cardValue = 22;
        Player player = new Player("NORD");
        Hand hand = new Hand();
        hand.add(12);
        hand.add(22);
        player.setHand(hand);
        Pile pile = new Pile(player, 41, "asc");
        assertFalse(Rules.checkIfValid(cardValue, pile, false));
    }

    @Test
    public void testOnlyOneEnemyMove(){
        String[] s1 = {"16^", "18^'", "20^'", "50^"};
        String[] s2 = {"16^", "18^'", "46v", "20v", "50^", "58^"};
        assertFalse(Rules.onlyOneEnemyMove(s1));
        assertTrue(Rules.onlyOneEnemyMove(s2));
    }

    @Test
    public void testRequiredDraws(){
        String[] s1 = {"16^", "18^'", "46v", "20v", "50^", "58^"};
        String[] s2 = {"16^", "18^'", "20^", "50^"};
        String[] s3 = {"16^", "18^", "20^", "50^"};
        Hand hand1 = new Hand();
        Hand hand2 = new Hand();
        hand2.add(56);
        hand2.add(36);
        assertEquals(6, Rules.requiredDraws(s1, hand1));
        assertEquals(4, Rules.requiredDraws(s2, hand2));
        assertEquals(2, Rules.requiredDraws(s3, hand2));
    }

    @Test
    public void testIsPlayable(){
        int cardValue = 50;
        Player player1 = new Player("NORD");
        Player player2 = new Player("SUD");
        Board board = new Board(player1, player2);
        assertTrue(Rules.isPlayable(cardValue, board, player1));
        int ascendingNord = board.getAscendingNord().getActualValue();
        int ascendingSud = board.getAscendingSud().getActualValue();
        int descendingNord = board.getDescendingNord().getActualValue();
        int descendingSud = board.getDescendingSud().getActualValue();
        ascendingNord = 60;
        ascendingSud = 40;
        descendingNord = 40;
        descendingSud = 60;
        assertFalse(Rules.isPlayable(cardValue, board, player1));
    }
}
