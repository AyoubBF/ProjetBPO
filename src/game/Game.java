package game;

import java.util.ArrayList;

public class Game {
    private Board board;
    private Player nord;
    private Player sud;
    private ArrayList<Integer> nordHand;
    private ArrayList<Integer> sudHand;
    private ArrayList<Integer> nordDeck;
    private ArrayList<Integer> sudDeck;

    /**
     * @brief Constructeur pour les joueurs, leurs decks, leurs mains et
     * le plateau
     */
    public Game() {
        this.nord = new Player("NORD");
        this.sud = new Player("SUD");
        this.board = new Board(nord, sud);
        this.nordDeck = nord.getDeck();
        this.nordHand = nord.getHand();
        this.sudDeck = sud.getDeck();
        this.sudHand = sud.getHand();
    }

    /**
     * @brief affiche les 4 piles ainsi que le nombre de cartes en main et
     * dans la pioche des deux joueurs puis affiche les cartes en main du
     * joueur qui va jouer
     * @param player correspond au joueur courant
     */
    public void showState(Player player) {
        int ascendingNordValue = this.board.getAscendingNord().getActualValue();
        int descendingNordValue = this.board.getDescendingNord().getActualValue();
        int ascendingSudValue = this.board.getAscendingSud().getActualValue();
        int descendingSudValue = this.board.getDescendingSud().getActualValue();

        System.out.printf("NORD ^[%02d] v[%02d] (m%dp%d) \n", ascendingNordValue, descendingNordValue, nordHand.size(), nordDeck.size());
        System.out.printf("SUD ^[%02d] v[%02d] (m%dp%d) \n", ascendingSudValue, descendingSudValue, sudHand.size(), sudDeck.size());
        System.out.print("cartes " + player.getName() + " { ");
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.print(String.format("%02d ", player.getHand().get(i)));
        }
        System.out.println("}");
    }

    /**
     * @brief affiche le joueur qui a gagné la partie
     * @param player le vainqueur
     */
    public void announceWinner(Player player) {
        System.out.printf("partie finie, %s a gagné\n", player.getName());
    }

    /**
     *@brief permet d'obtenir le joueur Nord
     *@return le joueur Nord
     */
    public Player getNord() {
        return this.nord;
    }

    /**
     * @brief permet d'obtenir le joueur Sud
     * @return le joueur Sud
     */
    public Player getSud() {
        return this.sud;
    }

    /**
     * @brief permet d'obtenir les valeurs des piles du plateau
     * @return le plateau
     */
    public Board getBoard() {
        return this.board;
    }

    public Player[] getPlayers(){
        Player[] players = {nord, sud};
        return players;
    }

    public Player getOpponent(Player player){
        return player.getName() == "NORD" ? sud : nord;
    }
}
