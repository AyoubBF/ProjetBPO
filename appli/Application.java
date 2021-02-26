package appli;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        Plateau p = new Plateau();

        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");

        NORD.creerCartes();
        SUD.creerCartes();

        NORD.Piocher(6);
        System.out.println("----------------");
        SUD.Piocher(6);

        p.afficherStatutPlateau(NORD, SUD);
        NORD.Poser(p);
    }
}