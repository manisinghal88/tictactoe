package com.livongo.tictactoe.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by mani on 7/15/17.
 */
public class AIPlayer {

    private static Random random = new Random();

    public int playTurn(BasicTicTacToe game) {
        final List<Integer> availableTurn = new ArrayList<Integer>();
        for (int i = 0; i < game.getBoard().length; i++) {
            if (game.getBoard()[i] == ' ') {
                availableTurn.add(i + 1);
            }
        }

        return availableTurn.get(Math.abs(random.nextInt() % availableTurn.size()));
    }
}
