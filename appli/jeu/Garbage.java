//package appli.jeu;
//
//import java.util.Random;
//import java.util.ArrayList;
//import java.util.Scanner;
//import java.util.Collections;
//
//public class Joueur {
//    private static final int VAL_MAX_MAIN = 6;
//    private ArrayList<Integer> saMain = new ArrayList<>(VAL_MAX_MAIN);
//    private String nomJoueur;
//    private ArrayList<Integer> Deck = new ArrayList<>();
//    private int tailleMain = 0;
//    private int tailleDeck = 58;
//    private int nombreDeCartesPiochees = 0;
//    private int pileAsc;
//    private int pileDesc;
//
//    public Joueur(String nomJoueur){
//        this.nomJoueur = nomJoueur;
//        this.pileAsc = 1;
//        this.pileDesc = 60;
//    }
//
//    public int getPileAsc(){
//        return this.pileAsc;
//    }
//
//    public int getPileDesc(){
//        return this.pileDesc;
//    }
//
//    public ArrayList<Integer> getSaMain(){
//        return this.saMain;
//    }
//
//    public ArrayList<Integer> getDeck(){
//        return this.Deck;
//    }
//
//    public String getNomJoueur() {
//        return this.nomJoueur;
//    }
//
//    public int getTailleMain(){
//        return this.tailleMain;
//    }
//
//    public int getTailleDeck(){
//        return this.tailleDeck;
//    }
//
//    public ArrayList<Integer> creerCartes() {
//        for(int i = 2; i<60; i++) {
//            this.Deck.add(i);
//        }
//        return Deck;
//    }
//
//    public ArrayList<Integer> Piocher(int nombreDePioches) {
//        for( int a = 0; a < nombreDePioches; a++){
//            Random rand = new Random();
//            this.saMain.add(a, Deck.get(2+rand.nextInt(tailleDeck-2)));
//            Deck.remove(this.saMain.get(a));
//            tailleDeck--;
//            tailleMain++;
//            nombreDeCartesPiochees = nombreDePioches;
//        }
//        return Deck;
//    }
//
//    public void retirerCartes(String[] cartesSelectionnes){
//        for (String carte : cartesSelectionnes) {
//            for (int i = 0; i<saMain.size();i++) {
//                if(saMain.get(i) == Integer.parseInt(carte.substring(0, 2))){
//                    saMain.remove(i);
//                    tailleMain--;
//                }
//            }
//        }
//    }
//
//    public boolean comparerAvecCarteAscPrecedente(String carteActuelle, int cartePrecedente){
//        if(Integer.parseInt(carteActuelle.substring(0, 2)) > cartePrecedente) {
//            return true;
//        }
//        return false;
//    }
//
//    public boolean comparerAvecCarteDescPrecedente(String carteActuelle, int cartePrecedente){
//        if(Integer.parseInt(carteActuelle.substring(0, 2)) < cartePrecedente) {
//            return true;
//        }
//        return false;
//    }
//
//    public void trierSaMain(){
//        Collections.sort(this.saMain);
//        System.out.print("cartes "+this.nomJoueur+" { ");
//        for(int i=0; i<this.tailleMain;i++){
//            System.out.print(String.format("%02d ", this.saMain.get(i)));
//        }
//        System.out.println("}");
//    }
//
//
//    private static boolean isNumeric(String str) {
//        try {
//            Integer.parseInt(str);
//            return true;
//        }
//        catch(NumberFormatException e){
//            return false;
//        }
//    }
//
//    public boolean poseValideAsc(String s){
//        if(Integer.parseInt(s) > this.pileAsc || Integer.parseInt(s) == (this.pileAsc - 10)){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean poseValideDesc(String s){
//        if(Integer.parseInt(s) < this.pileDesc || Integer.parseInt(s) == (this.pileDesc + 10)){
//            return true;
//        }
//        return false;
//    }
//
//    private boolean poseDansSaPile(String carte, int cartesValides, int carteAscPrecedente, int carteDescPrecedente){
//        if (isNumeric(carte.substring(0, 2)) && this.saMain.contains(Integer.parseInt(carte.substring(0, 2)))) {
//            if (carte.substring(2, 3).matches("\\^")) {
//                if(!poseValideAsc(carte.substring(0,2))) {
//                    return false;
//                }
//                if (comparerAvecCarteAscPrecedente(carte, pileAsc)) {
//                    carteAscPrecedente = Integer.parseInt(carte.substring(0, 2)); //POSSIBLE ERREUR (IL FAUDRAIT METTRE UNE VARIABLE CARTE PRECEDENTE COMME AVANT
//                    cartesValides++;
//                }
//            }
//            if (carte.substring(2, 3).matches("v")) {
//                if(!poseValideDesc(carte.substring(0,2))) {
//                    return false;
//                }
//                if (comparerAvecCarteDescPrecedente(carte, pileDesc)) {
//                    carteDescPrecedente = Integer.parseInt(carte.substring(0, 2)); //POSSIBLE ERREUR (IL FAUDRAIT METTRE UNE VARIABLE CARTE PRECEDENTE COMME AVANT
//                    cartesValides++;
//                }
//            }
//        }
//        return true;
//    }
//
//    private boolean poseDansLaPileEnnemie(String carte, int cartesValides, Player ennemi , int limitePoseEnnemie, int carteAscEnmPrecedente, int carteDescEnmPrecedente){
//        if (isNumeric(carte.substring(0, 2)) && this.saMain.contains(Integer.parseInt(carte.substring(0, 2)))) {
//            if (carte.substring(2, 3).matches("\\^")) {
//                if (carte.substring(3, 4).matches("'")) {
//                    if (!poseValideAsc(carte.substring(0, 2))) {
//                        return false;
//                    }
//                    if (comparerAvecCarteAscPrecedente(carte, ennemi.pileAsc)) {
//                        carteAscEnmPrecedente = Integer.parseInt(carte.substring(0, 2)); //POSSIBLE ERREUR (IL FAUDRAIT METTRE UNE VARIABLE CARTE PRECEDENTE COMME AVANT
//                        cartesValides++;
//                    }
//                    limitePoseEnnemie++;
//                    if (limitePoseEnnemie > 1) {
//                        return false;
//                    }
//                }
//            }
//            if (carte.substring(2, 3).matches("v")) {
//                if (carte.substring(3, 4).matches("'")) {
//                    if (!poseValideDesc(carte.substring(0, 2))) {
//                        return false;
//                    }
//                    if (comparerAvecCarteDescPrecedente(carte, ennemi.pileDesc)) {
//                        carteDescEnmPrecedente = Integer.parseInt(carte.substring(0, 2)); //POSSIBLE ERREUR (IL FAUDRAIT METTRE UNE VARIABLE CARTE PRECEDENTE COMME AVANT
//                        cartesValides++;
//                    }
//                }
//                limitePoseEnnemie++;
//                if (limitePoseEnnemie > 1) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    private boolean verifierSelection(String[] cartesSelectionnes, int nombreDeCartes, Player ennemi, Partie partie) {
//        int cartesValides = 0;
//        int limitePoseEnnemie = 0;
//        int carteAscPrecedente = this.pileAsc;
//        int carteDescPrecedente = this.pileDesc;
//        int carteAscEnmPrecedente = ennemi.pileAsc;
//        int carteDescEnmPrecedente = ennemi.pileDesc;
//        for (String carte : cartesSelectionnes) {
//            if (cartesSelectionnes.length == 3) {
//                if (!poseDansSaPile(carte, cartesValides, carteAscPrecedente, carteDescPrecedente)) {
//                    return false;
//                }
//            }
//            if (carte.length() == 4) {
//                if (!poseDansLaPileEnnemie(carte, cartesValides, ennemi, limitePoseEnnemie, carteAscEnmPrecedente, carteDescEnmPrecedente)) {
//                    return false;
//                }
//            }
//        }
//        if(cartesValides == nombreDeCartes) {
//            p.miseAJourValeursPlateau(carteAscPrecedente, carteDescPrecedente, carteAscEnmPrecedente, carteDescEnmPrecedente);
//            retirerCartes(cartesSelectionnes);
//            if(limitePoseEnnemie == 0) {
//                if(saMain.size() == 5){
//                    partie.Vainqueur(this);
//                    Piocher(1);
//                }else{
//                    partie.Vainqueur(this);
//                    Piocher(2);
//                }
//            }
//            if(limitePoseEnnemie == 1){
//                Piocher(6 - saMain.size());
//            }
//            return true;
//        }
//        return false;
//    }
//
//    private boolean peutJouer(Player ennemi){
//        int cartesJouables = 0;
//        for(int i = 0; i<saMain.size(); i++){
//            if(saMain.get(i) > this.pileAsc || saMain.get(i) > ennemi.pileAsc || saMain.get(i) < this.pileDesc || saMain.get(i) < ennemi.pileDesc){
//                cartesJouables++;
//            }
//            if(cartesJouables >= 2){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean décompose(String s, String[] cartesSelectionnes, Player ennemi, Partie partie) {
//        cartesSelectionnes = s.split("\\s+");
//        if(cartesSelectionnes.length > 6 || cartesSelectionnes.length < 2){
//            return false;
//        }
//        if(this.verifierSelection(cartesSelectionnes, cartesSelectionnes.length,ennemi ,partie)){
//            System.out.println(cartesSelectionnes.length+" cartes posées, "+nombreDeCartesPiochees+" cartes piochées");
//            return true;
//        }
//        return false;
//    }
//
//    public void Poser(Partie partie, Player ennemi){
//        trierSaMain();
//        int boucleInfinie = 0;
//        if(!peutJouer(ennemi)){
//            partie.PartieFinie(this);
//        }
//        Scanner sc = new Scanner(System.in);
//        String s;
//        System.out.print("> ");
//        s = sc.nextLine();
//        String[] cartesSelectionnes = new String[6];
//        while (boucleInfinie == 0) {
//            nombreDeCartesPiochees = 0;
//            if(décompose(s, cartesSelectionnes, ennemi, partie)){
//                return;
//            }
//            System.out.print("#> ");
//            s = sc.nextLine();
//        }
//    }
//}
