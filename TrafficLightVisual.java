import javax.swing.*;
import java.awt.*;

public class TrafficLightVisual extends JPanel {
    private TrafficLightColor currentColor = TrafficLightColor.RED;

    public void setColor(TrafficLightColor color) {
        currentColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int circleSize = 30;
        int padding = 10;

        // Draw Red
        g.setColor(currentColor == TrafficLightColor.RED ? Color.RED : Color.GRAY);
        g.fillOval((width - circleSize) / 2, padding, circleSize, circleSize);

        // Draw Yellow
        g.setColor(currentColor == TrafficLightColor.YELLOW ? Color.YELLOW : Color.GRAY);
        g.fillOval((width - circleSize) / 2, padding * 2 + circleSize, circleSize, circleSize);

        // Draw Green
        g.setColor(currentColor == TrafficLightColor.GREEN ? Color.GREEN : Color.GRAY);
        g.fillOval((width - circleSize) / 2, padding * 3 + circleSize * 2, circleSize, circleSize);
    }
}
