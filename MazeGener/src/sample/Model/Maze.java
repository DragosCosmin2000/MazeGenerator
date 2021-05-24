package sample.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Maze {
    private int nrOfRows;
    private int nrOfCols;
    private List<Cell> grid;
    private Cell currentCell;

    public Maze(int nrOfRows, int nrOfCols) {
        this.nrOfRows = nrOfRows;
        this.nrOfCols = nrOfCols;

        // Create the grid.
        this.grid = new ArrayList<>();
        for(int y = 0; y < nrOfRows; y++) {
            for(int x = 0; x < nrOfCols; x++) {
                this.grid.add(new Cell(x, y));
            }
        }

        // Set the initial cell. We can start anywhere. Let it (0, 0) for the moment.
        this.currentCell = grid.get(0 * nrOfRows + 0);
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

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public int getGridIndex(int i, int j) {
        return i + j * nrOfCols;
    }

    public boolean checkLocation(int i, int j) {
        if(i < 0 || j < 0 || i > nrOfCols || j > nrOfRows - 1) {
            return false;
        }

        return true;
    }

    public void pushNeighbour(int i, int j, List<Cell> neighbours) {
        if(checkLocation(i, j)) {
            Cell neighbour = grid.get(getGridIndex(i, j));
            if(!neighbour.isVisited()) {
                neighbours.add(neighbour);
            }
        }
    }

    public Cell checkCellNeighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();

        // Top Neighbour.
        pushNeighbour(
                cell.getI(),
                cell.getJ() - 1,
                neighbours
        );

        // Right Neighbour.
        pushNeighbour(
                cell.getI() + 1,
                cell.getJ(),
                neighbours
        );

        // Bottom Neighbour.
        pushNeighbour(
                cell.getI(),
                cell.getJ() + 1,
                neighbours
        );

        // Left Neighbour.
        pushNeighbour(
                cell.getI() - 1,
                cell.getJ(),
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
        return "Maze{" +
                "nrOfRows=" + nrOfRows +
                ", nrOfCols=" + nrOfCols +
                ", grid=" + grid +
                ", currentCell=" + currentCell +
                '}';
    }
}
