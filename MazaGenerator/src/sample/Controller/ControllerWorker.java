package sample.Controller;

import sample.View.ViewWorker;

public class ControllerWorker extends Thread {
    private ViewWorker view;

    public ControllerWorker(ViewWorker v) {
        this.view = v;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JavaFXController.updateTable();
        }

        // Asteptam detalii de la JavaFxController.

        // Construim maze ul.
    }
}
