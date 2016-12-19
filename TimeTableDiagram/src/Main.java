import javax.swing.JApplet;

/**
 * Created by AlexLin on 12/18/16.
 */
public class Main extends JApplet{
    private static final int SCREEN_SIZE = 500;
    private static final int CIRCLE_RADIUS = SCREEN_SIZE / 2 - 50;
    private static final int NUM_OF_SECTORS = 360;
    private static final int MULTIPLIER = 2;

    private DiagramView diagramView;

    @Override
    public void init() {
        super.init();
        this.setSize(SCREEN_SIZE, SCREEN_SIZE);
        DiagramModel diagramModel = new DiagramModel(SCREEN_SIZE, SCREEN_SIZE, CIRCLE_RADIUS, NUM_OF_SECTORS, MULTIPLIER);

        diagramView = new DiagramView(diagramModel);
        add(diagramView);

        // Starts a thread that bump the # of multiplier after some units of time.
        new Thread(new TimerRunnable(diagramView)).start();
    }

    private class TimerRunnable implements Runnable {
        private static final int TIME_TO_PAUSE_IN_MS = 400;
        private int multiplier = MULTIPLIER + 1;

        DiagramView diagramView;

        public TimerRunnable(DiagramView diagramView) {
            this.diagramView = diagramView;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    diagramView.getLatch().await();
                    Thread.sleep(TIME_TO_PAUSE_IN_MS);
                    diagramView.update(new DiagramModel(SCREEN_SIZE, SCREEN_SIZE, CIRCLE_RADIUS, NUM_OF_SECTORS, multiplier++));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }

}
