package appli.jeu;

public class Input {
    public static String[] splitString(String input) {
        String[] selectionnedCards = input.split("\\s+");
        return selectionnedCards;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValid(String card, Hand hand) {
        String firstChars = card.substring(0, 2);
        int cardValue = Integer.parseInt(firstChars);

        boolean hasCard = hand.contains(cardValue);
        boolean sizeIsValid = card.length() >= 3 && card.length() <= 4;
        boolean firstCharsAreValid = isNumeric(firstChars);
        boolean thirdCharIsValid = card.charAt(2) == '^' || card.charAt(2) == 'v';
        boolean fourthCharIsValid = true;

        if (card.length() == 4)
            fourthCharIsValid = card.charAt(3) == '\'';

        return sizeIsValid && firstCharsAreValid && thirdCharIsValid && fourthCharIsValid && hasCard;
    }

    private static boolean findIfDuplicates(String[] selectionnedCards) {
        boolean hasDuplicates = false;
        for (int i = 0; i < selectionnedCards.length; i++) {
            for (int j = i + 1; j < selectionnedCards.length; j++) {
                String firstCardValue = selectionnedCards[i].substring(0, 2);
                String secondCardValue = selectionnedCards[j].substring(0, 2);
                if (firstCardValue.equals(secondCardValue))
                    hasDuplicates = true;
            }
        }
        return hasDuplicates;
    }

    public static boolean allIsValid(String[] selectionnedCards, Hand hand) {
        if (selectionnedCards.length > 6 || selectionnedCards.length <= 1) {
            return false;
        }
        if (!Rules.onlyOneEnemyMove(selectionnedCards))
            return false;
        for (String card : selectionnedCards) {
            if (!isValid(card, hand))
                return false;
        }
        if (findIfDuplicates(selectionnedCards))
            return false;

        return true;
    }
}