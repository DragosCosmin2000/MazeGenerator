package sample.Model.Algorithms;

import sample.Model.Structures._2D.Cell;
import sample.Model.Structures._2D.Problem;

public class HuntAndKillAlgorithm extends Algorithm{

    public HuntAndKillAlgorithm(Problem problem) {
        super(problem);
    }
    @Override
    public void transition() {

        Cell current = problem.getMaze().getCurrentCell();
        current.setVisited(true);

        // Get a random unvisited neighbour and make it the current cell.
        Cell next = problem.getMaze().checkCellNeighbours(current);

        if(next != null) {
            next.setVisited(true);
            problem.push(current);

            // Remove the walls.
            removeWalls(current, next);

            // Change current cell.
            problem.getMaze().setCurrentCell(next);
        }
        else {
            for(Cell cell:problem.getMaze().getGrid()) {
                Cell aux= problem.getMaze().takeAnVisitedNeighbours(cell);
                if (!cell.isVisited() &&  aux != null) {
                    problem.getMaze().setCurrentCell(cell);
                    removeWalls(cell, aux );
                    break;
                }
            }
        }
    }

    private void removeWalls(Cell current, Cell next) {
        int x = current.getI() - next.getI();
        // Remove current's top wall and next's bottom wall.
        if(x == 1) {
            current.hideWall(0);
            next.hideWall(2);
        }
        // Remove current's bottom wall and next's top wall.
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
    }
}
