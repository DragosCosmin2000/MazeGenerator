package sample.Model.Structures;

import javafx.scene.paint.Color;
import sample.Model.Structures._2D.Cell;

import java.util.List;

public class Settings {
    public boolean importer;
    private int dimension;
    private Color colorTheme;
    private int cellDimension;
    private int animationSpeed;
    private List<Cell> cellList;

    public Settings() {
    }

    public Settings(int dimension, Color colorTheme, boolean importer, List<Cell> cellList) {
        this.dimension = dimension;
        this.colorTheme = colorTheme;
        this.importer = importer;
        this.cellList = cellList;

        // Calculate cellDimension. 600 is the dimension of the MazePane.
        cellDimension = (int)(600 / dimension);

        // Set animation speed.
        // Minimum speed is 1. We'll divide 1000(1 second) to animationSpeed.
        // Example: To make the animation 2 times faster you need to double the value of <animationSpeed>.
        animationSpeed = 1;

    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public Color getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(Color colorTheme) {
        this.colorTheme = colorTheme;
    }

    public int getCellDimension() {
        return cellDimension;
    }

    public void setCellDimension(int cellDimension) {
        this.cellDimension = cellDimension;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public boolean isImporter() {
        return importer;
    }

    public void setImporter(boolean importer) {
        this.importer = importer;
    }

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "dimension=" + dimension +
                ", colorTheme=" + colorTheme +
                ", cellDimension=" + cellDimension +
                ", animationSpeed=" + animationSpeed +
                '}';
    }
}
