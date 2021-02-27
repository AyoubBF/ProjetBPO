package appli;

import java.util.ArrayList;

public class Plateau {
    private int pileAscNORD;
    private int pileDescNORD;
    private int pileAscSUD;
    private int pileDescSUD;

    public Plateau(){
        this.pileAscNORD = 1;
        this.pileDescNORD = 60;
        this.pileAscSUD = 1;
        this.pileDescSUD = 60;
    }

    public int getPileAscNORD(){
        return this.pileAscNORD;
    }

    public int getPileDescNORD(){
        return this.pileDescNORD;
    }

    public void afficherStatutPlateau(Joueur j1, Joueur j2){
        System.out.println(j1.getNomJoueur()+" ^["+this.pileAscNORD+"] v["+this.pileDescNORD+"] (m"+j1.getTailleMain()+"p"+j1.getTailleDeck()+")");
        System.out.println(j2.getNomJoueur()+"  ^["+this.pileAscSUD+"] v["+this.pileDescSUD+"] (m"+j2.getTailleMain()+"p"+j2.getTailleDeck()+")");
    }
}
