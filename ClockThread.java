public class ClockThread extends Thread {
    private int seconds = 0;
    public boolean running = true;
    public boolean paused = false;

    public void run() {
        while (running) {
            try {
                if (!paused) {
                    TrafficSimulationGUI.timeLabel.setText("Time: " + seconds++);
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void stopClock() { running = false; }
    public void pauseClock() { paused = true; }
    public void resumeClock() { paused = false; }
}
