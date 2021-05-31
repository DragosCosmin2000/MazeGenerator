package sample.Model.Structures._3D;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private List<Level> levels;

    private Cell currentCell;

    public Maze() {
        levels = new ArrayList<>();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }
}
