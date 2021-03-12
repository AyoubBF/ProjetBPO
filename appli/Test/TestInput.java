package appli.Test;

import static org.junit.Assert.*;

import appli.jeu.Hand;
import appli.jeu.Input;
import org.junit.Test;

public class TestInput {
    @Test
    public void testAllIsValid() {
        Hand hand = new Hand();
        hand.add(16);
        hand.add(18);
        hand.add(20);
        hand.add(50);
        String[] s = {"16^", "18^'", "20v", "50^"};
        assertTrue(Input.allIsValid(s, hand));
    }
}
