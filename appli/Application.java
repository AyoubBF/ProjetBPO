package appli;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        Plateau p = new Plateau();

        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");

        NORD.creerCartes(NORD);
        SUD.creerCartes(SUD);

        NORD.Piocher(NORD,6);
        SUD.Piocher(SUD,6);
        
        NORD.Poser(NORD, p);
    }
}