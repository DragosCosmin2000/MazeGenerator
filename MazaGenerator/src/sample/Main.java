package sample;

import sample.Controller.ControllerWorker;
import sample.View.View;
import sample.View.ViewWorker;

public class Main {
    public static void main(String[] args) {
        ViewWorker v = new ViewWorker();
        v.start();

        ControllerWorker c = new ControllerWorker(v);
        c.start();
    }
}
