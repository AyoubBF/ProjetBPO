package appli;

import rules.Input;
import rules.Rules;
import game.*;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        Player nord = game.getNord();
        Player sud = game.getSud();
        Board board = game.getBoard();
        Player[] players = game.getPlayers();
        Scanner sc = new Scanner(System.in);
        String input;
        boolean isValidInput = false;
        boolean isGameOver = false;

        nord.draw(6);
        sud.draw(6);

        while (!isGameOver) {
            for(Player player : players){
                Player opponent = game.getOpponent(player);
                game.showState(player);

                boolean opponentFinished = opponent.checkIfFinished();
                if(opponentFinished){
                    game.announceWinner(opponent);
                    System.exit(0);
                }

                boolean playerCanPlay = player.checkIfCanPlay(player.getHand(), board, player);
                if(!playerCanPlay) {
                    game.announceWinner(opponent);
                    System.exit(0);
                }

                System.out.print("> ");

                while (!isValidInput) {
                    input = sc.nextLine();
                    String[] selectionnedCards = Input.splitString(input);

                    if (Input.allIsValid(selectionnedCards, player.getHand())) {
                        for (String card : selectionnedCards) {
                            Pile target = board.getTarget(player.getName(), card.substring(2, card.length()));
                            int cardValue = Integer.parseInt(card.substring(0, 2));
                            boolean isEnemyMove = Input.checkIfEnemyMove(card);
                            boolean isValidMove = Rules.checkIfValid(cardValue, target, isEnemyMove);

                            if (isValidMove) {
                                player.playCard(cardValue, target);
                                isValidInput = true;
                            }
                        }
                        int cardsToDraw = Rules.requiredDraws(selectionnedCards, player.getHand());
                        player.draw(cardsToDraw);
                        System.out.printf("%d cartes posées, %d cartes piochées\n", selectionnedCards.length, cardsToDraw);
                    } else {
                        System.out.print("#> ");
                    }
                }
                isValidInput = false;
            }
        }
    }
}
