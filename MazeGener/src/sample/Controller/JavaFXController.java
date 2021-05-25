package sample.Controller;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import sample.Model.Cell;
import sample.Model.Maze;

import java.util.ArrayList;
import java.util.List;

public class JavaFXController extends Task<Long> {

    private Pane pane;
    private int slowmo=1;
    private int numarPatratele;
    private Color culoare;
    private Boolean pause=Boolean.FALSE;
    private Maze maze;
    private int lungimeCelula=15;
    List<Cell> cells=new ArrayList<>();
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
            if(!pause) {
                this.culoare=Color.GRAY;
                Thread.sleep(slowmo * 1000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        cells=maze.getGrid();
                        GridPane gridPane;
                        pane.getChildren().clear();
                        gridPane = draw();
                        pane.getChildren().add(gridPane);
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
    public void changePause(Boolean pause){
        this.pause=pause;
    }

    public GridPane draw(){
        GridPane gridPane=new GridPane();
        for(Cell cell:cells){
            gridPane.getChildren().add(show(cell));
        }
        return gridPane;
    }
    public Node show(Cell cell){
        //startX = startX / 2 - (lungimeCelula * numarPatratele) / 2;
        //startY = startY / 2 - (lungimeCelula * numarPatratele) / 2;
        double x=cell.getI()*lungimeCelula;
        double y=cell.getJ()*lungimeCelula;
        Line line1=null;
        Line line2=null;
        Line line3=null;
        Line line4=null;
        boolean[] walls=cell.getWalls();
        if(walls[0]) {//top
            line1 = new Line(x, y, x + lungimeCelula, y);
            line1.setStroke(Color.WHITE);
        }
        if(walls[1]) {//left
            line2 = new Line(x,y+lungimeCelula,x,y);
            line2.setStroke(Color.WHITE);
        }
        if(walls[2]) {//bottom
            line3 = new Line(x+lungimeCelula,y+lungimeCelula,x,y+lungimeCelula);
            line3.setStroke(Color.WHITE);
        }
        if(walls[3]) {//right
            line4 = new Line(x+lungimeCelula,y,x+lungimeCelula,y+lungimeCelula);
            line4.setStroke(Color.WHITE);
        }

        Rectangle rectangle=new Rectangle(x,y,lungimeCelula,lungimeCelula);

        if(cell.isVisited())
            rectangle.setFill(culoare);
        Pane pane = new Pane();
        pane.getChildren().addAll(line1,line2,line3,line4,rectangle);
        System.out.println(pane.getChildren());
        return pane;
    }
}
