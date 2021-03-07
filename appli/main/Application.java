package appli.main;
import appli.jeu.Joueur;
import appli.jeu.Plateau;
import appli.jeu.Partie;

public class Application {
    public static void main(String[] args) {
        Partie partie = new Partie();
        Plateau p = new Plateau();

        Joueur NORD = new Joueur("NORD");
        Joueur SUD = new Joueur("SUD");

        NORD.creerCartes();
        SUD.creerCartes();

        NORD.Piocher(6);
        SUD.Piocher(6);

        while(!partie.Vainqueur(NORD) || !partie.Vainqueur(SUD)) {
            p.afficherStatutPlateau(NORD, SUD);
            NORD.Poser(p, partie);
            p.afficherStatutPlateau(NORD, SUD);
            SUD.Poser(p, partie);
        }
    }
}