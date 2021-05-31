module MazeGenerator {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires java.json;

    opens sample;
    opens sample.Controller;
    opens sample.Model.Structures;
    opens sample.Model.Algorithms;
    opens sample.Model.Models;
    opens sample.View;
    opens sample.Model.Structures._2D;
}