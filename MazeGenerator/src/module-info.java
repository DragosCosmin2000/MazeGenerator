module MazeGenerator {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens sample;
    opens sample.Controller;
    opens sample.Model.Structures;
    opens sample.Model.Algorithms;
    opens sample.Model.Models;
    opens sample.View;
}