import java.util.ArrayList;

public class Main {
    static ClockThread clock;
    static ArrayList<TrafficLightThread> lights = new ArrayList<>();
    static ArrayList<CarThread> cars = new ArrayList<>();
    public static TrafficLightColor[] lightColors = new TrafficLightColor[4]; // shared for car-light check
    private static boolean simulationStarted = false;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new TrafficSimulationGUI());
    }

    public static void startSimulation() {
        if (simulationStarted) return; // prevent double start
        simulationStarted = true;

        // Start clock
        clock = new ClockThread();
        clock.start();

        // Start 4 traffic lights at different positions
        for (int i = 0; i < 4; i++) {
            int delay = i * 2000; // staggered start
            TrafficLightThread light = new TrafficLightThread(i, delay);
            lights.add(light);
            light.start();
        }

        // Add first 3 cars by default
        for (int i = 0; i < 3; i++) {
            addCar();
        }
    }

    public static void addCar() {
        int id = cars.size() + 1;
        CarThread newCar = new CarThread(id);
        cars.add(newCar);
        newCar.start();
    }

    public static void pauseSimulation() {
        if (clock != null) clock.pauseClock();
        for (TrafficLightThread light : lights) light.pauseLight();
        for (CarThread car : cars) car.pauseCar();
    }

    public static void resumeSimulation() {
        if (clock != null) clock.resumeClock();
        for (TrafficLightThread light : lights) light.resumeLight();
        for (CarThread car : cars) car.resumeCar();
    }

    public static void stopSimulation() {
        if (clock != null) clock.stopClock();
        for (TrafficLightThread light : lights) light.stopLight();
        for (CarThread car : cars) car.stopCar();
        simulationStarted = false;
    }
}
