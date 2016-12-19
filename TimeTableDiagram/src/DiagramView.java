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
import javax.swing.JTextField;

/**
 * Created by AlexLin on 12/18/16.
 */
public class DiagramView extends JPanel{

    private DiagramModel diagramModel;
    private CountDownLatch latch = new CountDownLatch(1);
    private ViewUpdaterCallback viewUpdaterCallback;


    JButton pauseButton = new JButton("Paused");

    JLabel speedLabel = new JLabel("Speed: ");
    JButton increaseSpeed = new JButton("+");
    JButton decreaseSpeed = new JButton("-");

    JLabel multiplierLabel = new JLabel();

    JLabel seekLabel = new JLabel("Seek: ");
    JTextField multiplierTextField = new JTextField(3);

    JButton seekButton = new JButton("Enter");

    public DiagramView(DiagramModel diagramModel, ViewUpdaterCallback viewUpdaterCallback) {
        super();
        this.viewUpdaterCallback = viewUpdaterCallback;
        setupUI();
        update(diagramModel);

    }

    public void update(DiagramModel diagramModel) {
        this.diagramModel = diagramModel;
        this.setSize(diagramModel.getScreenWidth(), diagramModel.getScreenHeight());
        setSize(diagramModel.getScreenWidth(), diagramModel.getScreenHeight());
        multiplierLabel.setText("Multiplier: "+ diagramModel.getMultiplier());
        repaint();
    }

    private void setupUI(){
        setLayout(new FlowLayout());

        add(speedLabel);
        add(increaseSpeed);
        add(decreaseSpeed);
        add(pauseButton);
        add(multiplierLabel);
        add(seekLabel);
        add(multiplierTextField);
        add(seekButton);

        pauseButton.addActionListener(e -> {
            viewUpdaterCallback.onPause();
            pauseButton.setText(viewUpdaterCallback.isPause() ? "Resume" : "Pause");
        });

        increaseSpeed.addActionListener(e -> viewUpdaterCallback.increaseSpeed());

        decreaseSpeed.addActionListener(e -> viewUpdaterCallback.decreaseSpeed());

        seekButton.addActionListener(e -> {
            viewUpdaterCallback.seek(multiplierTextField.getText());
            pauseButton.setText(viewUpdaterCallback.isPause() ? "Resume" : "Pause");
        });
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

}
