package appli.jeu;

public class Rules {
    /**
     * @param cardValue
     * @param target
     * @param isEnemyMove
     * @return
     */
    public static boolean checkIfValid(int cardValue, Pile target, boolean isEnemyMove) {
        String direction = target.getDirection();
        int actualValue = target.getActualValue();

        if (isEnemyMove) {
            if (direction == "asc") {
                if (cardValue < actualValue || cardValue == actualValue + 10)
                    return true;
            } else {
                if (cardValue > actualValue || cardValue == actualValue - 10)
                    return true;
            }
        } else {
            if (direction == "asc") {
                if (cardValue > actualValue || cardValue == actualValue - 10)
                    return true;
            } else {
                if (cardValue < actualValue || cardValue == actualValue + 10)
                    return true;
            }
        }
        return false;
    }

    /**
     * @param selectionnedCards
     * @return
     */
    public static boolean onlyOneEnemyMove(String[] selectionnedCards) {
        int enemyMoveCounter = 0;

        for (String card : selectionnedCards) {
            if (card.charAt(card.length() - 1) == '\'')
                enemyMoveCounter++;
        }

        return (enemyMoveCounter <= 1) ? true : false;
    }

    /**
     * @param selectionnedCards
     * @return
     */
    private static boolean containsEnemyMove(String[] selectionnedCards) {
        int enemyMoveCounter = 0;

        for (String card : selectionnedCards) {
            if (card.charAt(card.length() - 1) == '\'')
                enemyMoveCounter++;
        }

        return (enemyMoveCounter == 1) ? true : false;
    }

    /**
     * @param selectionnedCards
     * @param hand
     * @return
     */
    public static int requiredDraws(String[] selectionnedCards, Hand hand) {
        int cardsToDraw = 6 - hand.size();
        return containsEnemyMove(selectionnedCards) ? cardsToDraw : 2;
    }

    /**
     * @param card
     * @param board
     * @return
     */
    public static boolean isPlayable(int card, Board board) {  //################################################
        boolean isValid = false;
        if (card > board.getAscendingNord().getActualValue()
                || card > board.getAscendingSud().getActualValue()
                || card < board.getDescendingNord().getActualValue()  // Ici c'est faux pcq faudra voir si la carte appartient à nord ou sud pour connaître le signe de la comparaison
                || card < board.getDescendingSud().getActualValue())  // Et faudra pas oublier les cas avec +10
            return true;
        return isValid;
    }
}
