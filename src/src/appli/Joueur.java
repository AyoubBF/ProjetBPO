package appli;
import java.util.Random;

public class Joueur {
    private static final int VAL_MAX_MAIN = 6;
    private static final int VAL_MAX_PIOCHE = 58;
    private int[] saMain = new int[VAL_MAX_MAIN];
    private int[] saPioche = new int[VAL_MAX_PIOCHE];

    public int[] getsaMain() {
        return saMain;
    }

    public void creerCartes(String[] Deck) {
        for(int i = 0; i<60; i++) {
            Deck[i] = String.format("%02d", i+1);
        }
    }

    public void Piocher(String[] Deck, Joueur j, int nombreDePioches) {
        for( int a = 0; a < nombreDePioches; a++){
            Random rand = new Random();
            System.out.println(Deck[rand.nextInt(Deck.length)]);
        }
    }
}
