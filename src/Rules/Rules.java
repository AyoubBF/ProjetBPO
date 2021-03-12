package rules;

import game.Board;
import game.Pile;
import game.Player;

import java.util.ArrayList;

public class Rules {
    /**
     * @brief Verifie si la pose que l'on veut effectuer est un coup valide en fonction de la pile ciblée
     * et de s'il s'agit d'un coup sur pile ennemie
     * @param cardValue la valeur de la carte
     * @param target la pile ciblée
     * @param isEnemyMove est vrai si on cible une pile ennemie
     * @return true si la pose est valide
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
     * @brief vérifie qu'un seul coup est joué sur une pile ennemi
     * @param selectionnedCards la chaîne de caractère saisie par le joueur
     * @return true si le nombre de coup sur les piles ennemies est inférieur ou égal à 1
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
     * @brief vérifie si le joueur joue sur une pile ennemie (pour ensuite lui faire piocher
     * des cartes jusqu'à que sa main soit pleine)
     * @param selectionnedCards la chaîne de caractère saisie par le joueur
     * @return true si le joueur joue sur une pile ennemie
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
     * @brief permet de déterminer automatiquement le nombre de cartes qu'un joueur doit piocher
     * @param selectionnedCards la chaîne de caractère saisie par le joueur
     * @param hand la main du joueur
     * @return le nombre de cartes que le joueur doit piocher
     */
    public static int requiredDraws(String[] selectionnedCards, ArrayList<Integer> hand) {
        int cardsToDraw = 6 - hand.size();
        return containsEnemyMove(selectionnedCards) ? cardsToDraw : 2;
    }

    /**
     * @brief Vérifie si la carte peut être posée dans au moins une des piles du plateau
     * @param card la carte vérifiée
     * @param board le plateau contenant les piles
     * @return false si la carte n'est pas jouable
     */
    public static boolean isPlayable(int card, Board board, Player player) {
        boolean isPlayable = false;
        Pile[] piles = board.getAllPiles();
        String playerName = player.getName();
        boolean isEnemyMove;

        for (Pile pile : piles) {
            String pileOwner = pile.getOwner().getName();
            isEnemyMove = !playerName.equals(pileOwner);
            boolean isValid = checkIfValid(card, pile, isEnemyMove);
            if (isValid) isPlayable = true;
        }
        return isPlayable;
    }
}
