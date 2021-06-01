package sample.Model.Structures._3D;

import java.util.Arrays;

public class Cell {
    private int i; // Row number.
    private int j; // Col number.
    private int k; // Level number.
    private boolean[] walls;
    private boolean visited;

    public Cell(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;

        //                         {front, right, back, left, above, below}
        this.walls = new boolean[]{ true,  true,  true, true, false,  false};

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

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public boolean[] getWalls() {
        return walls;
    }

    public void hideWall(int index) {
        if(index >= 0 && index <= 5) {
            this.walls[index] = false;
        }
    }

    public void makeStair() {
        this.walls[4] = true;
    }

    public void makeHole() {
        this.walls[5] = true;
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
                ", k=" + k +
                '}';
    }
}
