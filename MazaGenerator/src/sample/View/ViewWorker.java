package sample.View;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class ViewWorker extends Thread {

    @Override
    public void run() {
        View.main(null);
    }

    public static void notify1() {
        System.out.println("Notify...");
    }
}
