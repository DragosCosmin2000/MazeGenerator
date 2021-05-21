package sample.Controller;

import sample.Model.Maze;
import sample.View.ViewWorker;

// Face legatura dintre View si Maze.
public class ControllerWorker extends Thread {

    private ViewWorker view;
    private Maze maze;

    public ControllerWorker(ViewWorker v) {
        this.view = v;
    }

    @Override
    public void run() {

        /*while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JavaFXController.updateTable();
        }*/

        /*
        while(astept notificare de la javafxController) {
            - Primesc date legate de dimensiunea maze-ului.
            this.maze = new Maze(datele de la javafxController);


        }
        */

        // Asteptam detalii de la JavaFxController.

        // Construim maze ul.
    }
}
