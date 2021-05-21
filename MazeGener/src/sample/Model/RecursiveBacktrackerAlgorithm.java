package sample.Model;

import sample.Controller.Controller;

public class RecursiveBacktrackerAlgorithm extends Algorithm {

    public RecursiveBacktrackerAlgorithm(Problem problem) {
        super(problem);
    }

    @Override
    public void solve() {
        // Do things..

        // Marcam celula curenta ca fiind vizitata.
        problem.getMaze().getCurrentCell().setVisited(true);

        Cell nextCell = problem.getMaze().getCurrentCell().checkNeighbours(problem.getMaze().getTable());

        if(nextCell != null) {
            problem.getMaze().setCurrentCell(nextCell);
            nextCell.setVisited(true);
        }

        for(Cell[] cArr : problem.getMaze().getTable()) {
            for(Cell c : cArr) {
                System.out.println(c);
            }
        }
    }
}
