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

    public void trierSaMain(){
        Collections.sort(this.saMain);
        System.out.print("cartes "+this.nomJoueur+" { ");
        for(int i=0; i<this.tailleMain;i++){
            System.out.print(String.format("%02d ", this.saMain.get(i)));
        }
        System.out.println("}");
    }


    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

//    private int validePoseAsc(String carte, String[] cartesSelectionnes, Plateau p){
//        if(this.nomJoueur == "NORD"){
//            if(!p.poseValideAscNORD(carte.substring(0,2))) {
//                return false;
//            }
//            cartesValides++;
//        }else{
//            if(!p.poseValideAscSUD(carte.substring(0,2))){
//                return false;
//            }
//            cartesValides++;
//        }
//    }

    private boolean verifierSelection(String[] cartesSelectionnes, int nombreDeCartes, Plateau p) {
        int cartesValides = 0;
        int limitePoseEnnemie = 0;
        for (String carte : cartesSelectionnes) {
            if (carte.length() <= 2) {return false;}

            if (carte.length() == 3) {
                if (isNumeric(carte.substring(0, 2)) && this.saMain.contains(Integer.parseInt(carte.substring(0, 2)))) {
                    if (carte.substring(2, 3).matches("\\^")) {
                        if(this.nomJoueur == "NORD"){
                            if(!p.poseValideAscNORD(carte.substring(0,2))) {
                                return false;
                            }
                            cartesValides++;
                        }else{
                            if(!p.poseValideAscSUD(carte.substring(0,2))){
                                return false;
                            }
                            cartesValides++;
                        }
                    }
                    if (carte.substring(2, 3).matches("v")) {
                        if(this.nomJoueur == "NORD"){
                            if(!p.poseValideDescNORD(carte.substring(0,2))) {
                                return false;
                            }
                            cartesValides++;
                        }else{
                            if(!p.poseValideDescSUD(carte.substring(0,2))){
                                return false;
                            }
                            cartesValides++;
                        }
                    }
                }
            }
            if (carte.length() == 4) {
                if (isNumeric(carte.substring(0, 2)) && this.saMain.contains(Integer.parseInt(carte.substring(0, 2)))) {
                    if (carte.substring(2, 3).matches("\\^")) {
                        if (carte.substring(3, 4).matches("'")) {
                            if(this.nomJoueur == "NORD"){
                                if(!p.poseValideAscNORD(carte.substring(0,2))) {
                                    return false;
                                }
                                cartesValides++;
                            }else{
                                if(!p.poseValideAscSUD(carte.substring(0,2))){
                                    return false;
                                }
                                cartesValides++;
                            }
                        }
                    }
                    if (carte.substring(2, 3).matches("v")) {
                        if (carte.substring(3, 4).matches("'")) {
                            if(this.nomJoueur == "NORD"){
                                if(!p.poseValideDescNORD(carte.substring(0,2))) {
                                    return false;
                                }
                                cartesValides++;
                            }else{
                                if(!p.poseValideDescSUD(carte.substring(0,2))){
                                    return false;
                                }
                                cartesValides++;
                                limitePoseEnnemie++;
                                if(limitePoseEnnemie > 1){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(cartesValides == nombreDeCartes) {
                return true;
        }
        return false;
    }

    private int décompose(String s, String[] cartesSelectionnes, Plateau p) {
        cartesSelectionnes = s.split("\\s+");
        int nombreDeCartes = cartesSelectionnes.length;
        if(nombreDeCartes > 6 || nombreDeCartes == 0){
            return -1;
        }
        if(this.verifierSelection(cartesSelectionnes, nombreDeCartes, p)){
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
            décompose(s, cartesSelectionnes, p);
            System.out.print("#> ");
            s = sc.nextLine();
        }
    }
}
