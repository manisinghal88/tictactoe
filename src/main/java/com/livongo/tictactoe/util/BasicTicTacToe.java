package com.livongo.tictactoe.util;

import com.livongo.tictactoe.model.TicTacToe;

/**
 * Created by mani on 7/14/17.
 */
public class BasicTicTacToe extends TicTacToe {


    public BasicTicTacToe(char userMarker, char compMarker, int size) {
        super(userMarker,compMarker,size);
    }


    public boolean isGameOver() {
        return isWinner() || isBoardFull();
    }

    public boolean playTurn(int spot) {
        if(!withinRange(spot) || isSpotFilled(spot)) {
            return false;
        }

        getBoard()[spot - 1] = getCurrentTurn();

        if (getCurrentTurn() == getUserMarker()) {
            setCurrentTurn(getCompMarker());
        } else {
            setCurrentTurn(getUserMarker());
        }
        return true;
    }

    public void printBoard() {
        System.out.println("Board:\t\t\tMovement Options:");
        int count = 0;
        int option = 1;
        for (int i = 0; i < getBoardSize(); i++) {
            boolean isFirst = true;
            for (int j = 0; j < getBoardSize(); j++) {
                if (!isFirst) {
                    System.out.print("|");
                }
                isFirst = false;
                System.out.print(getBoard()[count]);
                count++;
            }
            System.out.print("\t\t\t");
            isFirst = true;
            for (int k = 0; k < getBoardSize(); k++) {
                if (!isFirst) {
                    System.out.print("|");
                }
                isFirst = false;
                System.out.print(option);
                option++;
            }

            System.out.println();
        }

    }

    public void populateMessageMap() {
        getMessageMap().put(1, "upper left");
        getMessageMap().put(2, "upper middle");
        getMessageMap().put(3, "upper right");
        getMessageMap().put(4, "center left");
        getMessageMap().put(5, "center");
        getMessageMap().put(6, "center right");
        getMessageMap().put(7, "lower left");
        getMessageMap().put(8, "lower middle");
        getMessageMap().put(9, "lower right");
    }

    private boolean completeColumn() {
        boolean isColumnComplete = false;

        int firstColumnIndex = 0;
        for (int i = 0; i < getBoardSize(); i++) {
            boolean isWinner = true;
            int nextIndex = firstColumnIndex + getBoardSize();
            for (int j = 0; j < getBoardSize() - 1; j++) {
                if (getBoard()[firstColumnIndex] != getBoard()[nextIndex] || getBoard()[firstColumnIndex] == EMPTY_SLOT_CHAR) {
                    isWinner = false;
                    break;
                }
                nextIndex = nextIndex + getBoardSize();
            }

            if (isWinner) {
                isColumnComplete = true;
                setWinner(getBoard()[firstColumnIndex]);
                break;
            }
            firstColumnIndex = firstColumnIndex + 1;
        }
        return isColumnComplete;
    }

    private boolean completeFirstDiagonal() {
        boolean isDiagonalsComplete = true;

        int firstDiagonalIndex = 0;
        int nextIndex = firstDiagonalIndex + getBoardSize() + 1;
        for (int j = 0; j < getBoardSize() - 1; j++) {
            if (getBoard()[firstDiagonalIndex] != getBoard()[nextIndex] || getBoard()[firstDiagonalIndex] == EMPTY_SLOT_CHAR) {
                isDiagonalsComplete = false;
                break;
            }
            nextIndex = nextIndex + getBoardSize() + 1;
        }
        if (isDiagonalsComplete) {
            setWinner(getBoard()[firstDiagonalIndex]);
        }

        return isDiagonalsComplete;
    }

    private boolean completeRow() {
        boolean isRowComplete = false;

        int firstRowIndex = 0;
        for (int i = 0; i < getBoardSize(); i++) {
            boolean isWinner = true;
            for (int j = 0; j < getBoardSize(); j++) {
                if (getBoard()[firstRowIndex] != getBoard()[firstRowIndex + j] || getBoard()[firstRowIndex] == EMPTY_SLOT_CHAR) {
                    isWinner = false;
                    break;
                }
            }

            if (isWinner) {
                isRowComplete = true;
                setWinner(getBoard()[firstRowIndex]);
                break;
            }
            firstRowIndex = firstRowIndex + getBoardSize();
        }
        return isRowComplete;
    }

    private boolean completeSecondDiagonal() {
        boolean isDiagonalsComplete = true;

        int firstDiagonalIndex = getBoardSize() - 1;
        int nextIndex = firstDiagonalIndex + getBoardSize() - 1;
        for (int j = 0; j < getBoardSize() - 1; j++) {
            if (getBoard()[firstDiagonalIndex] != getBoard()[nextIndex] || getBoard()[firstDiagonalIndex] == EMPTY_SLOT_CHAR) {
                isDiagonalsComplete = false;
                break;
            }
            nextIndex = nextIndex + getBoardSize() - 1;
        }
        if (isDiagonalsComplete) {
            setWinner(getBoard()[firstDiagonalIndex]);
        }

        return isDiagonalsComplete;
    }

    private boolean isBoardFull() {

        for (char ch : getBoard()) {
            if (ch == EMPTY_SLOT_CHAR) {
                return false;
            }
        }
        return true;
    }

    private boolean isSpotFilled(int spot) {
        return getBoard()[spot - 1] != EMPTY_SLOT_CHAR;
    }

    private boolean isWinner() {
        return (completeRow() || completeColumn() || completeFirstDiagonal() || completeSecondDiagonal());
    }

    private boolean withinRange(int spot) {
        return (spot > 0 && spot <= getBoard().length);
    }

}
