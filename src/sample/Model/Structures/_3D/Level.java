package sample.Model.Structures._3D;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int nrOfRows;
    private int nrOfCols;
    private int levelNumber;
    private List<Cell> grid;
    private List<Level> allLevels;

    public Level(int nrOfRows, int nrOfCols, int levelNumber, List<Level> allLevels) {
        this.nrOfRows = nrOfRows;
        this.nrOfCols = nrOfCols;
        this.levelNumber = levelNumber;

        // Create the grid.
        this.grid = new ArrayList<>();
        for(int i = 0; i < nrOfRows; i++) {
            for(int j = 0; j < nrOfCols; j++) {
                this.grid.add(new Cell(i, j, levelNumber));
            }
        }

        this.allLevels = allLevels;
    }

    public int getNrOfRows() {
        return nrOfRows;
    }

    public void setNrOfRows(int nrOfRows) {
        this.nrOfRows = nrOfRows;
    }

    public int getNrOfCols() {
        return nrOfCols;
    }

    public void setNrOfCols(int nrOfCols) {
        this.nrOfCols = nrOfCols;
    }

    public List<Cell> getGrid() {
        return grid;
    }

    public void setGrid(List<Cell> grid) {
        this.grid = grid;
    }

    public int getGridIndex(int i, int j) {
        return i * nrOfCols + j;
    }

    public boolean checkLocation(int i, int j, int k) {
        if(i < 0 || j < 0 || k < 0 || i >= nrOfRows || j >= nrOfCols || k >= allLevels.size()) {
            return false;
        }

        return true;
    }

    public Cell takeNeighbour(Cell cell){
        List<Cell> neighbours = new ArrayList<>();
        if(checkLocation(cell.getI(), cell.getJ()-1,cell.getK()))
            neighbours.add(new Cell(cell.getI(), cell.getJ()-1,cell.getK()));

        if(checkLocation(cell.getI()+1, cell.getJ(),cell.getK()))
            neighbours.add(new Cell(cell.getI()+1, cell.getJ(),cell.getK()));

        if(checkLocation(cell.getI(), cell.getJ()+1,cell.getK()))
            neighbours.add(new Cell(cell.getI(), cell.getJ()+1,cell.getK()));

        if(checkLocation(cell.getI()-1, cell.getJ(),cell.getK()))
            neighbours.add(new Cell(cell.getI()-1, cell.getJ(),cell.getK()));
        int random = (int)Math.floor(Math.random() * neighbours.size());
        return neighbours.get(random);

    }
    public void pushNeighbour(int i, int j, int k, List<Cell> neighbours) {
        if(checkLocation(i, j, k)) {
            Cell neighbour = allLevels.get(k).getGrid().get(getGridIndex(i, j));
            if(!neighbour.isVisited()) {
                neighbours.add(neighbour);
            }
        }
    }

    public Cell checkCellNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        // Front Neighbour.
        pushNeighbour(
                cell.getI(),
                cell.getJ() - 1,
                cell.getK(),
                neighbours
        );

        // Right Neighbour.
        pushNeighbour(
                cell.getI() + 1,
                cell.getJ(),
                cell.getK(),
                neighbours
        );

        // Back Neighbour.
        pushNeighbour(
                cell.getI(),
                cell.getJ() + 1,
                cell.getK(),
                neighbours
        );

        // Left Neighbour.
        pushNeighbour(
                cell.getI() - 1,
                cell.getJ(),
                cell.getK(),
                neighbours
        );

        // Above Neighbour.
        pushNeighbour(
                cell.getI(),
                cell.getJ(),
                cell.getK() + 1,
                neighbours
        );

        // Below Neighbour.
        pushNeighbour(
                cell.getI(),
                cell.getJ(),
                cell.getK() - 1,
                neighbours
        );

        if(neighbours.size() > 0) {
            // Pick a random neighbour.
            int random = (int)Math.floor(Math.random() * neighbours.size());
            return neighbours.get(random);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Level{" +
                "nrOfRows=" + nrOfRows +
                ", nrOfCols=" + nrOfCols +
                ", levelNumber=" + levelNumber +
                '}';
    }
}
