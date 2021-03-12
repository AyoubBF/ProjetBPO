package appli.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Integer> {
    /**
     * @brief initialise un deck de 60 cartes
     */
    public void initialize() {
        for (int i = 2; i < 60; i++) {
            this.add(i);
        }
        Collections.shuffle(this);
    }
}
