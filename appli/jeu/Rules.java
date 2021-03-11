package appli.jeu;

public class Rules {
    public static boolean isValid(int cardValue, Pile target){
        String direction = target.getDirection();
        int actualValue = target.getActualValue();

        if(direction == "asc"){
            if(cardValue > actualValue || cardValue == actualValue - 10)
                return true;
        }else{
            if(cardValue < actualValue || cardValue == actualValue + 10)
                return true;
        }
        return false;
    }

    public static boolean onlyOneEnemyMove(String[] selectionnedCards){
        int enemyMoveCounter = 0;

        for(String card : selectionnedCards){
            if(card.charAt(card.length() - 1) == '\'')
                enemyMoveCounter++;
        }

        return (enemyMoveCounter <= 1 ) ? true : false;
    }

    private static boolean containsEnemyMove(String[] selectionnedCards){
        int enemyMoveCounter = 0;

        for(String card : selectionnedCards){
            if(card.charAt(card.length() - 1) == '\'')
                enemyMoveCounter++;
        }

        return (enemyMoveCounter == 1 ) ? true : false;
    }

    public static int requiredDraws(String[] selectionnedCards, Hand hand){
        int cardsToDraw = 6 - hand.size();
        return containsEnemyMove(selectionnedCards) ? cardsToDraw : 2;
    }
}
