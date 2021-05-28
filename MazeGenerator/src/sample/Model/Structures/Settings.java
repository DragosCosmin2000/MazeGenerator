package sample.Model.Structures;

import javafx.scene.paint.Color;

public class Settings {
    private int dimension;
    private Color colorTheme;
    private int cellDimension;
    private int animationSpeed;

    public Settings(int dimension, Color colorTheme) {
        this.dimension = dimension;
        this.colorTheme = colorTheme;

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

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
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
