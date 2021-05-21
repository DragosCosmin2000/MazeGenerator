package sample.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell {
    private Integer i;
    private Integer j;
    private boolean[] walls;
    private boolean visited;

    public Cell(Integer i, Integer j) {
        this.i = i;
        this.j = j;
        //                         {top,  left, bottom, right}
        this.walls = new boolean[]{ true, true,  true,  true};
        this.visited = false;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getJ() {
        return j;
    }

    public void setJ(Integer j) {
        this.j = j;
    }

    public boolean getTopWall() {
        return walls[0];
    }

    public boolean getRightWall() {
        return walls[1];
    }

    public boolean getBottomWall() {
        return walls[2];
    }

    public boolean getLeftWall() {
        return walls[3];
    }

    public void setTopWall(boolean newFlag) {
        walls[0] = newFlag;
    }

    public void setRightWall(boolean newFlag) {
        walls[1] = newFlag;
    }

    public void setBottomWall(boolean newFlag) {
        walls[2] = newFlag;
    }

    public void setLeftWall(boolean newFlag) {
        walls[3] = newFlag;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public static boolean checkLocation(Integer i, Integer j, Integer cols, Integer rows) {
        if(i < 0 || j < 0 || i > rows - 1 || j > cols - 1) {
            return false;
        }
        return true;
    }

    public Cell checkNeighbours(Cell[][] table) {
        List<Cell> unvisitedCells = new ArrayList<>();

        Integer rows = table.length;
        Integer cols = table[0].length;

        Integer i;
        Integer j;

        // Top cell.
        i = this.getI() - 1;
        j = this.getJ();

        // Daca locatia exista si inca nu a fost vizitata.
        if(checkLocation(i, j, rows, cols) && !table[i][j].isVisited()) {
            unvisitedCells.add(table[i][j]);
        }

        // Right cell.
        i = this.getI();
        j = this.getJ() + 1;

        // Daca locatia exista si inca nu a fost vizitata.
        if(checkLocation(i, j, rows, cols) && !table[i][j].isVisited()) {
            unvisitedCells.add(table[i][j]);
        }

        // Bottom cell.
        i = this.getI() + 1;
        j = this.getJ();

        // Daca locatia exista si inca nu a fost vizitata.
        if(checkLocation(i, j, rows, cols) && !table[i][j].isVisited()) {
            unvisitedCells.add(table[i][j]);
        }

        // Left cell.
        i = this.getI();
        j = this.getJ() - 1;

        // Daca locatia exista si inca nu a fost vizitata.
        if(checkLocation(i, j, rows, cols) && !table[i][j].isVisited()) {
            unvisitedCells.add(table[i][j]);
        }

        if(unvisitedCells.size() > 0) {
            Integer randomCell = (int) (Math.random() * unvisitedCells.size());
            return unvisitedCells.get(randomCell);
        }
        else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Cell{" +
                "i=" + i +
                ", j=" + j +
                ", walls=" + Arrays.toString(walls) +
                '}';
    }
}
