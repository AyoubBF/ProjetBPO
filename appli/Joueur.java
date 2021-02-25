package appli;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private static final int VAL_MAX_MAIN = 6;
    private ArrayList<Integer> saMain = new ArrayList<>(VAL_MAX_MAIN);
    private static String nomJoueur = new String();
    private ArrayList<Integer> Deck = new ArrayList<>();
    private int tailleDeck = 58;

    public Joueur(String nomJoueur){
        this.nomJoueur = nomJoueur;
    }

    public ArrayList<Integer> getDeck(){
        return this.Deck;
    }

    public ArrayList<Integer> creerCartes(Joueur j) {
        for(int i = 2; i<60; i++) {
            j.Deck.add(i);
        }
        return Deck;
    }

    public ArrayList<Integer> Piocher(Joueur j, int nombreDePioches) {
        for( int a = 0; a < nombreDePioches; a++){
            Random rand = new Random();
            j.saMain.add(a, Deck.get(2+rand.nextInt(tailleDeck-2)));
            Deck.remove(j.saMain.get(a));
            tailleDeck--;
            System.out.println("taille : "+ tailleDeck);
           System.out.println(String.format("%02d", j.saMain.get(a)));
        }
        return Deck;
    }

    private static void décompose(String s) {
        // une solution
        String[] tab = s.split("\\s+");
        for (String mot : tab)
            System.out.println(mot);
    }
    public void Poser(Joueur j, Plateau p){
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.print("> ");
        s = sc.nextLine();
        while (!s.equals("fin")) {
            décompose(s);
            System.out.print("> ");
            s = sc.nextLine();
        }
//        if(!PoseValide){
//            System.out.println("erreur de saisie");
//            return;
//        }
    }
}
