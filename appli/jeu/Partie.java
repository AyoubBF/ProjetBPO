package appli.jeu;

public class Partie {
    public static String gagnant = "";

    public static void PartieFinie(Joueur j){
        if(j.getNomJoueur() == "NORD"){
            System.out.print("partie finie, SUD a gagné");
            gagnant = "SUD";
            System.exit(0);
        }
        System.out.print("partie finie, NORD a gagné");
        gagnant = "NORD";
        System.exit(0);
    }

    public boolean Vainqueur(Joueur j){
        if(j.getDeck().isEmpty() && j.getSaMain().isEmpty()){
            System.out.print("partie finie, "+j.getNomJoueur()+" a gagné");
            gagnant = j.getNomJoueur();
            System.exit(0);
        }
        return false;
    }
}

