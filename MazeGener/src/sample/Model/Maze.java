package sample.Model;

import java.util.Arrays;

public class Maze {
    private Integer cols;
    private Integer rows;
    private Cell[][] table;
    private Cell currentCell;

    public Maze(Integer rows, Integer cols, Integer currentCellXPos, Integer currentCellYPos) {
        this.rows = rows;
        this.cols = cols;
        table = new Cell[rows][cols];

        // Crearea celulelor.
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                table[i][j] = new Cell(i, j);
            }
        }

        this.currentCell = table[currentCellYPos][currentCellXPos];
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell[][] getTable() {
        return table;
    }

    public void setTable(Cell[][] table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return "Maze{" +
                "\n\tcols=" + cols +
                "\n\trows=" + rows +
                "\n\ttable=" + Arrays.toString(table) +
                "\n\tcurrentCell=" + currentCell +
                '}';
    }
}
