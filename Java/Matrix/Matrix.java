package Java.Matrix;

class Matrix {


    /* 419. Battleships in a Board
    Given an 2D board, count how many battleships are in it.
    The battleships are represented with 'X's,
    empty slots are represented with '.'s.

    You may assume the following rules:
    You receive a valid board, made of only battleships or empty slots.
    Battleships can only be placed horizontally or vertically.
    In other words, they can only be made of the shape 1xN
    (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
    At least one horizontal or vertical cell separates between
    two battleships - there are no adjacent battleships.
    */

    public int countBattleships(char[][] board) {
        if(board.length == 0) return 0;
        int rows = board.length, cols = board[0].length, count = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(board[row][col] == 'X' &&
                (row == 0 || board[row - 1][col] != 'X') &&
                (col == 0 || board[row][col - 1] != 'X')) {
                    count++;
                }
            }
        }
        return count;
    }

    /* 348. Design Tic-Tac-Toe

    Design a Tic-tac-toe game that is played between two players on a n x n grid.

    You may assume the following rules:

    A move is guaranteed to be valid and is placed on an empty block.
    Once a winning condition is reached, no more moves is allowed.

    A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
    */


    public static void main(String[] args) {
        System.out.println("hellow");
    }
}
