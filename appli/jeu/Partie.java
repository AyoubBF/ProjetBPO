package appli.jeu;

public class Partie {
    private static String gagnant = "";

    public static void PartieFinie(Joueur j){
        if(j.getNomJoueur() == "NORD"){
            System.out.print("partie finie, SUD a gagné");
            gagnant = "SUD";
            System.exit(0);
        }else{
            System.out.print("partie finie, NORD a gagné");
            gagnant = "NORD";
            System.exit(0);
        }
    }
}

