package sample.Model.Algorithms;

import sample.Model.Cell;
import sample.Model.Problem;

public class RecursiveBacktrackerAlgorithm extends Algorithm{
    public RecursiveBacktrackerAlgorithm(Problem problem) {
        super(problem);
    }

    @Override
    public void transition() {
        // Make the current cell visited.
        Cell current = problem.getMaze().getCurrentCell();
        current.setVisited(true);

        // Get a random unvisited neighbour and make it the current cell.
        Cell next = problem.getMaze().checkCellNeighbours(current);

        if(next != null) {
            // next.setVisited(true);
            problem.push(current);

            // Remove the walls.
            removeWalls(current, next);

            // Change current cell.
            current = next;
        }
        else if(problem.getStackLength() > 0) {
            // If it is stuck, pop a cell from the stack.
            current = problem.pop();
        }
    }

    private void removeWalls(Cell current, Cell next) {
        int x = current.getI() - next.getI();
        // Remove current's left wall and next's right wall.
        if(x == 1) {
            current.hideWall(3);
            next.hideWall(1);
        }
        // Remove current's right wall and next's left wall.
        else if(x == -1) {
            current.hideWall(1);
            next.hideWall(3);
        }

        int y = current.getJ() - next.getJ();
        // Remove current's top wall and next's bottom wall.
        if(y == 1) {
            current.hideWall(0);
            next.hideWall(2);
        }
        // Remove current's bottom wall and next's top wall.
        else if(y == -1) {
            current.hideWall(2);
            next.hideWall(0);
        }
    }
}
