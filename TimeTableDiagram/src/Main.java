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
        MultiplierUpdaterRunnable runnable = new MultiplierUpdaterRunnable(diagramView, SCREEN_SIZE, SCREEN_SIZE, CIRCLE_RADIUS, NUM_OF_SECTORS, MULTIPLIER);
        new Thread(runnable).start();

        diagramView.setViewUpdaterCallback(runnable);
    }

}
