package com.livongo.tictactoe;

import com.livongo.tictactoe.util.BasicTicTacToe;
import com.livongo.tictactoe.util.AIPlayer;

import java.util.Scanner;

/**
 * Created by mani on 7/15/17.
 */
class PlayGameWithAI {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean continuePlay = true;

        while (continuePlay) {
            System.out.println("Welcome to Tic-Tac-Toe. \nPlease make your move selection by entering a number 1-9 corresponding to the movement option on the right.");

            char playerChar = 'X';
            char opponentChar = 'O';

            BasicTicTacToe game = new BasicTicTacToe(playerChar, opponentChar, 3);
            AIPlayer aiPlayer = new AIPlayer();

            game.printBoard();
            game.populateMessageMap();

            game.setCurrentTurn(playerChar);
            int userLocation = 0;
            int opponentLocation = 0;
            while (!game.isGameOver()) {
                if (game.getCurrentTurn() == game.getUserMarker()) {
                    System.out.print("\nWhere to? ");
                    userLocation = scanNext(scan);
                    while (!game.playTurn(userLocation)) {
                        System.out.println("**Invalid Option**\nPlease enter valid Option which is not used OR in range\n");
                        game.printBoard();
                        System.out.print("\nWhere to? ");
                        userLocation = scanNext(scan);
                    }
                } else {
                    opponentLocation = aiPlayer.playTurn(game);
                    game.playTurn(opponentLocation);
                    System.out.println("\nYou have put an " + game.getUserMarker() + " in " + game.getMessageMap().get(userLocation) + ". " + "I will put an " + game.getCompMarker() + " in the " + game.getMessageMap().get(opponentLocation) + ".");
                    System.out.println();
                    game.printBoard();
                }

            }
            if (game.getWinner() == playerChar) {
                System.out.println("\nYou have put an " + game.getUserMarker() + " in " + game.getMessageMap().get(userLocation) + ". " + "You have beaten my AI!");
            } else if (game.getWinner() == opponentChar) {
                System.out.println("\nYou have put an " + game.getUserMarker() + " in " + game.getMessageMap().get(userLocation) + ". " + "I will put an " + game.getCompMarker() + " in the " + game.getMessageMap().get(opponentLocation) + ".");
                System.out.println("I Win!!!");
            } else {
                System.out.println("Game Draw. No one wins this game.");
            }
            System.out.println();
            game.printBoard();

            System.out.println("\nDo you want to play again? \n Enter Y to continue");

            char playAgain = scan.next().charAt(0);

            continuePlay = (playAgain == 'Y');
        }
    }

    private static int scanNext(Scanner scan) {
        int location = 0;
        String next = scan.next();
        try {
            location = Integer.parseInt(next);
        } catch (Exception e) {
            System.out.println("Only Integers input allowed in this game.");
        }
        return location;
    }
}
