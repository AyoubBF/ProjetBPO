package game;

public class Board {
    private Pile ascendingNord;
    private Pile descendingNord;
    private Pile ascendingSud;
    private Pile descendingSud;

    /**
     * @brief Constructeur pour les piles ascendantes et descendantes des joueurs Nord et Sud
     * @param nord le joueur Nord
     * @param sud le joueur Sud
     */
    public Board(Player nord, Player sud) {
        ascendingNord = new Pile(nord, 1, "asc");
        descendingNord = new Pile(nord, 60, "desc");
        ascendingSud = new Pile(sud, 1, "asc");
        descendingSud = new Pile(sud, 60, "desc");
    }


    /**
     * @brief permet de donner le point de vue des piles en fonction du joueur fourni
     * @param playerName le nom du joueur
     * @param targetCode les caractères qui montrent la pile que vise le joueur
     * @return renvoie la pile que vise le joueur
     */
    public Pile getTarget(String playerName, String targetCode) {
        String direction;
        boolean isTargetEnemy = false;

        if (targetCode.charAt(0) == '^')
            direction = "asc";
        else
            direction = "desc";
        if (targetCode.charAt(targetCode.length() - 1) == '\'')
            isTargetEnemy = true;

        if (playerName == "NORD" && direction == "asc" && !isTargetEnemy) {
            return this.ascendingNord;
        }
        if (playerName == "NORD" && direction == "desc" && !isTargetEnemy) {
            return this.descendingNord;
        }
        if (playerName == "NORD" && direction == "asc" && isTargetEnemy) {
            return this.ascendingSud;
        }
        if (playerName == "NORD" && direction == "asc" && isTargetEnemy) {
            return this.descendingSud;
        }

        if (playerName == "SUD" && direction == "asc" && !isTargetEnemy) {
            return this.ascendingSud;
        }
        if (playerName == "SUD" && direction == "desc" && !isTargetEnemy) {
            return this.descendingSud;
        }
        if (playerName == "SUD" && direction == "asc" && isTargetEnemy) {
            return this.ascendingNord;
        }
        if (playerName == "SUD" && direction == "asc" && isTargetEnemy) {
            return this.descendingNord;
        }

        return this.ascendingNord;
    }

    /**
     * @brief permet d'accéder la valeur privée de la pile ascendante Nord
     * @return la valeur de la pile ascendante Nord
     */
    public Pile getAscendingNord() {
        return this.ascendingNord;
    }

    /**
     * @brief permet d'accéder la valeur privée de la pile descendante Nord
     * @return la valeur de la pile descendante Nord
     */
    public Pile getDescendingNord() {
        return this.descendingNord;
    }

    /**
     * @brief permet d'accéder la valeur privée de la pile ascendante Sud
     * @return la valeur de la pile ascendante Sud
     */
    public Pile getAscendingSud() {
        return this.ascendingSud;
    }

    /**
     * @brief permet d'accéder la valeur privée de la pile descendante Sud
     * @return la valeur de la pile descendante Sud
     */
    public Pile getDescendingSud() {
        return this.descendingSud;
    }

    /**
     * @brief permet de récupérer les valeurs privées de toutes les piles
     * @return un tableau de type Pile contenant les quatres piles
     */
    public Pile[] getAllPiles() {
        Pile[] piles = {this.ascendingNord, this.ascendingSud, this.descendingNord, this.descendingSud};
        return piles;
    }

    public void setAscendingNord(int value){
        this.ascendingNord.setActualValue(value);
    }

    public void setAscendingSud(int value){
        this.ascendingSud.setActualValue(value);
    }

    public void setDescendingNord(int value){
        this.descendingNord.setActualValue(value);
    }

    public void setDescendingSud(int value){
        this.descendingSud.setActualValue(value);
    }
}
