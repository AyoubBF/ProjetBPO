package appli;
import java.util.Random;

public class Joueur {
    private static final int VAL_MAX_MAIN = 6;
    private static final int VAL_MAX_PIOCHE = 58;
    private int[] saMain = new int[VAL_MAX_MAIN];
    private int[] saPioche = new int[VAL_MAX_PIOCHE];

    public int[] creerCartes(int[] Deck) {
        for(int i = 0; i<60; i++) {
            Deck[i] = i;
        }
        return Deck;
    }

    public int[] Piocher(int[] Deck, Joueur j, int nombreDePioches) {
        for( int a = 0; a < nombreDePioches; a++){
            Random rand = new Random();
            Deck[a] = 2+rand.nextInt(59);
            System.out.println(String.format("%02d", Deck[a]));
        }
        return Deck;
    }
}
