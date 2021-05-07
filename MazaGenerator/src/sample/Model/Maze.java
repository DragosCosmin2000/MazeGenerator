package sample.Model;

public class Maze {
    private Integer cols;
    private Integer rows;
    private Cell[][] table;

    public Maze(Integer rows, Integer cols) {
        this.rows = rows;
        this.cols = cols;
        table = new Cell[rows][cols];

        // Crearea celulelor.
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                table[i][j] = new Cell(i, j);
            }
        }
    }
}
