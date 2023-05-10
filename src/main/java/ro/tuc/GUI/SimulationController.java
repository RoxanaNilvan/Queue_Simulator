package ro.tuc.GUI;

import ro.tuc.BusinessLogic.SelectionPolicy;
import ro.tuc.BusinessLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationController implements ActionListener {
    private SimulationFrame frame;
    private SimulationManager simulationManager;

    public SimulationController(SimulationFrame frame){
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == frame.start){
            int timeLimit = Integer.parseInt(frame.textSimulationTime.getText());
            int numberOfClients = Integer.parseInt(frame.textNumberOfClients.getText());
            int numberOfQueues = Integer.parseInt(frame.textNumberOfServers.getText());
            int minimumArrivalTime = Integer.parseInt(frame.textMinArrivalTime.getText());
            int maximumArrivalTime = Integer.parseInt(frame.textMaxArrivalTime.getText());
            int minimumServiceTime = Integer.parseInt(frame.textMinProcessingTime.getText());
            int maximumServiceTime = Integer.parseInt(frame.textMaxProcessingTime.getText());
            SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
            if(frame.shortestQueue.isSelected()){
                selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
            }

            if(frame.shortestTime.isSelected()){
                selectionPolicy = SelectionPolicy.SHORTEST_TIME;
            }

            simulationManager = new SimulationManager(timeLimit, maximumServiceTime, minimumServiceTime, maximumArrivalTime, minimumArrivalTime, numberOfQueues,numberOfClients, frame, selectionPolicy);
            Thread thread = new Thread(simulationManager);
            thread.start();
        }
    }
}
