package sample;

import sample.Model.Algorithm;
import sample.Model.Maze;
import sample.Model.Problem;
import sample.Model.RecursiveBacktrackerAlgorithm;

public class Test {
    public static void main(String[] args) {
        Integer startI = 0;
        Integer startJ = 0;
        Maze m = new Maze(5, 5, startI, startJ);
        Problem p = new Problem(m);

        Algorithm solver = new RecursiveBacktrackerAlgorithm(p);

        solver.solve();
    }
}
