package sample;

import sample.Model.Algorithms.Algorithm;
import sample.Model.Algorithms.RecursiveBacktrackerAlgorithm;
import sample.Model.Structures.Maze;
import sample.Model.Structures.Problem;

public class Test {
    public static void main(String[] args) {
        // Create the maze.
        Maze maze = new Maze(10, 10);

        // Create the problem.
        Problem pb = new Problem(maze);

        // The solver.
        Algorithm solver = new RecursiveBacktrackerAlgorithm(pb);

        while(true) {
            solver.transition();
            System.out.println(solver.getProblem());
        }
    }
}
