package sample.Model.Structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cell {
    private int i; // Row number.
    private int j; // Col number.
    private boolean[] walls;
    private boolean visited;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;

        //                         {top,  left, bottom, right}
        this.walls = new boolean[]{ true, true,  true,  true};

        // Set it as unvisited.
        this.visited = false;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        if(i >= 0) {
            this.i = i;
        }
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        if(j >= 0) {
            this.j = j;
        }
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void hideWall(int index) {
        if(index >= 0 && index <= 3) {
            this.walls[index] = false;
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
