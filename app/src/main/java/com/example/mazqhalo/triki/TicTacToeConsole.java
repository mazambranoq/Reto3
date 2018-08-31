package com.example.mazqhalo.triki;

/* TicTacToeConsole.java
 * By Frank McCown (Harding University)
 *
 * This is a tic-tac-toe game that runs in the console window.  The human
 * is X and the computer is O.
 */

import android.graphics.Region;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeConsole {

    private char mBoard[] = new char[9];
    public static final int BOARD_SIZE = 9;

    public static final char HUMAN_PLAYER = 'X';
    public static final char COMPUTER_PLAYER = 'O';
    public static final char OPEN_SPOT = ' ';
    private Random mRand;

    public TicTacToeConsole() {

        // Seed the random number generator
        mRand = new Random();
        clearBoard();
//        char turn = HUMAN_PLAYER;    // Human starts first
//        int  win = 0;                // Set to 1, 2, or 3 when game is over
//
//        // Keep looping until someone wins or a tie
//        while (win == 0)
//        {
//            displayBoard();
//
//            if (turn == HUMAN_PLAYER)
//            {
//                getUserMove();
//                turn = COMPUTER_PLAYER;
//            }
//            else
//            {
//                getComputerMove();
//                turn = HUMAN_PLAYER;
//            }
//
//            win = checkForWinner();
//        }
//
//        displayBoard();

        // Report the winner

//        if (win == 1)
//            System.out.println("It's a tie.");
//        else if (win == 2)
//            System.out.println(HUMAN_PLAYER + " wins!");
//        else if (win == 3)
//            System.out.println(COMPUTER_PLAYER + " wins!");
//        else
//            System.out.println("There is a logic problem!");
    }

    private void displayBoard()	{
        System.out.println();
        System.out.println(mBoard[0] + " | " + mBoard[1] + " | " + mBoard[2]);
        System.out.println("-----------");
        System.out.println(mBoard[3] + " | " + mBoard[4] + " | " + mBoard[5]);
        System.out.println("-----------");
        System.out.println(mBoard[6] + " | " + mBoard[7] + " | " + mBoard[8]);
        System.out.println();
    }


    public void clearBoard(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            mBoard[i] = OPEN_SPOT;
        }
    }
    /** Set the given player at the given location on the game board.
     *  The location must be available, or the board will not be changed.
     *
     * @param player - The HUMAN_PLAYER or COMPUTER_PLAYER
     * @param location - The location (0-8) to place the move
     */
    public void setMove(char player, int location){
        mBoard[location] = player;
    }
    /** Return the best move for the computer to make. You must call setMove()
     * to actually make the computer move oto that location.
     * @return The best move fot the computer to make (0-8).
     */
    public int getComputerMove(){
        int move;

        // First see if there's a move O can make to win
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] == OPEN_SPOT) {
                char curr = mBoard[i];
                mBoard[i] = COMPUTER_PLAYER;
                if (checkForWinner() == 3) {
//                    System.out.println("Computer is moving to " + (i + 1));
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        // See if there's a move O can make to block X from winning
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (mBoard[i] == OPEN_SPOT) {
                char curr = mBoard[i];   // Save the current number
                mBoard[i] = HUMAN_PLAYER;
                if (checkForWinner() == 2) {
                    mBoard[i] = COMPUTER_PLAYER;
//                    System.out.println("Computer is moving to " + (i + 1));
                    return i;
                }
                else
                    mBoard[i] = curr;
            }
        }

        // Generate random move
        do{
        move = mRand.nextInt(BOARD_SIZE);
        }while(mBoard[move]!=OPEN_SPOT);
        return move;
    }
    /**
     * Check for a winner and return a status value indicating whi has won.
     * @return Return 0 if no winner or tie yet, 1 if it is a tie, 2 if X won,
     * or 3 if O won.
     */
    public int checkForWinner(){

        for (int i = 0; i <= 6; i += 3)	{
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+1] == HUMAN_PLAYER &&
                    mBoard[i+2]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+1]== COMPUTER_PLAYER &&
                    mBoard[i+2] == COMPUTER_PLAYER)
                return 3;
        }

        // Check vertical wins
        for (int i = 0; i <= 2; i++) {
            if (mBoard[i] == HUMAN_PLAYER &&
                    mBoard[i+3] == HUMAN_PLAYER &&
                    mBoard[i+6]== HUMAN_PLAYER)
                return 2;
            if (mBoard[i] == COMPUTER_PLAYER &&
                    mBoard[i+3] == COMPUTER_PLAYER &&
                    mBoard[i+6]== COMPUTER_PLAYER)
                return 3;
        }

        // Check for diagonal wins
        if ((mBoard[0] == HUMAN_PLAYER &&
                mBoard[4] == HUMAN_PLAYER &&
                mBoard[8] == HUMAN_PLAYER) ||
                (mBoard[2] == HUMAN_PLAYER &&
                        mBoard[4] == HUMAN_PLAYER &&
                        mBoard[6] == HUMAN_PLAYER))
            return 2;
        if ((mBoard[0] == COMPUTER_PLAYER &&
                mBoard[4] == COMPUTER_PLAYER &&
                mBoard[8] == COMPUTER_PLAYER) ||
                (mBoard[2] == COMPUTER_PLAYER &&
                        mBoard[4] == COMPUTER_PLAYER &&
                        mBoard[6] == COMPUTER_PLAYER))
            return 3;

        // Check for tie
        for (int i = 0; i < BOARD_SIZE; i++) {
            // If we find a number, then no one has won yet
            if (mBoard[i] == OPEN_SPOT)
                return 0;
        }

        // If we make it through the previous loop, all places are taken, so it's a tie
        return 1;

    }



}