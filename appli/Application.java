package appli;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        Plateau p = new Plateau();

        Joueur NORD = new Joueur("NORD");
        System.out.println(NORD.getNomJoueur());
        Joueur SUD = new Joueur("SUD");
        System.out.println(NORD.getNomJoueur());

        NORD.creerCartes(NORD);
        SUD.creerCartes(SUD);

        NORD.Piocher(NORD,6);
        System.out.println("----------------");
        SUD.Piocher(SUD,6);

        p.afficherStatutPlateau(NORD, SUD);
        NORD.Poser(NORD, p);
    }
}