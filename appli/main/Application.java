package appli.main;

import appli.jeu.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        Player nord = game.getNord();
        Player sud = game.getSud();
        Board board = game.getBoard();
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isValidInput = false;
        boolean isGameOver = false;

        nord.draw(6);
        sud.draw(6);
        game.showState(nord);
        System.out.print("> ");
        while (!isValidInput) {
            input = sc.nextLine();
            String[] selectionnedCards = Input.splitString(input);
            if (Input.allIsValid(selectionnedCards, nord.getHand())) {
                for (String card : selectionnedCards) {
                    int cardValue = Integer.parseInt(card.substring(0, 2));
                    Pile target = board.getTarget("NORD", card.substring(2, card.length()));
                    nord.playCard(cardValue, target);
                    isValidInput = true;
                }
                int cardsToDraw = Rules.requiredDraws(selectionnedCards, nord.getHand());
                nord.draw(cardsToDraw);
                System.out.printf("%d cartes posées, %d cartes piochées\n", selectionnedCards.length, cardsToDraw);
            } else {
                System.out.print("#> ");
            }
        }
        game.showState(sud);
        isValidInput = false;
        System.out.print("> ");
        while (!isValidInput) {
            input = sc.nextLine();
            String[] selectionnedCards = Input.splitString(input);
            if (Input.allIsValid(selectionnedCards, sud.getHand())) {
                for (String card : selectionnedCards) {
                    int cardValue = Integer.parseInt(card.substring(0, 2));
                    Pile target = board.getTarget("SUD", card.substring(2, card.length()));
                    sud.playCard(cardValue, target);
                    isValidInput = true;
                }
                int cardsToDraw = Rules.requiredDraws(selectionnedCards, sud.getHand());
                sud.draw(cardsToDraw);
                System.out.printf("%d cartes posées, %d cartes piochées\n", selectionnedCards.length, cardsToDraw);
            } else {
                System.out.print("#> ");
            }
        }
    }
}
