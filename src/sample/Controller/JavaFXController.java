package sample.Controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONException;
import sample.Model.Models.Main3DModel;
import sample.Model.Models.MainModel;
import sample.Model.Structures._2D.Cell;
import sample.Model.Structures._2D.Maze;
import sample.Model.Structures.Settings;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
public class JavaFXController implements Initializable {
    private ResourceBundle bundle;

    @FXML
    public Label appTitle;
    @FXML
    public Label dimensionLabel;
    @FXML
    public Label colorLabel;
    @FXML
    public Label languageLabel;
    @FXML
    public Label algorithmLabel;

    @FXML
    public Button generateButton;
    @FXML
    public Button generate3DButton;
    @FXML
    public Button saveButton;
    @FXML
    public Button saveAsJsonButton;
    @FXML
    public Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button startStopButton;
    @FXML
    private Button importMaze;

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
    private ColorPicker colorPicker;
    @FXML
    private Spinner spinner;
    @FXML
    private Spinner spinner1;

    @FXML
    private Button romanianLanguageButton;
    @FXML
    private Button englishLanguageButton;

    // Main Scene Pane.
    @FXML
    private Pane mazePane;

    // Main3D Scene main Pane.
    @FXML
    private AnchorPane main3DPane;

    // Main3D Scene Panes.
    @FXML
    private Pane level1Pane;
    @FXML
    private Pane level2Pane;
    @FXML
    private Pane level3Pane;
    @FXML
    private Pane level4Pane;

    private Boolean isImported = false;
    private List<Cell>cellList;

    // To create a new thread in order to run the animation.
    private ExecutorService executorService;
    private MainModel mainModel;
    private Main3DModel main3DModel;

    private static Settings settings;

