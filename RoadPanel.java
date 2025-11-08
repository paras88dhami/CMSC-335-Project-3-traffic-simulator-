import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RoadPanel extends JPanel {
    public static final int ROAD_Y = 150;
    public static final int CAR_WIDTH = 60;
    public static final int CAR_HEIGHT = 30;
    public static final int[] LIGHT_POSITIONS = {200, 400, 600, 800};

    private ArrayList<CarObject> cars = new ArrayList<>();
    private TrafficLightColor[] lightColors = new TrafficLightColor[4];

    public RoadPanel() {
        setBackground(Color.LIGHT_GRAY);
        for (int i = 0; i < lightColors.length; i++) {
            lightColors[i] = TrafficLightColor.RED;
        }
    }

    public void addCar(CarObject car) {
        cars.add(car);
        repaint();
    }

    public void updateCarPosition(int carId, int newX, int speed) {
        for (CarObject car : cars) {
            if (car.id == carId) {
                car.x = newX;
                car.speed = speed;
                break;
            }
        }
        repaint();
    }

    public void updateLightColor(int index, TrafficLightColor color) {
        if (index >= 0 && index < lightColors.length) {
            lightColors[index] = color;
            repaint();
        }
    }

    public ArrayList<CarObject> getCars() {
        return cars;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw road
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, ROAD_Y, getWidth(), 50);

        // Draw traffic lights
        for (int i = 0; i < LIGHT_POSITIONS.length; i++) {
            int x = LIGHT_POSITIONS[i];
            g.setColor(Color.BLACK);
            g.fillRect(x, ROAD_Y - 60, 20, 60);

            switch (lightColors[i]) {
                case RED -> g.setColor(Color.RED);
                case GREEN -> g.setColor(Color.GREEN);
                case YELLOW -> g.setColor(Color.YELLOW);
            }
            g.fillOval(x + 2, ROAD_Y - 50, 16, 16);
        }

        // Draw cars with individual colors
        for (CarObject car : cars) {
            g.setColor(car.color);
            g.fillRect(car.x, ROAD_Y + 10, CAR_WIDTH, CAR_HEIGHT);

            g.setColor(Color.WHITE);
            g.drawString("Car " + car.id, car.x + 5, ROAD_Y + 25);
            g.drawString(car.speed + " m/s", car.x + 5, ROAD_Y + 40);
        }
    }
}
