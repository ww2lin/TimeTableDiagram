import Shape.Circle;
import Shape.Line;
import Interfaces.ViewUpdaterCallback;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by AlexLin on 12/18/16.
 */
public class DiagramView extends JPanel{

    private DiagramModel diagramModel;
    private CountDownLatch latch = new CountDownLatch(1);
    private ViewUpdaterCallback viewUpdaterCallback;

    public DiagramView(DiagramModel diagramModel) {
        super();
        setupUI();
        update(diagramModel);
    }

    public void update(DiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        this.setSize(diagramModel.getScreenWidth(), diagramModel.getScreenHeight());
        setSize(diagramModel.getScreenWidth(), diagramModel.getScreenHeight());

        repaint();
    }

    private void setupUI(){
        setLayout(new FlowLayout());
        JButton pauseButton = new JButton("Pause");
        JLabel speedLabel = new JLabel("Speed: ");
        JButton increaseSpeed = new JButton("+");
        JButton decreaseSpeed = new JButton("-");

        add(speedLabel);
        add(increaseSpeed);
        add(decreaseSpeed);
        add(pauseButton);

        pauseButton.addActionListener(e -> viewUpdaterCallback.onPause());

        increaseSpeed.addActionListener(e -> viewUpdaterCallback.increaseSpeed());

        decreaseSpeed.addActionListener(e -> viewUpdaterCallback.decreaseSpeed());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

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


        // Draw the outer circle
        g.setColor(Color.BLACK);
        Circle circle = diagramModel.getCircle();
        g.drawOval(circle.getOffsetX(), circle.getOffsetY(), circle.getDiameter(), circle.getDiameter());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setViewUpdaterCallback(ViewUpdaterCallback viewUpdaterCallback) {
        this.viewUpdaterCallback = viewUpdaterCallback;
    }
}
