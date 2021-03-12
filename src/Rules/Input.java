package Rules;

import java.util.ArrayList;

public class Input {
    /**
     * @brief découpe une string pour former un tableau de string en fonction
     * du format de la regex
     * @param input la saisie de l'utilisateur
     * @return un tableau de string contenant la découpe
     */
    public static String[] splitString(String input) {
        String[] selectionnedCards = input.split("\\s+");
        return selectionnedCards;
    }

    /**
     * @brief vérifie si la chaîne de caractère est un entier
     * @param str la chaîne de caractère à vérifier
     * @return vrai si les caractères sont tous au format numérique
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
     * @brief vérifie la validité de la carte que le joueur s'apprête à poser
     * @param card correspond à la carte choisie
     * @param hand correspond à la main du joueur
     * @return true si toutes les règles de saisie sont valides
     */
    private static boolean isValid(String card, ArrayList<Integer> hand) {
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
     * @brief Vérifie si, lors de la saisie, un joueur tente de poser plus d'une
     * fois la même carte peu importe les caractères qui suivent les deux digits
     * @param selectionnedCards la saisie du joueur
     * @return  false si chaque coup est unique
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
     * @brief Utilise la méthode isValid, onlyOneEnemyMove, findIfDuplicates &
     * orderIsValid pour toute la saisie du joueur découpée
     * @param selectionnedCards saisie du joueur découpée
     * @param hand les cartes en main du joueur
     * @return true si toutes les méthodes utilisées renvoient toutes true
     */
    public static boolean allIsValid(String[] selectionnedCards, ArrayList<Integer> hand) {
        boolean allCardsValid = true;

        for (String card : selectionnedCards) {
            if (!isValid(card, hand))
                allCardsValid = false;
        }
        return selectionnedCards.length <= 6
                && selectionnedCards.length >= 1
                && Rules.onlyOneEnemyMove(selectionnedCards)
                && !findIfDuplicates(selectionnedCards)
                && orderIsValid(selectionnedCards)
                && allCardsValid;
    }

    /**
     * @brief vérifie si les cartes que le joueur tente de poser suivies du caractère
     * '^' sont bien ascendantes (pas besoin de vérifier pour le cas où on joue sur
     * une pile ennemie car on ne peut en saisir qu'une)
     * @param arraylist la liste des cartes avec le caractère '^'
     * @return true si l'ordre est ascendant est respecté
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
     * @brief vérifie si les cartes que le joueur tente de poser suivies du caractère
     * 'v' sont bien ascendantes (même parenthèse que pour la méthode isAscending)
     * @param arraylist la liste des cartes avec le caractère 'v'
     * @return true si l'ordre est descendant est respecté
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
     * @brief vérifie que l'ordre de toutes les cartes selectionnées sont valides
     * grâce aux méthodes isAscending & isDescending
     * @param selectionnedCards la chaîne de caractère saisie découpée
     * @return true si l'ordre est valide
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
     * @brief Vérifie s'il s'agit d'un coup ennemi, en effet ici on ne vérifie pas
     * si le caractère est une apostrophe car cette méthode sert seulement à
     * vérifier si plus d'un coup ennemi est effectuer
     * @param card la carte et sa commande qui indique la pile ciblée
     * @return true s'il s'agit d'un coup ennemi
     */
    public static boolean checkIfEnemyMove(String card) {
        return card.length() == 4 ? true : false;
    }
}