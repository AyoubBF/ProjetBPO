package appli;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Joueur {
    private static final int VAL_MAX_MAIN = 6;
    private ArrayList<Integer> saMain = new ArrayList<>(VAL_MAX_MAIN);
    private String nomJoueur;
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

    public ArrayList<Integer> creerCartes() {
        for(int i = 2; i<60; i++) {
            this.Deck.add(i);
        }
        return Deck;
    }

    public ArrayList<Integer> Piocher(int nombreDePioches) {
        for( int a = 0; a < nombreDePioches; a++){
            Random rand = new Random();
            this.saMain.add(a, Deck.get(2+rand.nextInt(tailleDeck-2)));
            Deck.remove(this.saMain.get(a));
            tailleDeck--;
            tailleMain++;
           System.out.println(String.format("%02d", this.saMain.get(a)));
        }
        return Deck;
    }

    private void trierSaMain(){
        Collections.sort(this.saMain);
        System.out.print("cartes "+this.nomJoueur+" { ");
        for(int i=0; i<this.tailleMain;i++){
            System.out.print(String.format("%02d ", this.saMain.get(i)));
        }
        System.out.println("}");
    }

    public boolean PoseValideNORDASC(Plateau p, String s){
        if(saMain.contains(s) || Integer.parseInt(s) > p.getPileAscNORD() || Integer.parseInt(s) == (p.getPileAscNORD() - 10)){
            return true;
        }
        return false;
    }

    public boolean PoseValideNORDDESC(Plateau p, String s){
        if(saMain.contains(s) || Integer.parseInt(s) < p.getPileAscNORD() || Integer.parseInt(s) == (p.getPileAscNORD() + 10)){
            return true;
        }
        return false;
    }

    private static String[] décompose(String s, String[] tab) {
        tab = s.split("\\s+");
        for (String mot : tab)
            System.out.print(mot+" ");
        return tab;
    }

//    public boolean PoseValide(Plateau p, String s){
//
//        return true;
//    }

    public void Poser(Plateau p){
        trierSaMain();
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.print("> ");
        s = sc.nextLine();
        String[] cartesSelectionnes = new String[6];
        while (!s.equals("fin")) {
            décompose(s, cartesSelectionnes);
            System.out.println();
            System.out.print("> ");
            s = sc.nextLine();
        }
//        if(!PoseValide){
//            System.out.println("erreur de saisie");
//            return;
//        }
    }
}
