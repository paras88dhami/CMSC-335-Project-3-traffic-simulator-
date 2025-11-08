public class TrafficLightThread extends Thread {
    private int index;
    private int delayOffset;
    private TrafficLightColor color = TrafficLightColor.RED;
    public boolean running = true;
    public boolean paused = false;

    public TrafficLightThread(int index, int delayOffset) {
        this.index = index;
        this.delayOffset = delayOffset;
        // Set initial color in shared array
        Main.lightColors[index] = color;
        TrafficSimulationGUI.roadPanel.updateLightColor(index, color);
    }

    public void run() {
        try {
            Thread.sleep(delayOffset); // stagger light start
        } catch (InterruptedException e) {
            return;
        }

        while (running) {
            try {
                if (!paused) {
                    switch (color) {
                        case RED -> Thread.sleep(5000);
                        case GREEN -> Thread.sleep(4000);
                        case YELLOW -> Thread.sleep(2000);
                    }
                    changeColor();
                } else {
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void changeColor() {
        color = switch (color) {
            case RED -> TrafficLightColor.GREEN;
            case GREEN -> TrafficLightColor.YELLOW;
            case YELLOW -> TrafficLightColor.RED;
        };

        Main.lightColors[index] = color;
        TrafficSimulationGUI.roadPanel.updateLightColor(index, color);
    }

    public TrafficLightColor getColor() {
        return color;
    }

    public void stopLight() {
        running = false;
    }

    public void pauseLight() {
        paused = true;
    }

    public void resumeLight() {
        paused = false;
    }
}
