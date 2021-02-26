package appli;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Joueur {
    private static final int VAL_MAX_MAIN = 6;
    private ArrayList<Integer> saMain = new ArrayList<>(VAL_MAX_MAIN);
    private String nomJoueur = new String();
    private ArrayList<Integer> Deck = new ArrayList<>();
    private int tailleMain = 0;
    private int tailleDeck = 58;

    public Joueur(String nomJoueur){
        this.nomJoueur = nomJoueur;
    }

    public ArrayList<Integer> getSaMain(){
        return this.saMain;
    }

    public ArrayList<Integer> getDeck(){
        return this.Deck;
    }

    public String getNomJoueur() {
        return this.nomJoueur;
    }

    public int getTailleMain(){
        return this.tailleMain;
    }

    public int getTailleDeck(){
        return this.tailleDeck;
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
            tailleMain++;
           System.out.println(String.format("%02d", j.saMain.get(a)));
        }
        return Deck;
    }

    private static void trierSaMain(Joueur j){
        Collections.sort(j.saMain);
        System.out.print("cartes "+j.nomJoueur+" { ");
        for(int i=0; i<j.tailleMain;i++){
            System.out.print(String.format("%02d ", j.saMain.get(i)));
        }
        System.out.println("}");
    }

    private static void décompose(String s) {
        // une solution
        String[] tab = s.split("\\s +");
        for (String mot : tab)
            System.out.print(mot);
    }
    public void Poser(Joueur j, Plateau p){
        trierSaMain(j);
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
