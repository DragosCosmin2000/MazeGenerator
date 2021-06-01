package sample.Model.Algorithms;

import sample.Model.Structures._2D.Cell;
import sample.Model.Structures._2D.Problem;

import java.util.List;

public class AldousBroderAlgorithm extends Algorithm{
    public AldousBroderAlgorithm(Problem problem) {
        super(problem);
    }


    @Override
    public void transition() {
        Cell current = problem.getMaze().getCurrentCell();
        problem.push(current);

        current.setVisited(true);
        boolean ok=false;
        for(Cell cell : problem.getMaze().getGrid()){
            if(cell.isVisited()==false)
                ok =true;
        }
        if(ok){
            Cell next = problem.getMaze().checkCellNeighbours(current);
            if(next!=null)
                if(!problem.verifica(next)){
                    problem.push(next);
                    removeWalls(current,next);
                    next.setVisited(true);
                    problem.getMaze().setCurrentCell(next);
                }
                else;
            else{
                problem.getMaze().setCurrentCell(problem.getMaze().takeNeighbour(current));
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
