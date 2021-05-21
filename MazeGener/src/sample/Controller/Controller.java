package sample.Controller;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Model.Maze;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    @FXML
    public Button backButton;
    @FXML
    public javafx.scene.control.Button generateButton;
    @FXML
    public CheckBox checkbox;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Spinner spinner;
    @FXML
    private Pane pane;
    @FXML
    private Button slow;
    JavaFXController fiboCalcTask;
    private int counter;

    private static int numarPatratele;
    private static Color culoare;
    private static double width;
    private static double height;

    public static void updateTable() {
        System.out.println("Update table...");
    }

    public void generateMaze(ActionEvent actionEvent) throws IOException {

        numarPatratele = (int) spinner.getValue();
        culoare = colorPicker.getValue();

        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\main.fxml"));
        Stage window = (Stage) generateButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("..\\View\\menu.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    public void makePane(ActionEvent actionEvent) throws IOException {
        if(counter==0) {
            Maze maze = new Maze(numarPatratele,numarPatratele,0,0);
            ExecutorService executorService
                    = Executors.newFixedThreadPool(1);
            fiboCalcTask = new JavaFXController(pane, numarPatratele, culoare,maze);
            executorService.execute(fiboCalcTask);
            executorService.shutdown();
        }
        if(counter%2==0){
            System.out.println("counter%2==0  "+counter);
            fiboCalcTask.changePause(false);
        }
        else{
            System.out.println("counter%2==1  "+counter);
            fiboCalcTask.changePause(true);
        }
        counter++;
    }

    public void slowAction(ActionEvent actionEvent) {
        fiboCalcTask.setSlowmo(fiboCalcTask.getSlowmo()+1);
    }
}
