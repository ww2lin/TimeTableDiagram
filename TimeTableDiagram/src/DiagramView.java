import Shape.Circle;
import Shape.Line;
import java.awt.Graphics;
import java.util.concurrent.CountDownLatch;
import javax.swing.JPanel;

/**
 * Created by AlexLin on 12/18/16.
 */
public class DiagramView extends JPanel{

    private DiagramModel diagramModel;
    private CountDownLatch latch = new CountDownLatch(1);

    public DiagramView(DiagramModel diagramModel) {
        super();
        update(diagramModel);
    }

    public void update(DiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        this.setSize(diagramModel.getScreenWidth(), diagramModel.getScreenHeight());
        setSize(diagramModel.getScreenWidth(), diagramModel.getScreenHeight());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the outer circle
        Circle circle = diagramModel.getCircle();
        g.drawOval(circle.getOffsetX(), circle.getOffsetY(), circle.getDiameter(), circle.getDiameter());

        for (Line line : diagramModel.getLines()) {
            g.drawLine(line.x1(), line.y1(), line.x2(), line.y2());
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
