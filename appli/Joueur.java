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

    public void retirerCartes(String[] cartesSelectionnes){
        for (String carte : cartesSelectionnes) {
            for (int i = 0; i<saMain.size();i++) {
                if(saMain.get(i) == Integer.parseInt(carte.substring(0, 2))){
                    saMain.remove(i);
                }
            }
        }
    }

    public boolean comparerAvecCarteAscPrecedente(String carteActuelle, int cartePrecedente){
        if(Integer.parseInt(carteActuelle.substring(0, 2)) > cartePrecedente) {
            return true;
        }
        return false;
    }

    public boolean comparerAvecCarteDescPrecedente(String carteActuelle, int cartePrecedente){
        if(Integer.parseInt(carteActuelle.substring(0, 2)) < cartePrecedente) {
            return true;
        }
        return false;
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

    private boolean verifierSelection(String[] cartesSelectionnes, int nombreDeCartes, Plateau p) {
        int cartesValides = 0;
        int limitePoseEnnemie = 0;
        int carteAscPrecedente;
        int carteDescPrecedente;
        int carteAscEnmPrecedente;
        int carteDescEnmPrecedente;
        if(this.nomJoueur == "NORD"){
            carteAscPrecedente = p.getPileAscNORD();
            carteDescPrecedente = p.getPileDescNORD();
            carteAscEnmPrecedente = p.getPileAscSUD();
            carteDescEnmPrecedente = p.getPileDescSUD();
        }else{
            carteAscPrecedente = p.getPileAscSUD();
            carteDescPrecedente = p.getPileDescSUD();
            carteAscEnmPrecedente = p.getPileAscNORD();
            carteDescEnmPrecedente = p.getPileDescNORD();
        }

        for (String carte : cartesSelectionnes) {
            if (carte.length() <= 2) {return false;}
            if (carte.length() == 3) {
                if (isNumeric(carte.substring(0, 2)) && this.saMain.contains(Integer.parseInt(carte.substring(0, 2)))) {
                    if (carte.substring(2, 3).matches("\\^")) {
                        if(this.nomJoueur == "NORD"){
                            if(!p.poseValideAscNORD(carte.substring(0,2))) {
                                return false;
                            }
                            if (comparerAvecCarteAscPrecedente(carte, carteAscPrecedente)) {
                                carteAscPrecedente = Integer.parseInt(carte.substring(0, 2));
                                cartesValides++;
                            }
                        }else{
                            if(!p.poseValideAscSUD(carte.substring(0,2))){
                                return false;
                            }
                            if(comparerAvecCarteAscPrecedente(carte, carteAscPrecedente)) {
                                carteAscPrecedente = Integer.parseInt(carte.substring(0, 2));
                                cartesValides++;
                            }
                        }
                    }
                    if (carte.substring(2, 3).matches("v")) {
                        if(this.nomJoueur == "NORD"){
                            if(!p.poseValideDescNORD(carte.substring(0,2))) {
                                return false;
                            }
                            if(comparerAvecCarteDescPrecedente(carte, carteDescPrecedente)) {
                                carteDescPrecedente = Integer.parseInt(carte.substring(0, 2));
                                cartesValides++;
                            }
                        }else{
                            if(!p.poseValideDescSUD(carte.substring(0,2))){
                                return false;
                            }
                            if(comparerAvecCarteDescPrecedente(carte, carteDescPrecedente)) {
                                carteDescPrecedente = Integer.parseInt(carte.substring(0, 2));
                                cartesValides++;
                            }
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
                                if(comparerAvecCarteAscPrecedente(carte, carteAscEnmPrecedente)) {
                                    carteAscEnmPrecedente = Integer.parseInt(carte.substring(0, 2));
                                    cartesValides++;
                                }
                            }else{
                                if(!p.poseValideAscSUD(carte.substring(0,2))){
                                    return false;
                                }
                                if(comparerAvecCarteAscPrecedente(carte, carteAscEnmPrecedente)) {
                                    carteAscEnmPrecedente = Integer.parseInt(carte.substring(0, 2));
                                    cartesValides++;
                                }
                            }
                            limitePoseEnnemie++;
                            if(limitePoseEnnemie > 1){
                                return false;
                            }
                        }
                    }
                    if (carte.substring(2, 3).matches("v")) {
                        if (carte.substring(3, 4).matches("'")) {
                            if(this.nomJoueur == "NORD"){
                                if(!p.poseValideDescNORD(carte.substring(0,2))) {
                                    return false;
                                }
                                if(comparerAvecCarteDescPrecedente(carte, carteDescEnmPrecedente)) {
                                    carteDescEnmPrecedente = Integer.parseInt(carte.substring(0, 2));
                                    cartesValides++;
                                }
                            }else{
                                if(!p.poseValideDescSUD(carte.substring(0,2))){
                                    return false;
                                }
                                if(comparerAvecCarteDescPrecedente(carte, carteDescEnmPrecedente)) {
                                    carteDescEnmPrecedente = Integer.parseInt(carte.substring(0, 2));
                                    cartesValides++;
                                }
                            }
                            limitePoseEnnemie++;
                            if(limitePoseEnnemie > 1){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("nombre de cartes valides : "+cartesValides);
        System.out.println("nombre de cartes : "+nombreDeCartes);
        System.out.println(carteAscPrecedente +" "+ carteDescPrecedente +" "+ carteAscEnmPrecedente +" "+ carteDescEnmPrecedente);
        if(cartesValides == nombreDeCartes) {
            if(this.nomJoueur == "NORD") {
                p.miseAJourValeursPlateauVueNORD(carteAscPrecedente, carteDescPrecedente, carteAscEnmPrecedente, carteDescEnmPrecedente);
                retirerCartes(cartesSelectionnes);
            }else{
                p.miseAJourValeursPlateauVueSUD(carteAscPrecedente, carteDescPrecedente, carteAscEnmPrecedente, carteDescEnmPrecedente);
                retirerCartes(cartesSelectionnes);
            }
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
            for( int a = 0; a < saMain.size(); a++){
                System.out.print(String.format("%02d ", this.saMain.get(a)));
            }
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
