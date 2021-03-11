package appli.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Integer>{
    public void initialize() {
        for(int i = 2; i<60; i++) {
            this.add(i);
        }
        Collections.shuffle(this);
    }
}
