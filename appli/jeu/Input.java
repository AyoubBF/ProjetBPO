package appli.jeu;

import java.util.ArrayList;

public class Input {
    /**
     * @param input
     * @return
     */
    public static String[] splitString(String input) {
        String[] selectionnedCards = input.split("\\s+");
        return selectionnedCards;
    }

    /**
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param card
     * @param hand
     * @return
     */
    private static boolean isValid(String card, Hand hand) {
        String firstChars = card.substring(0, 2);
        int cardValue = 0;
        try {
            cardValue = Integer.parseInt(firstChars);

            boolean hasCard = hand.contains(cardValue);
            boolean sizeIsValid = card.length() >= 3 && card.length() <= 4;
            boolean firstCharsAreValid = isNumeric(firstChars);
            boolean thirdCharIsValid = card.charAt(2) == '^' || card.charAt(2) == 'v';
            boolean fourthCharIsValid = true;

            if (card.length() == 4)
                fourthCharIsValid = card.charAt(3) == '\'';

            return sizeIsValid && firstCharsAreValid && thirdCharIsValid && fourthCharIsValid && hasCard;
        } catch (NumberFormatException e) {
            return false;
        } catch (StringIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * @param selectionnedCards
     * @return
     */
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

    /**
     * @param selectionnedCards
     * @param hand
     * @return
     */
    public static boolean allIsValid(String[] selectionnedCards, Hand hand) {
        boolean allCardsValid = true;

        for (String card : selectionnedCards) {
            if (!isValid(card, hand))
                allCardsValid = false;
        }
        return selectionnedCards.length <= 6 && selectionnedCards.length >= 1 && Rules.onlyOneEnemyMove(selectionnedCards) && !findIfDuplicates(selectionnedCards) && orderIsValid(selectionnedCards) && allCardsValid;
    }

    /**
     * @param arraylist
     * @return
     */
    private static boolean isAscending(ArrayList<Integer> arraylist) {
        boolean isSorted = true;
        for (int i = 1; i < arraylist.size(); i++) {
            if (arraylist.get(i - 1) - (arraylist.get(i)) != 10) {
                if (arraylist.get(i - 1) - (arraylist.get(i)) > 0) {
                    isSorted = false;
                    break;
                }
            }
        }
        return isSorted;
    }

    /**
     * @param arraylist
     * @return
     */
    private static boolean isDescending(ArrayList<Integer> arraylist) {
        boolean isSorted = true;
        for (int i = 1; i < arraylist.size(); i++) {
            if (arraylist.get(i) - (arraylist.get(i - 1)) != 10) {
                if (arraylist.get(i - 1) - (arraylist.get(i)) < 0) {
                    isSorted = false;
                    break;
                }
            }
        }
        return isSorted;
    }

    /**
     * @param selectionnedCards
     * @return
     */
    private static boolean orderIsValid(String[] selectionnedCards) {
        boolean isValid = true;
        ArrayList<Integer> ascendingPlayer = new ArrayList<>();
        ArrayList<Integer> descendingPlayer = new ArrayList<>();
        try {
            for (String card : selectionnedCards) {
                if (card.length() != 4) {
                    if (card.charAt(2) == '^' && card.length() == 3)
                        ascendingPlayer.add(Integer.parseInt(card.substring(0, 2)));
                    if (card.charAt(2) == 'v' && card.length() == 3)
                        descendingPlayer.add(Integer.parseInt(card.substring(0, 2)));
                }
            }
        } catch (StringIndexOutOfBoundsException e) {

        }

        if (!isAscending(ascendingPlayer) || !isDescending(descendingPlayer)) {
            isValid = false;
        }

        return isValid;
    }

    /**
     * @param card
     * @return
     */
    public static boolean checkIfEnemyMove(String card) {
        return card.length() == 4 ? true : false;
    }
}