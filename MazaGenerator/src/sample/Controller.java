package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    public javafx.scene.control.Button generateButton;
    @FXML
    public Button backButton;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Spinner spinner;
    @FXML
    public CheckBox checkbox;

    public void generateMaze(ActionEvent actionEvent) throws IOException {
        System.out.println(spinner.getValue());
        System.out.println(colorPicker.getValue());
        System.out.println(checkbox.isSelected());

        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        Stage window = (Stage) generateButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
