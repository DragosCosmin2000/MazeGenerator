package sample.Model.Algorithms;

import sample.Model.Structures._3D.Cell;
import sample.Model.Structures._3D.Problem;

public class RecursiveBacktracker3DAlgorithm extends Algorithm3D{
    public RecursiveBacktracker3DAlgorithm(Problem problem) {
        super(problem);
    }

    @Override
    public void transition() {
        // Make the current cell visited.
        Cell current = problem.getMaze().getCurrentCell();
        current.setVisited(true);

        // Get a random unvisited neighbour and make it the current cell.
        Cell next = problem.getMaze().getLevels().get(current.getK()).checkCellNeighbours(current);

        if(next != null) {
            next.setVisited(true);
            problem.push(current);

            // Remove the walls.
            removeWalls(current, next);

            // Change current cell.
            problem.getMaze().setCurrentCell(next);
        }
        else if(problem.getStackLength() > 0) {
            // If it is stuck, pop a cell from the stack.
            problem.getMaze().setCurrentCell(problem.pop());
        }
    }

    private void removeWalls(Cell current, Cell next) {
        int x = current.getI() - next.getI();
        // Remove current's front wall and next's back wall.
        if(x == 1) {
            current.hideWall(0);
            next.hideWall(2);
        }
        // Remove current's back wall and next's front wall.
        else if(x == -1) {
            current.hideWall(2);
            next.hideWall(0);
        }

        int y = current.getJ() - next.getJ();
        // Remove current's left wall and next's right wall.
        if(y == 1) {
            current.hideWall(3);
            next.hideWall(1);
        }
        // Remove current's right wall and next's left wall.
        else if(y == -1) {
            current.hideWall(1);
            next.hideWall(3);
        }

        int z = current.getK() - next.getK();
        // Create a hole from current to next.
        if(z == 1) {
            current.makeHole();
        }
        // Create a stair from current to next.
        else if(z == -1) {
            current.makeStair();
        }
    }
}
