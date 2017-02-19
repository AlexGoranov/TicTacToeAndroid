package com.example.kajht.myapplication;

import android.widget.Button;
import java.util.Random;

/**
 * Created by Alex on 2/18/17.
 */

public class Game {
    private final static int WIN_ONE = 3;
    private final static int WIN_TWO = 4;
    private final static int DRAW = 0;
    private final static int DEFAULT = -1;
    private final static int COMPUTER = 5;
    private final static int PLAYER_ONE = 1;
    private final static int PLAYER_TWO = 2;
    private boolean finished;
    private boolean against;
    private boolean player;
    private int board[];
    private int bSize = 9;

    public Game(boolean who) {
        against = who;
        finished = false;
        player = false;
        board = new int[bSize];
        for (int i = 0; i < board.length; i++) {
            board[i] = 0;
        }
    }

    public void init() {
        finished = false;
        player = false;
        board = new int[bSize];
        for (int i = 0; i < board.length; i++) {
            board[i] = 0;
        }
    }

    public int move(int n) {
        if (!finished) {
            if (checkDraw()) {
                return DRAW;
            }
            if (!against) {
                if (board[n] == 0) {
                    player = !player;
                    if (player) {
                        board[n] = PLAYER_ONE;
                        if (checkWin(PLAYER_ONE)) {
                            return WIN_ONE;
                        }
                        if (checkDraw()) {
                            return DRAW;
                        }
                        return PLAYER_ONE;
                    } else {
                        board[n] = PLAYER_TWO;
                        if (checkWin(PLAYER_TWO)) {
                            return WIN_TWO;
                        }
                        if (checkDraw()) {
                            return DRAW;
                        }
                        return PLAYER_TWO;
                    }
                }
            } else {
                if (board[n] == 0) {
                    board[n] = PLAYER_ONE;
                    if (checkWin(PLAYER_ONE)) {
                        return WIN_ONE;
                    }
                    if (checkDraw()) {
                        return DRAW;
                    }
                    return COMPUTER;
                }
            }
        }
        return DEFAULT;
    }

    public int getMove() {
        Random rand = new Random();
        int m;
        do {
            m = rand.nextInt(9);
        } while (board[m] != 0);
        board[m] = PLAYER_TWO;
        return m;
    }

    public boolean checkWin(int sign) {
        boolean win = false;
        int bRow, bCol;
        bRow = bCol = 3;

        for (int i = 0; i < bRow; i++) {
            //check every row
            // board[i] and board[i+numRows] and board[i+2*numRows]
            if (board[i] == sign && board[i+bRow] == sign
                    && board[i+2*bRow] == sign)
                win = true;
        }
        for (int i = 0; i < bSize; i += bCol) {
            //check every column
            // board[i] and board[i+1] and board[i+2]
            if (board[i] == sign && board[i+1] == sign
                    && board[i+2] == sign)
                win = true;
        }

        //check each diagonal for sign
        if (board[0] == sign && board[4] == sign && board[8] == sign)
            win = true;
        if (board[2] == sign && board[4] == sign && board[6] == sign)
            win = true;

        if (win) {
            finished = true;
        }

        return win;
    }

    public boolean checkDraw() {
        boolean draw = true;

        //simply check if any of the cells is empty
        for (int i = 0; i < bSize; i++) {
            if (board[i] == 0)
                draw = false;
        }

        if (draw) {
            finished = true;
        }

        return draw;
    }
}
