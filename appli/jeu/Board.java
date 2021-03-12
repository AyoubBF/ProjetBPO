package appli.jeu;

public class Board {
    private Pile ascendingNord;
    private Pile descendingNord;
    private Pile ascendingSud;
    private Pile descendingSud;

    /**
     * @param nord
     * @param sud
     */
    public Board(Player nord, Player sud) {
        ascendingNord = new Pile(nord, 1, "asc");
        descendingNord = new Pile(nord, 60, "desc");
        ascendingSud = new Pile(sud, 1, "asc");
        descendingSud = new Pile(sud, 60, "desc");
    }

    /**
     * @param playerName
     * @param targetCode
     * @return
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
     * @return
     */
    public Pile getAscendingNord() {
        return this.ascendingNord;
    }

    /**
     * @return
     */
    public Pile getDescendingNord() {
        return this.descendingNord;
    }

    /**
     * @return
     */
    public Pile getAscendingSud() {
        return this.ascendingSud;
    }

    /**
     * @return
     */
    public Pile getDescendingSud() {
        return this.descendingSud;
    }
}
