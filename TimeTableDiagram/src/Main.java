import javax.swing.JApplet;

/**
 * Created by AlexLin on 12/18/16.
 */
public class Main extends JApplet{


    private DiagramView diagramView;

    @Override
    public void init() {
        super.init();
        this.setSize(Constants.SCREEN_SIZE, Constants.SCREEN_SIZE);
        DiagramModel diagramModel = new DiagramModel(Constants.SCREEN_SIZE, Constants.SCREEN_SIZE, Constants.CIRCLE_RADIUS, Constants.NUM_OF_SECTORS, Constants.MULTIPLIER);
        MultiplierUpdaterRunnable runnable = new MultiplierUpdaterRunnable(Constants.SCREEN_SIZE, Constants.SCREEN_SIZE, Constants.CIRCLE_RADIUS, Constants.NUM_OF_SECTORS, Constants.MULTIPLIER);

        diagramView = new DiagramView(diagramModel, runnable);
        runnable.setDiagramView(diagramView);

        add(diagramView);

        // Starts a thread that bump the # of multiplier after some units of time.
        new Thread(runnable).start();
    }

}
