package sample.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Model.Models.MainModel;
import sample.Model.Structures.Maze;
import sample.Model.Structures.Settings;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaFXController {
    @FXML
    public Button generateButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button startStopButton;

    // Speed changers.
    @FXML
    private Button normalSpeedButton;
    @FXML
    private Button x2SpeedButton;
    @FXML
    private Button x4SpeedButton;
    @FXML
    private Button x8SpeedButton;
    @FXML
    private Button maxSpeedButton;
    @FXML
    public CheckBox checkbox;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Spinner spinner;
    @FXML
    private Pane mazePane;

    // To create a new thread in order to run the animation.
    private ExecutorService executorService;
    private MainModel mainModel;

    private static Settings settings;

    public JavaFXController() {
        mainModel = new MainModel();
    }

    public void generateMaze(ActionEvent actionEvent) throws IOException {
        // Get the settings.
        settings = new Settings(
                (int) spinner.getValue(),
                colorPicker.getValue()
        );

        // Change to main scene.
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/sample/View/main.fxml"));
        Stage window = (Stage) generateButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        // Stop the thread.
        if(mainModel != null) {
            mainModel.stopThread();
        }

        if(executorService != null) {
            executorService.shutdownNow();
        }

        // Change to menu scene.
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/menu.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void exitApp(ActionEvent actionEvent) {
        // Stop the thread.
        if(mainModel != null) {
            mainModel.stopThread();
        }

        if(executorService != null) {
            executorService.shutdownNow();
        }

        // Exit the app.
        Platform.exit();
    }

    public void startStopAnimation(ActionEvent actionEvent) throws IOException {
        if(mainModel.getRunningAnimationFlag() == 0) {
            System.out.println("FIRST CLICK"); // de scos

            // Send data to model.
            mainModel.configureModel(settings, mazePane);

            // Create a new thread.
            executorService = Executors.newFixedThreadPool(1);

            // Start it.
            executorService.execute(mainModel);
            // executorService.shutdown(); // Vad ca ajunge aici imediat dupa execute si nu face absolut nimic sau n am vazut eu ca face ceva.

            // Set the flag to 2 in order to pause the animation at the next press.
            mainModel.setRunningAnimationFlag(2);
        }
        else if(mainModel.getRunningAnimationFlag() == 1){
            System.out.println("RUNNING"); // de scos
            // Continue the animation.
            mainModel.changePause(false);

            // Set the flag to 2. If the button will be pressed again the animation will pause.
            mainModel.setRunningAnimationFlag(2);
        }
        else if(mainModel.getRunningAnimationFlag() == 2){
            System.out.println("PAUSE"); // de scos
            // Pause the animation.
            mainModel.changePause(true);

            // Set the flag to 1 in order to start the animation at the next press.
            mainModel.setRunningAnimationFlag(1);
        }
    }

    public void setToNormalSpeed() {
        settings.setAnimationSpeed(1);
    }

    public void setTox2Speed() {
        settings.setAnimationSpeed(2);
    }

    public void setTox4Speed() {
        settings.setAnimationSpeed(4);
    }

    public void setTox8Speed() {
        settings.setAnimationSpeed(8);
    }

    public void setToMaxSpeed() {
        settings.setAnimationSpeed(100);
    }

    public void saveMaze() {
        // TODO.
        mainModel.saveMaze();
    }
}