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

//    public boolean PoseValideNORDASC(Plateau p, String s){
//        if(saMain.contains(s) || Integer.parseInt(s) > p.getPileAscNORD() || Integer.parseInt(s) == (p.getPileAscNORD() - 10)){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean PoseValideNORDDESC(Plateau p, String s){
//        if(saMain.contains(s) || Integer.parseInt(s) < p.getPileAscNORD() || Integer.parseInt(s) == (p.getPileAscNORD() + 10)){
//            return true;
//        }
//        return false;
//    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    private static boolean formatValide(String[] cartesSelectionnes) {
        for (String carte : cartesSelectionnes) {
            if (carte.length() <= 2)
//                return false;

            if (carte.length() == 3) {
                if (isNumeric(carte.substring(0, 2))) {
                    if (carte.substring(2, 3).matches("\\^") || carte.substring(2, 3).matches("v")) {
//                        return true; // ici c'est faux pcq il faut que ce soit true pour tous les éléments (ici dès que un élément return true ça compte comme si tout était true)
                    }
                }
            }
            if (carte.length() == 4) {
                if (isNumeric(carte.substring(0, 2))) {
                    if (carte.substring(2, 3).matches("\\^") || carte.substring(2, 3).matches("v")) {
                        if (carte.substring(3, 4).matches("'")) {
//                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static int décompose(String s, String[] cartesSelectionnes) {
        cartesSelectionnes = s.split("\\s+");
        int nombreDeCartes = cartesSelectionnes.length;
        if(nombreDeCartes > 6 || nombreDeCartes == 0){
            return -1;
        }
        if(formatValide(cartesSelectionnes)){
                System.out.println("X cartes posées, X cartes piochées");
        }
        return nombreDeCartes;
    }

    public void Poser(Plateau p){
        trierSaMain();
        Scanner sc = new Scanner(System.in);
        String s;
        System.out.print("> ");
        s = sc.nextLine();
        String[] cartesSelectionnes = new String[6];
        while (!s.equals("fin")) {
            int nombreDeCartes = décompose(s, cartesSelectionnes);
            System.out.print("#> ");
            s = sc.nextLine();
        }
    }
}
