package appli.main;

import appli.jeu.*;

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

        while (!isGameOver) {
            game.showState(nord);
            boolean sudFinished = sud.checkIfFinished(sud.getHand(), sud.getDeck());
            if(sudFinished){
                game.announceWinner(sud);
                System.exit(0);
            }
            boolean nordCanPlay = nord.checkIfCanPlay(nord.getHand(), board, nord);
            if(!nordCanPlay) {
                game.announceWinner(sud);
                System.exit(0);
            }
            System.out.print("> ");
            while (!isValidInput) {
                input = sc.nextLine();
                String[] selectionnedCards = Input.splitString(input);
                if (Input.allIsValid(selectionnedCards, nord.getHand())) {
                    for (String card : selectionnedCards) {
                        Pile target = board.getTarget("NORD", card.substring(2, card.length()));
                        int cardValue = Integer.parseInt(card.substring(0, 2));
                        boolean isEnemyMove = Input.checkIfEnemyMove(card);
                        boolean isValidMove = Rules.checkIfValid(cardValue, target, isEnemyMove);
                        if (isValidMove) {
                            nord.playCard(cardValue, target);
                            isValidInput = true;
                        }
                    }
                    int cardsToDraw = Rules.requiredDraws(selectionnedCards, nord.getHand());
                    nord.draw(cardsToDraw);
                    System.out.printf("%d cartes posées, %d cartes piochées\n", selectionnedCards.length, cardsToDraw);
                } else {
                    System.out.print("#> ");
                }
            }
            game.showState(sud);
            boolean nordFinished = nord.checkIfFinished(nord.getHand(), nord.getDeck());
            if(nordFinished){
                game.announceWinner(nord);
                System.exit(0);
            }
            boolean sudCanPlay = sud.checkIfCanPlay(sud.getHand(), board, sud);
            if(!sudCanPlay) {
                game.announceWinner(nord);
                System.exit(0);
            }
            isValidInput = false;
            System.out.print("> ");
            while (!isValidInput) {
                input = sc.nextLine();
                String[] selectionnedCards = Input.splitString(input);
                if (Input.allIsValid(selectionnedCards, sud.getHand())) {
                    for (String card : selectionnedCards) {
                        Pile target = board.getTarget("SUD", card.substring(2, card.length()));
                        int cardValue = Integer.parseInt(card.substring(0, 2));
                        boolean isEnemyMove = Input.checkIfEnemyMove(card);
                        boolean isValidMove = Rules.checkIfValid(cardValue, target, isEnemyMove);
                        if (isValidMove) {
                            sud.playCard(cardValue, target);
                            isValidInput = true;
                        }
                    }
                    int cardsToDraw = Rules.requiredDraws(selectionnedCards, sud.getHand());
                    sud.draw(cardsToDraw);
                    System.out.printf("%d cartes posées, %d cartes piochées\n", selectionnedCards.length, cardsToDraw);
                } else {
                    System.out.print("#> ");
                }
            }
            isValidInput = false;
        }
    }
}
