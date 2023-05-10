package ro.tuc.GUI;

import ro.tuc.BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {
    public JFrame frame;
    public JPanel panel;
    public JLabel title;
    public JLabel labelSimulationTime;
    public JTextField textSimulationTime;
    public JLabel labelMaxProcessingTime;
    public JTextField textMaxProcessingTime;
    public JLabel labelMinProcessingTime;
    public JTextField textMinProcessingTime;
    public JLabel labelMinArrivalTime;
    public JTextField textMinArrivalTime;
    public JLabel labelMaxArrivalTime;
    public JTextField textMaxArrivalTime;
    public JLabel labelNumberOfServers;
    public JTextField textNumberOfServers;
    public JLabel labelNumberOfClients;
    public JTextField textNumberOfClients;
    public JCheckBox shortestQueue;
    public JCheckBox shortestTime;
    public JButton start;
    public JTextField results;
    SimulationController controller = new SimulationController(this);
    public SimulationFrame() {
        this.frame = new JFrame("Queue Simulator");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(900, 800);

        this.panel = new JPanel();
        this.panel.setLayout(null);

        this.title = new JLabel("Queue Simulator");
        this.title.setBounds(200, 0, 100, 50);
        panel.add(title);

        this.labelSimulationTime = new JLabel("Introduceti timpul de simulare");
        this.labelSimulationTime.setBounds(50, 50, 250, 50);
        panel.add(labelSimulationTime);

        this.textSimulationTime = new JTextField("");
        this.textSimulationTime.setBounds(250, 60, 70, 30);
        panel.add(textSimulationTime);

        this.labelNumberOfClients = new JLabel("Intrduceti numarul de clienti");
        this.labelNumberOfClients.setBounds(50, 110, 250, 50);
        panel.add(labelNumberOfClients);

        this.textNumberOfClients = new JTextField("");
        this.textNumberOfClients.setBounds(250, 120, 70, 30);
        panel.add(textNumberOfClients);

        this.labelNumberOfServers = new JLabel("Intrduceti numarul de cozi");
        this.labelNumberOfServers.setBounds(350, 110, 250, 50);
        panel.add(labelNumberOfServers);

        this.textNumberOfServers = new JTextField("");
        this.textNumberOfServers.setBounds(500, 120, 70, 30);
        panel.add(textNumberOfServers);

        this.labelMinArrivalTime = new JLabel("Introduceti timpul minim de sosire");
        this.labelMinArrivalTime.setBounds(50, 170, 250, 50);
        panel.add(labelMinArrivalTime);

        this.textMinArrivalTime = new JTextField("");
        this.textMinArrivalTime.setBounds(250, 180, 70, 30);
        panel.add(textMinArrivalTime);

        this.labelMaxArrivalTime = new JLabel("Introduceti timpul maxim de sosire");
        this.labelMaxArrivalTime.setBounds(350, 170, 250, 50);
        panel.add(labelMaxArrivalTime);

        this.textMaxArrivalTime = new JTextField("");
        this.textMaxArrivalTime.setBounds(550, 180, 70, 30);
        panel.add(textMaxArrivalTime);

        this.labelMinProcessingTime = new JLabel("Introduceti timpul minim de servire");
        this.labelMinProcessingTime.setBounds(50, 230, 250, 50);
        panel.add(labelMinProcessingTime);

        this.textMinProcessingTime = new JTextField("");
        this.textMinProcessingTime.setBounds(250, 240, 70, 30);
        panel.add(textMinProcessingTime);

        this.labelMaxProcessingTime = new JLabel("Introduceti timpul maxim de servire");
        this.labelMaxProcessingTime.setBounds(350, 230, 250, 50);
        panel.add(labelMaxProcessingTime);

        this.textMaxProcessingTime = new JTextField("");
        this.textMaxProcessingTime.setBounds(550, 240, 70, 30);
        panel.add(textMaxProcessingTime);

        this.shortestQueue = new JCheckBox("Shortest Queue Strategy");
        this.shortestQueue.setBounds(50, 300, 200, 30);
        panel.add(shortestQueue);

        this.shortestTime = new JCheckBox("Shortest Time Strategy");
        this.shortestTime.setBounds(300, 300, 200, 30);
        panel.add(shortestTime);

        this.start = new JButton("Start");
        this.start.setBounds(200, 350, 70, 30);
        start.addActionListener(controller);
        panel.add(start);

        this.results = new JTextField();
        this.results.setBounds(50, 410, 700, 300);
        panel.add(results);

        this.frame.setContentPane(panel);
        this.frame.setVisible(true);
    }

    public void writeResult(String toWrite){
        results.setText(" ");
        results.setText(toWrite);
    }
}