    public JavaFXController() {
        mainModel = new MainModel();
        main3DModel = new Main3DModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resource) {
        bundle = resource;

        setSpinnerForAlgorithmName(spinner1);
        // Buttons hover animation.
        // Menu Buttons.
        setAnimationButtons(generateButton);
        setAnimationButtons(generate3DButton);
        setAnimationButtons(exitButton);
        setAnimationButtons(importMaze);

        // Main Scene Buttons
        setAnimationButtons(saveButton);
        setAnimationButtons(saveAsJsonButton);
        setAnimationButtons(backButton);

        // Set mouse event for Main3D. When right click, maze's cells will have numbers.
        if(main3DPane != null) {
            main3DPane.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    main3DModel.setShowNumbersFlag(
                            !main3DModel.getShowNumbersFlag()
                    );
                }
            });
        }
    }

    public void setSpinnerForAlgorithmName(Spinner spinner){
        ObservableList<String> months = FXCollections.observableArrayList(//
                "Recursive Backtracker Algorithm",
                    "Hunt and Kill Algorithm",
                    "Aldous-Broder algorithm");
        SpinnerValueFactory<String> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<String>(months);

        // Default value
        valueFactory.setValue("Recursive Backtracker Algorithm");

        if(spinner!=null)
            spinner.setValueFactory(valueFactory);
    }
    public void setAnimationButtons(Button btn) {
        // For some reason it becomes null when changing a scene.
        if(btn == null) {
            return;
        }
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #7544ae;" +
                "-fx-text-fill: white;" +
                "-fx-border-color: #7544ae;" +
                "-fx-border-width: 3;" +
                "-fx-border-radius: 5;"
        ));

        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: white;" +
                "-fx-text-fill: #7544ae;" +
                "-fx-border-color: #7544ae;" +
                "-fx-border-width: 3;" +
                "-fx-border-radius: 5;"
        ));
    }

    public void generateMaze(ActionEvent actionEvent) throws IOException {
        // Get the settings.
        settings = new Settings(
                (int) spinner.getValue(),
                colorPicker.getValue(),
                isImported,
                (String)spinner1.getValue(),
                cellList
        );

        // Change to main scene.
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/sample/View/main.fxml"), bundle);
        Stage window = (Stage) generateButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void generate3DMaze() throws IOException {
        // Get the settings.
        settings = new Settings(
                (int) spinner.getValue(),
                colorPicker.getValue(),
                isImported,
                (String) spinner1.getValue(),
                cellList
        );

        // Change to main scene.
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/sample/View/main3D.fxml"), bundle);
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
        Parent root = FXMLLoader.load(getClass().getResource("/sample/View/menu.fxml"), bundle);
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

    public void startStopAnimation(ActionEvent actionEvent) {
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

    public void startStopAnimation3D(ActionEvent actionEvent) {
        if(main3DModel.getRunningAnimationFlag() == 0) {
            System.out.println("FIRST CLICK");

            // Send data to model.
            main3DModel.configureModel(settings, level1Pane, level2Pane, level3Pane, level4Pane);

            // Create a new thread.
            executorService = Executors.newFixedThreadPool(1);

            // Start it.
            executorService.execute(main3DModel);
            // executorService.shutdown(); // Vad ca ajunge aici imediat dupa execute si nu face absolut nimic sau n am vazut eu ca face ceva.

            // Set the flag to 2 in order to pause the animation at the next press.
            main3DModel.setRunningAnimationFlag(2);
        }
        else if(main3DModel.getRunningAnimationFlag() == 1){
            System.out.println("RUNNING"); // de scos
            // Continue the animation.
            main3DModel.changePause(false);

            // Set the flag to 2. If the button will be pressed again the animation will pause.
            main3DModel.setRunningAnimationFlag(2);
        }
        else if(main3DModel.getRunningAnimationFlag() == 2){
            System.out.println("PAUSE"); // de scos
            // Pause the animation.
            main3DModel.changePause(true);

            // Set the flag to 1 in order to start the animation at the next press.
            main3DModel.setRunningAnimationFlag(1);
        }
    }

    // Animation speed settings.
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

    // Import/Export.
    public void saveMaze() {
        // TODO.
        //mainModel.saveMaze();
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", ".png"),
                new FileChooser.ExtensionFilter("BMP Files", ".bmp"),
                new FileChooser.ExtensionFilter("GIF Files", "*.gif"));

        fc.setInitialFileName("mazeGenerator.png");

        File file = fc.showSaveDialog(null);

        try {
            Image snapshot = mazePane.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveMazeAsJson() throws JSONException, IOException {
        Maze maze=mainModel.takeMaze();

        JSONObject obj = new JSONObject();
        obj.put("nrColumns",maze.getNrOfCols());
        obj.put("nrRows",maze.getNrOfRows());
        int i=1;
        for(Cell cell : maze.getGrid()){
            JSONArray listWall = new JSONArray();
            for(Boolean wall:cell.getWalls())
                listWall.put(wall);
            JSONObject cellAtributes = new JSONObject();
            cellAtributes.put("coloana",cell.getI());
            cellAtributes.put("linie",cell.getJ());
            cellAtributes.put("walls",listWall);
            obj.put("Cell"+i,cellAtributes);
            i++;
        }
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Files", ".json"));
        fc.setInitialFileName("mazeGenerator.json");
        File file = fc.showSaveDialog(null);

        if(file!=null) {
            PrintStream printStream = new PrintStream(file);
            printStream.println(obj);
            printStream.flush();
        }
    }

    public void ImportMazeAsJson() throws IOException, JSONException {
        List<Cell> cells=new ArrayList<>();
        Scanner scanIn;
        FileChooser fc = new FileChooser();
        JSONObject obj=null;
        File file = fc.showSaveDialog(null);
        System.out.println(file);
        scanIn=new Scanner(file);
        if( file.isFile()) {
            isImported=true;
            while (scanIn.hasNext()) {
                obj = new JSONObject(scanIn.next());
            }
            int nrColoane=Integer.parseInt(obj.get("nrColumns").toString());
            int nrRows=Integer.parseInt(obj.get("nrRows").toString());
            for(int i=1;i<=nrColoane*nrRows;i++) {
                JSONObject celula = obj.getJSONObject("Cell" + i);
                Cell cell = new Cell(celula.getInt("coloana"), celula.getInt("linie"));
                JSONArray walls = celula.getJSONArray("walls");
                for (int j = 0; j < walls.length(); j++) {
                    cell.setVisited(true);
                    if (!walls.getBoolean(j))
                        cell.hideWall(j);
                }
                cells.add(cell);
            }
            cellList=cells;
        }
    }

    // Language settings.
    public void setAllTexts(ResourceBundle newBundle) {
        appTitle.setText(newBundle.getString("title"));
        dimensionLabel.setText(newBundle.getString("dimensionLabel"));
        colorLabel.setText(newBundle.getString("colorLabel"));
        languageLabel.setText(newBundle.getString("languageLabel"));
        algorithmLabel.setText(newBundle.getString("algorithmLabel"));
        importMaze.setText(newBundle.getString("importButtonText"));
        exitButton.setText(newBundle.getString("exitButtonText"));
        generateButton.setText(newBundle.getString("generateButtonText"));
        generate3DButton.setText(newBundle.getString("generate3DButtonText"));
    }

    public void setLanguageToRomanian() {
        Locale currentLocale = new Locale("ro_RO");
        bundle = ResourceBundle.getBundle("resources.Messages", currentLocale);
        setAllTexts(bundle);
    }

    public void setLanguageToChinese() {
        Locale currentLocale = new Locale("zh_CN");
        bundle = ResourceBundle.getBundle("resources.Messages", currentLocale);
        setAllTexts(bundle);
    }

    public void setLanguageToEnglish() {
        Locale currentLocale = new Locale("en_UK");
        bundle = ResourceBundle.getBundle("resources.Messages", currentLocale);
        setAllTexts(bundle);
    }
}
