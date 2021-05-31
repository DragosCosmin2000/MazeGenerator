package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controller.JavaFXController;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale currentLocale = new Locale("en_UK");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.Messages", currentLocale);

        FXMLLoader fxmlLoader = new FXMLLoader();

        Parent root = fxmlLoader.load(getClass().getResource("View/menu.fxml"), bundle);
        primaryStage.setTitle("Maze Generator!");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /*
    private GridPane createInterface() {
        GridPane gp = new GridPane();
        TextField tf1 = new TextField();
        Button btnStart = new Button("Start");
        Button btnNext = new Button("Next Fibonacci Number");
        Label lbl1 = new Label();
        Label lbl2 = new Label();
        Label lbl3 = new Label();
        Label lbl4 = new Label();
        btnStart.setOnAction(new
                 EventHandler<ActionEvent>() {
                     @Override
                     public void handle(ActionEvent event) {
                             try {
                                 int ival = Integer.parseInt(tf1.getText());
                                 FiboCalcTask task = new FiboCalcTask(ival,gp, gridPane1);
                                 lbl1.textProperty().bind(task.messageProperty());
                                 task.setOnRunning((succeesesEvent) -> {
                                     btnStart.setDisable(true);
                                     lbl2.setText("");
                                 });
                                 task.setOnSucceeded((succeededEvent) -> {
                                     lbl2.setText(task.getValue().toString());
                                     btnStart.setDisable(false);
                                 });
                                 ExecutorService executorService
                                         = Executors.newFixedThreadPool(1);
                                 executorService.execute(task);
                                 executorService.shutdown();
                             } catch (NumberFormatException e) {
                                 tf1.setText("Enter a number");
                                 tf1.selectAll();
                                 tf1.requestFocus();
                             }
                         }
                 });
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lbl3.setText(num + "the Fibonacci number is = ");
                lbl4.setText(String.valueOf(n2));
                long temp = n1 + n2;
                n1 = n2;
                n2 = temp;
                ++num;
            }
        });
        lbl1.setTextFill(Color.RED);
        gp.add(new Label("Enter a number: "), 0, 0);
        gp.add(tf1, 1, 0);
        gp.add(lbl1, 2, 0);
        gp.add(btnStart, 0, 1);
        gp.add(lbl2, 1, 1);
        gp.add(lbl3, 0, 2);
        gp.add(lbl4, 1, 2);
        gp.add(btnNext, 0, 3);
        gp.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        return gp;
    }*/
}