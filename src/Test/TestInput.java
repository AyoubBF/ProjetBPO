package test;

import static org.junit.Assert.*;

import rules.Input;
import org.junit.Test;

import java.util.ArrayList;

public class TestInput {
    @Test
    public void testAllIsValid() {
        ArrayList<Integer> hand = new ArrayList<Integer>();
        hand.add(16);
        hand.add(18);
        hand.add(20);
        hand.add(50);
        hand.add(58);
        hand.add(46);
        String[] s1 = {"16^", "18^'", "20v", "50^"};
        String[] s2 = {"50^", "18^'", "20v", "16^"};
        String[] s3 = {"16^", "18^'", "20^'", "50^"};
        String[] s4 = {"16^", "18^'", "46v", "20v", "50^", "58^"};
        String[] s5 = {"16^", "18^'", "18^", "20v", "50^", "58^"};
        assertTrue(Input.allIsValid(s1, hand));
        assertFalse(Input.allIsValid(s2, hand));
        assertFalse(Input.allIsValid(s3, hand));
        assertTrue(Input.allIsValid(s4, hand));
        assertFalse(Input.allIsValid(s5, hand));
    }

    @Test
    public void testCheckIfEnemyMove() {
        String card1 = "50^'";
        String card2 = "50v '";
        String card3 = "50v";
        assertTrue(Input.checkIfEnemyMove(card1));
        assertFalse(Input.checkIfEnemyMove(card2));
        assertFalse(Input.checkIfEnemyMove(card3));
    }
}
