package hw6;

import java.util.*;

/**
 * A class that implements a breadth-first search algorithm
 * for finding the Graphs for which the isGoal predicate holds
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.3
 * @date 28-02-2013
 */
public class Solver
{
    // A queue for maintaining graphs that are not visited yet.
    Queue<Graph>        toExamine;

    public Solver(Graph g) {
        throw new UnsupportedOperationException("Solver : not supported yet.");        
    }
    
    /* A skeleton implementation of the solver
     * @return a string representation of the solution
     */
    public String solve () {
        while (! toExamine.isEmpty() ) {
            Graph next = toExamine.remove();
            if (next.isGoal()) {
                return "Succes!";
            } else {
                for (Graph succ: next.successors()) {
                    toExamine.add  (succ);
                }
            }
        }
        return "Failure!";
    }
    
}
