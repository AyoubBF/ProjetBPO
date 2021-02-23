package appli;

public class Application {
    public static void main(String[] args) {
        Joueur NORD = new Joueur();
        Joueur SUD = new Joueur();
        String[] DeckNORD = new String[60];
        String[] DeckSUD = new String[60];
        NORD.creerCartes(DeckNORD);
        SUD.creerCartes(DeckSUD);
        NORD.Piocher(DeckNORD,NORD,6);
    }
}