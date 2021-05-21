package sample.Controller;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sample.Model.Maze;

public class JavaFXController extends Task<Long> {

    private Pane pane;
    private int slowmo=1;
    private int numarPatratele;
    private Color culoare;
    private Boolean pause=Boolean.FALSE;
    private Maze maze;
    public JavaFXController(Pane pane, int numarPatratele, Color culoare, Maze maze) {
        this.pane = pane;
        this.numarPatratele=numarPatratele;
        this.culoare=culoare;
        this.maze=maze;
    }

    @Override
    protected Long call() throws Exception {

        Integer i=100;
        while(i>0){
            System.out.println(culoare);
            if(!pause) {
                maze.getTable();
                Thread.sleep(slowmo * 1000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        GridPane gridPane = new GridPane();
                        pane.getChildren().clear();
                        gridPane = makePane();
                        pane.getChildren().add(gridPane);
                        System.out.println(slowmo);
                    }
                });
                i--;
            }
            else {
                culoare = Color.RED;
                Thread.sleep(slowmo * 1000);
            }
        }
        return 100l;
    }

    public int getSlowmo() {
        return slowmo;
    }

    public void setSlowmo(int slowmo) {
        this.slowmo = slowmo;
    }
    public GridPane makePane(){
        GridPane pane1=new GridPane();
        System.out.println("salut");
        double startX = pane.getWidth();
        double startY = pane.getHeight();
        double lungimeCelula = 15;
        startX = startX / 2 - (lungimeCelula * numarPatratele) / 2;
        startY = startY / 2 - (lungimeCelula * numarPatratele) / 2;
        for (int i = 0; i < numarPatratele; i++)
            for (int j = 0; j < numarPatratele; j++) {
                pane1.getChildren().add(makeRectangle(startX + (i * lungimeCelula), startX + (i * lungimeCelula), startY + (j * lungimeCelula),
                        startY + (j * lungimeCelula) + lungimeCelula, lungimeCelula, culoare));
            }
        return pane1;
    }
    public Node makeRectangle(double startX, double stopX, double startY, double stopY, double lungime, Color color) {
        Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();
        Line line4 = new Line();

        line1.setStartX(startX);
        line1.setStartY(startY);
        line1.setEndX(startX);
        line1.setEndY(startY + lungime);
        line1.setStroke(Color.BLUE);

        line2.setStartX(startX);
        line2.setStartY(startY);
        line2.setEndX(startX + lungime);
        line2.setEndY(startY);
        line2.setStroke(Color.BLUE);

        line3.setStartX(startX + lungime);
        line3.setStartY(startY);
        line3.setEndX(startX + lungime);
        line3.setEndY(startY + lungime);
        line3.setStroke(Color.BLUE);

        line4.setStartX(startX);
        line4.setStartY(startY + lungime);
        line4.setEndX(startX + lungime);
        line4.setEndY(startY + lungime);
        line4.setStroke(Color.BLUE);

        Rectangle rect = new Rectangle(startX, startY, 15, 15);
        rect.setFill(color);
        Pane pane1 = new Pane();
        pane1.getChildren().addAll(line1,line2,line3,line4);
        pane1.getChildren().addAll(rect);
        return pane1;
    }

    public void changePause(Boolean pause){
        this.pause=pause;
    }
}
