import Interfaces.ViewUpdaterCallback;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by AlexLin on 12/18/16.
 */
public class MultiplierUpdaterRunnable implements Runnable, ViewUpdaterCallback {
    private static final int TIME_INCREMENT_IN_MS = 50;
    private static final int DEFAULT_PAUSE_TIME_IN_MS = 1000;

    private DiagramView diagramView;
    private final int screenWidth;
    private final int screenHeight;
    private final int radius;
    private final int numSectors;
    private int multiplier;
    private int sleepTimeInMs = 400;
    private AtomicBoolean pause = new AtomicBoolean(false);

    public MultiplierUpdaterRunnable(DiagramView diagramView, int screenWidth, int screenHeight, int radius, int numSectors, int multiplier) {
        this.diagramView = diagramView;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.radius = radius;
        this.numSectors = numSectors;
        this.multiplier = multiplier;
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (pause.get()) {
                    Thread.sleep(DEFAULT_PAUSE_TIME_IN_MS);
                }
                diagramView.getLatch().await();
                Thread.sleep(sleepTimeInMs);
                diagramView.update(new DiagramModel(screenWidth, screenHeight, radius, numSectors, ++multiplier));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onPause() {
        pause.set(!pause.get());
    }

    @Override
    public void increaseSpeed() {
        if (sleepTimeInMs > TIME_INCREMENT_IN_MS) {
            sleepTimeInMs -= TIME_INCREMENT_IN_MS;
        }
    }

    @Override
    public void decreaseSpeed() {
        sleepTimeInMs+=TIME_INCREMENT_IN_MS;
    }
}
