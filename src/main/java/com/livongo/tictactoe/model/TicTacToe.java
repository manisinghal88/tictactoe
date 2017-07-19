package com.livongo.tictactoe.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mani on 7/16/17.
 */
public abstract class TicTacToe {

    public static final char EMPTY_SLOT_CHAR = ' ';

    private final char[] board;
    private final char userMarker;
    private final char compMarker;
    private final int boardSize;
    private final Map<Integer, String> messageMap = new HashMap<Integer, String>();
    private char winner;
    private char currentTurn;

    protected TicTacToe(char userMarker, char compMarker, int size) {
        this.userMarker = userMarker;
        this.compMarker = compMarker;
        this.winner = EMPTY_SLOT_CHAR;
        this.boardSize = size;
        this.board = setBoard(boardSize * boardSize);

    }

    public char[] getBoard() {
        return board;
    }

    public char getUserMarker() {
        return userMarker;
    }

    public char getCompMarker() {
        return compMarker;
    }

    protected int getBoardSize() {
        return boardSize;
    }

    public Map<Integer, String> getMessageMap() {
        return messageMap;
    }

    public char getWinner() {
        return winner;
    }

    protected void setWinner(char winner) {
        this.winner = winner;
    }

    public char getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(char currentTurn) {
        this.currentTurn = currentTurn;
    }

    private char[] setBoard(int size) {
        char[] board = new char[size];
        for (int i = 0; i < size; i++) {
            board[i] = EMPTY_SLOT_CHAR;
        }
        return board;
    }
}
