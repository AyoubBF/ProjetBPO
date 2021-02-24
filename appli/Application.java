package appli;

public class Application {
    public static void main(String[] args) {
        Plateau p = new Plateau();
        Joueur NORD = new Joueur();
        Joueur SUD = new Joueur();
        int[] DeckNORD = new int[60];
        int[] DeckSUD = new int[60];

        NORD.creerCartes(DeckNORD);
        SUD.creerCartes(DeckSUD);

        NORD.Piocher(DeckNORD,NORD,6);
        SUD.Piocher(DeckSUD,SUD,6);
    }
}