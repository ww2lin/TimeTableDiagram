import Shape.Circle;
import Shape.Line;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
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

        // set the color
        Random random = new Random();
        final float hue = random.nextFloat();
        // Saturation between 0.1 and 0.3
        final float saturation = (random.nextInt(2000) + 1000) / 10000f;
        final float luminance = 0.9f;
        final Color color = Color.getHSBColor(hue, saturation, luminance);
        g.setColor(color);

        for (Line line : diagramModel.getLines()) {
            g.drawLine(line.x1(), line.y1(), line.x2(), line.y2());
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
