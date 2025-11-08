import java.awt.Color;

public class CarThread extends Thread {
    private int id;
    private int position = 0;
    private final int speed = 20;
    public boolean running = true;
    public boolean paused = false;

    private static final Color[] COLORS = {
        Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA,
        Color.ORANGE, Color.CYAN, Color.PINK, Color.YELLOW
    };
    private static int colorIndex = 0;

    public CarThread(int id) {
        this.id = id;
        Color assignedColor = COLORS[colorIndex % COLORS.length];
        colorIndex++;

        TrafficSimulationGUI.roadPanel.addCar(new CarObject(id, position, speed, assignedColor));
    }

    public void run() {
        while (running && position <= 1000) {
            try {
                if (!paused) {
                    boolean canMove = true;

                    // Check traffic lights
                    for (int i = 0; i < RoadPanel.LIGHT_POSITIONS.length; i++) {
                        int lightX = RoadPanel.LIGHT_POSITIONS[i];
                        TrafficLightColor light = Main.lightColors[i];

                        if (position + RoadPanel.CAR_WIDTH >= lightX - 5 &&
                            position + RoadPanel.CAR_WIDTH <= lightX + 20 &&
                            light == TrafficLightColor.RED) {
                            canMove = false;
                            TrafficSimulationGUI.carArea.append("Car " + id + " waiting at red light " + (i + 1) + "\n");
                            break;
                        }
                    }

                    // Check distance from other cars
                    for (CarObject other : TrafficSimulationGUI.roadPanel.getCars()) {
                        if (other.id != this.id) {
                            if (other.x > position && other.x - position < RoadPanel.CAR_WIDTH + 20) {
                                canMove = false;
                                TrafficSimulationGUI.carArea.append("Car " + id + " waiting behind Car " + other.id + "\n");
                                break;
                            }
                        }
                    }

                    if (canMove) {
                        position += speed;
                        TrafficSimulationGUI.carArea.append("Car " + id + " at " + position + "m\n");
                        TrafficSimulationGUI.roadPanel.updateCarPosition(id, position, speed);
                    }
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void stopCar() { running = false; }
    public void pauseCar() { paused = true; }
    public void resumeCar() { paused = false; }
}
