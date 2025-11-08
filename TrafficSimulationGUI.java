import javax.swing.*;
import java.awt.*;

public class TrafficSimulationGUI extends JFrame {
    public static JLabel timeLabel;
    public static JTextArea carArea;
    public static RoadPanel roadPanel = new RoadPanel();

    public TrafficSimulationGUI() {
        setTitle("Traffic Simulation");
        setSize(1000, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Time display at top
        timeLabel = new JLabel("Time: 0", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(timeLabel, BorderLayout.NORTH);

        // Road Panel in center
        roadPanel.setPreferredSize(new Dimension(800, 300));
        add(roadPanel, BorderLayout.CENTER);

        // Car log panel on the right
        carArea = new JTextArea();
        carArea.setEditable(false);
        carArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(carArea);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        add(scrollPane, BorderLayout.EAST);

        // Control Buttons
        JPanel buttonPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton pauseBtn = new JButton("Pause");
        JButton resumeBtn = new JButton("Resume");
        JButton stopBtn = new JButton("Stop");
        JButton addCarBtn = new JButton("+ Add Car");

        startBtn.addActionListener(e -> Main.startSimulation());
        pauseBtn.addActionListener(e -> Main.pauseSimulation());
        resumeBtn.addActionListener(e -> Main.resumeSimulation());
        stopBtn.addActionListener(e -> Main.stopSimulation());
        addCarBtn.addActionListener(e -> Main.addCar());

        buttonPanel.add(startBtn);
        buttonPanel.add(pauseBtn);
        buttonPanel.add(resumeBtn);
        buttonPanel.add(stopBtn);
        buttonPanel.add(addCarBtn);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}
