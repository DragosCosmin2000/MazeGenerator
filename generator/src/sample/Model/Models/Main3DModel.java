package sample.Model.Models;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import sample.Model.Algorithms.Algorithm3D;
import sample.Model.Algorithms.RecursiveBacktracker3DAlgorithm;
import sample.Model.Structures._3D.Cell;
import sample.Model.Structures._3D.Level;
import sample.Model.Structures._3D.Maze;
import sample.Model.Structures._3D.Problem;
import sample.Model.Structures.Settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main3DModel extends Task<Long> {
    // For Start/Stop button. To know when is running and when is not running.
    // 0 - We'll need to create a new Controller and a new Executor using the settings.
    // 1 - Start the animation.
    // 2 - Stop the animation.
    private int runningAnimationFlag;

    // To know when to end the thread.
    private boolean runningFlag;

    // App's settings.
    private Settings settings;

    // Where the levels are gonna be placed.
    List<Pane> panes = new ArrayList<>();

    // Pause flag.
    private boolean pause;

    // Import objects for maze and solver.
    private Maze maze;
    private Problem pb;
    private Algorithm3D solver;

    // Show cell's numbers flag. See JavaFxController constructor for more info.
    private boolean showNumbersFlag;

    public Main3DModel() {
    }

    public void configureModel(
                Settings settings,
                Pane level1Pane,
                Pane level2Pane,
                Pane level3Pane,
                Pane level4Pane
    ) {
        // Get the settings.
        this.settings = settings;

        this.settings.setCellDimension((int)(285 / this.settings.getDimension()));

        // Get the panes.
        panes.add(level1Pane);
        panes.add(level2Pane);
        panes.add(level3Pane);
        panes.add(level4Pane);

        // Reset runningAnimationFlag.
        runningAnimationFlag = 0;

        // Create a new maze.
        int nrOfLevels = 4;

        maze = new Maze();

        // I need to pass it to every level to be able to check neighbours.
        List<Level> tempLevels = new ArrayList<>();
        tempLevels.add(null);
        tempLevels.add(null);
        tempLevels.add(null);
        tempLevels.add(null);

        for(int i = 0; i < 4; i++) {
            tempLevels.set(i, new Level(settings.getDimension(), settings.getDimension(), i, tempLevels));
            maze.getLevels().add(tempLevels.get(i));
        }

        Level firstLevel = maze.getLevels().get(0);
        maze.setCurrentCell(firstLevel.getGrid().get(0));

        // By default. Because they will need these values when the start/stop button will be pressed for the first time.
        runningFlag = true;
        pause = false;

        pb = new Problem(maze);

        // The solver.
        solver = new RecursiveBacktracker3DAlgorithm(pb);

        showNumbersFlag = false;
    }

    @Override
    protected Long call() throws Exception {
        while(true) {
            // Used to slow down the animation.
            Thread.sleep((int) (1000 / settings.getAnimationSpeed()));

            if (!pause) {
                // Run the animation.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // The animation is too "expensive", so for max speed I will not display the progress.
                        if(settings.getAnimationSpeed() != 100) {
                            for(int i = 0; i < maze.getLevels().size(); i++) {
                                GridPane gridPane;
                                panes.get(i).getChildren().clear();
                                gridPane = draw(i);
                                panes.get(i).getChildren().add(gridPane);
                            }
                        }

                        // Edit the current state of the maze.
                        solver.transition();

                        maze = solver.getProblem().getMaze();
                    }
                });
            }

            if (!runningFlag) {
                break;
            }
        }
        return 100l;
    }

    public void stopThread() {
        runningFlag = false;
    }

    public void changePause(Boolean pause){
        this.pause=pause;
    }

    public void ChangeSpeed(int newAnimationSpeed) {
        settings.setAnimationSpeed(newAnimationSpeed);
    }

    public GridPane draw(int i) {
        GridPane gridPane = new GridPane();

        int margin = (285 - settings.getCellDimension() * settings.getDimension()) / 2;
        gridPane.setLayoutX(margin);
        gridPane.setLayoutY(margin);
        for(Cell cell : maze.getLevels().get(i).getGrid()) {
            gridPane.getChildren().add(show(cell));
        }

        return gridPane;
    }

    public Node show(Cell cell) {
        Pane pane = new Pane();

        double x = cell.getJ() * settings.getCellDimension(); // Cat de "in dreapta" e.
        double y = cell.getI() * settings.getCellDimension(); // Cat de "in jos" e.

        // Stair and hole
        Line stair = null;
        Circle hole = null;

        // The walls.
        Line line1 = null;
        Line line2 = null;
        Line line3 = null;
        Line line4 = null;
        boolean[] walls = cell.getWalls();
        if(walls[0]) { // front
            line1 = new Line(
                    x,
                    y,
                    x + settings.getCellDimension(),
                    y
            );
            line1.setStroke(settings.getColorTheme());
        }

        if(walls[1]) { // right
            line2 = new Line(
                    x + settings.getCellDimension(),
                    y,
                    x + settings.getCellDimension(),
                    y + settings.getCellDimension()
            );
            line2.setStroke(settings.getColorTheme());
        }

        if(walls[2]) { // back
            line3 = new Line(
                    x,
                    y + settings.getCellDimension(),
                    x + settings.getCellDimension(),
                    y + settings.getCellDimension()
            );
            line3.setStroke(settings.getColorTheme());
        }

        if(walls[3]) { // left
            line4 = new Line(
                    x,
                    y,
                    x,
                    y + settings.getCellDimension());
            line4.setStroke(settings.getColorTheme());
        }

        if(walls[4]) { // above
            // Draw stair(a diagonally line).
            stair = new Line(
                    x + 5,
                    y + 5,
                    x + settings.getCellDimension() - 5,
                    y + settings.getCellDimension() - 5);
            stair.setStroke(settings.getColorTheme());
        }

        if(walls[5]) { // below
            // Draw hole(a circle).
            int cellDimension = settings.getCellDimension();
            hole = new Circle(x + cellDimension / 2, y + cellDimension / 2, cellDimension / 2 - 2);
            hole.setStroke(settings.getColorTheme());
            hole.setFill(null);
        }

        double rectX = x;
        double rectY = y;

        if(showNumbersFlag) {
            rectX = 0;
            rectY = 0;
        }
        // Cell's background.
        Rectangle rectangle = new Rectangle(
                rectX,
                rectY,
                settings.getCellDimension() + 1,
                settings.getCellDimension() + 1
        );

        rectangle.setStyle("-fx-border: none");

        Cell current = maze.getCurrentCell();

        if(cell.isVisited()) {
            if(cell.getI() == current.getI() && cell.getJ() == current.getJ() && cell.getK() == current.getK()) {
                rectangle.setFill(Color.GRAY);
            }
            else {
                rectangle.setFill(Color.LIGHTGRAY);
            }
        }
        else {
            rectangle.setFill(Color.WHITE);
        }

        if(showNumbersFlag) {
            Text text = new Text(
                    String.valueOf(
                            cell.getI() * settings.getDimension() + cell.getJ()
                    )
            );
            StackPane stackPane = new StackPane();
            stackPane.setLayoutX(x);
            stackPane.setLayoutY(y);
            stackPane.setPrefSize(
                    settings.getCellDimension() + 1,
                    settings.getCellDimension() + 1
            );

            stackPane.getChildren().addAll(rectangle, text);
            pane.getChildren().add(stackPane);
        }
        else {
            pane.getChildren().add(rectangle);
        }

        if(line1 != null) {
            pane.getChildren().add(line1);
        }

        if(line2 != null) {
            pane.getChildren().add(line2);
        }

        if(line3 != null) {
            pane.getChildren().add(line3);
        }

        if(line4 != null) {
            pane.getChildren().add(line4);
        }

        if(stair != null) {
            pane.getChildren().add(stair);
        }

        if(hole != null) {
            pane.getChildren().add(hole);
        }

        return pane;
    }

    public int getRunningAnimationFlag() {
        return runningAnimationFlag;
    }

    public void setRunningAnimationFlag(int runningAnimationFlag) {
        this.runningAnimationFlag = runningAnimationFlag;
    }

    public boolean getShowNumbersFlag() {
        return showNumbersFlag;
    }

    public void setShowNumbersFlag(boolean showNumbersFlag) {
        this.showNumbersFlag = showNumbersFlag;
    }

    public Maze takeMaze(){
        return solver.getProblem().getMaze();
    }
}