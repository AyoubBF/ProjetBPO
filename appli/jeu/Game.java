package appli.jeu;

public class Game {
    private Board board;
    private Player nord;
    private Player sud;
    private Hand nordHand;
    private Hand sudHand;
    private Deck nordDeck;
    private Deck sudDeck;

    /**
     *
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
     * @param player
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
     * @return
     */
    public Player getNord() {
        return this.nord;
    }

    /**
     * @return
     */
    public Player getSud() {
        return this.sud;
    }

    /**
     * @return
     */
    public Board getBoard() {
        return this.board;
    }

}
