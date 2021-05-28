package sample.Model.Models;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sample.Model.Algorithms.Algorithm;
import sample.Model.Algorithms.RecursiveBacktrackerAlgorithm;
import sample.Model.Structures.Cell;
import sample.Model.Structures.Maze;
import sample.Model.Structures.Problem;
import sample.Model.Structures.Settings;

import java.util.Random;

public class MainModel extends Task<Long> {
    // For Start/Stop button. To know when is running and when is not running.
    // 0 - We'll need to create a new Controller and a new Executor using the settings.
    // 1 - Start the animation.
    // 2 - Stop the animation.
    private int runningAnimationFlag;

    // To know when to end the thread.
    private boolean runningFlag;

    // App's settings.
    private Settings settings;

    // Where the maze is gonna be placed.
    private Pane mazePane;

    // Pause flag.
    private boolean pause;

    // Import objects for maze and solver.
    private Maze maze;
    private Problem pb;
    private Algorithm solver;

    public MainModel() {
    }

    public void configureModel(Settings settings, Pane mazePane) {
        // Get the settings.
        this.settings = settings;

        // Get the pane.
        this.mazePane = mazePane;

        // Reset runningAnimationFlag.
        runningAnimationFlag = 0;

        // Create a new maze.
        maze = new Maze(settings.getDimension(), settings.getDimension());

        // By default. Because they will need these values when the start/stop button will be pressed for the first time.
        runningFlag = true;
        pause = false;

        pb = new Problem(maze);
        // Daca implementam mai multi algoritmi atunci asta o sa trebuiasca s o setam dinamic.
        // The solver.
        solver = new RecursiveBacktrackerAlgorithm(pb);
    }

    @Override
    protected Long call() throws Exception {
        // Create the problem.
        while(true) {
            // Used to slow down the animation.
            Thread.sleep((int) (1000 / settings.getAnimationSpeed()));

            if(!pause) {
                // Run the animation.
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GridPane gridPane;
                        mazePane.getChildren().clear();
                        gridPane = draw();
                        mazePane.getChildren().add(gridPane);

                        // Edit the current state of the maze.
                        solver.transition();

                        maze = solver.getProblem().getMaze();
                    }
                });
            }

            if(!runningFlag) {
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

    public GridPane draw(){
        GridPane gridPane = new GridPane();
        int margin = (600 - settings.getCellDimension() * settings.getDimension()) / 2;
        gridPane.setLayoutX(margin);
        gridPane.setLayoutY(margin);
        for(Cell cell : maze.getGrid()) {
            gridPane.getChildren().add(show(cell));
        }

        return gridPane;
    }

    public Node show(Cell cell) {
        //startX = startX / 2 - (lungimeCelula * numarPatratele) / 2;
        //startY = startY / 2 - (lungimeCelula * numarPatratele) / 2;
        double x = cell.getJ() * settings.getCellDimension(); // Cat de "in dreapta" e.
        double y = cell.getI() * settings.getCellDimension(); // Cat de "in jos" e.

        // The walls.
        Line line1 = null;
        Line line2 = null;
        Line line3 = null;
        Line line4 = null;
        boolean[] walls = cell.getWalls();
        if(walls[0]) { // top
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

        if(walls[2]) { // bottom
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

        // Cell's background.
        Rectangle rectangle = new Rectangle(
                x,
                y,
                settings.getCellDimension() + 1,
                settings.getCellDimension() + 1
        );

        rectangle.setStyle("-fx-border: none");

        Cell current = maze.getCurrentCell();

        if(cell.isVisited()) {
            if(cell.getI() == current.getI() && cell.getJ() == current.getJ()) {
                rectangle.setFill(Color.GRAY);
            }
            else {
                rectangle.setFill(Color.LIGHTGRAY);
            }
        }
        else {
            rectangle.setFill(Color.WHITE);
        }

        Pane pane = new Pane();
        if(rectangle != null) {
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

        return pane;
    }

    public int getRunningAnimationFlag() {
        return runningAnimationFlag;
    }

    public void setRunningAnimationFlag(int runningAnimationFlag) {
        this.runningAnimationFlag = runningAnimationFlag;
    }

    public void saveMaze() {

    }
}