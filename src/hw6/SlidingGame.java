package hw6;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.1
 * @date 28-02-2013
 * A template implementation of a sliding game also
 * implementing the Graph interface
 */
public class SlidingGame implements Graph
{
    public static final int N = 3, SIZE = N * N, HOLE = SIZE;
    /*
     * The bord is represented by a 2-dimensional arrray;
     * the position of the hole is kept in 2 variables holeX and holeY
     */
    private int board [][];
    private int holeX, holeY;
    
    /*
     * A constructor that intiializes the board with the specified array
     * @param start: a one dimensional array containing the intial board.
     * The elements of start are stored row-wise.
     */
    public SlidingGame (int [] start) {
        board = new int [N][N];
        assert start.length == N*N: "Length of specified board incorrect";
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                board[col][row] = start[row*N+col];
                if (board[col][row] == HOLE) {
                    holeX = col;
                    holeY = row;
                }
            }
        }
    }    

    /*
     * Converts a bord into a printable representation.
     * The hole is displayed as a space
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int puzzel = board[col][row];
                buf.append(puzzel == HOLE ? "  " : puzzel + " ");                
            }
            buf.append("\n");
        }
        return buf.toString();
    }
     
    /*
     * a standard implementation of equals checking
     * whether 2 boards are filled in exactly the same way
     * @return a boolean indicating equality
     */
   @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        } else {
            SlidingGame other_puzzle = (SlidingGame) o;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (board[col][row] != other_puzzle.board[col][row]) {
                        return false;
                    }
                }
            }
            return true;
        }
 	}

    @Override
    public boolean isGoal () {
        throw new UnsupportedOperationException("isGoal : not supported yet.");        
 	}

    @Override
    public Collection<Graph> successors () {
         throw new UnsupportedOperationException("successors : not supported yet.");        
    }

    @Override
    public int compareTo(Graph g) {
        throw new UnsupportedOperationException("compareTo : not supported yet.");        
     }

}
