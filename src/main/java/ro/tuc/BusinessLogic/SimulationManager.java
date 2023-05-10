package ro.tuc.BusinessLogic;

import ro.tuc.GUI.SimulationFrame;
import ro.tuc.Model.Server;
import ro.tuc.Model.Task;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    SimulationFrame simulationFrame;

    public SimulationManager(int timeLimit, int maxProcessingTime, int minProcessingTime, int maxArrivalTime,
                             int minArrivalTime, int numberOfServers, int numberOfClients, SimulationFrame frame, SelectionPolicy selectionPolicy) {
        this.timeLimit = timeLimit;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        this.selectionPolicy = selectionPolicy;
        if(selectionPolicy == SelectionPolicy.SHORTEST_QUEUE){
            scheduler.changeStrategy(SelectionPolicy.SHORTEST_QUEUE);
        }
        this.simulationFrame = frame;
        generateNRandomTasks();
    }

    private void generateNRandomTasks() {
        generatedTasks = new ArrayList<Task>(numberOfClients);
        Random rand = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            int id = (int)Math.floor(Math.random() * (numberOfClients - 1 + 1) + 1);
            int processingTime = rand.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            Task randomGeneratedTask = new Task(id, arrivalTime, processingTime);
            generatedTasks.add(randomGeneratedTask);
        }
        Collections.sort(generatedTasks);
    }

    public void run() {
        String textToBeWrittenInFile = "";
        String currentText = "";
        int max = Integer.MIN_VALUE;;
        int peakHour = 0;
        int currentTime = 0;
        float averageWaitingTime = 0;

        while (currentTime <= timeLimit) {
            currentText = "Time: " + currentTime + "\n";
            // Verificăm dacă există sarcini generate
            if (!generatedTasks.isEmpty()) {
                // Așteptăm și despachetăm sarcinile care trebuie procesate la acest moment
                Task currentTask = generatedTasks.get(0);
                while (currentTime == currentTask.getArrivalTime()) {
                    scheduler.dispatchTask(currentTask);
                    generatedTasks.remove(0);
                    if (generatedTasks.isEmpty()) {
                        break;
                    }
                    currentTask = generatedTasks.get(0);
                }
                // Adăugăm informații despre clienții care așteaptă
                currentText += "Waiting clients: ";
                for (Task task : generatedTasks) {
                    currentText += task.toString() + "  ";
                    currentText += "\n";
                }
                currentText += "\n";
            }
            // Adăugăm informații despre cozile de procesare și calculăm vârful de activitate
            int currentPeak = 0;
            for (Server queue : scheduler.getServers()) {
                currentText += queue.toString() + "\n";
                currentPeak += queue.getTasks().size();
            }
            if (currentPeak > max) {
                max = currentPeak;
                peakHour = currentTime;
            }
            currentText += "\n";
            // Actualizăm starea serverelor și timpul curent
            scheduler.updateServers();
            currentTime++;
            // Simulăm o pauză între verificări
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Verificăm dacă s-au terminat sarcinile și serverele nu mai procesează nimic
            if (generatedTasks.isEmpty() && !scheduler.workingServers()) {
                averageWaitingTime = (float) scheduler.waitedTime() / numberOfClients;
                break;
            }
            // Adăugăm rezultatele curente în interfața grafică și în textul de salvat în fișier
            simulationFrame.writeResult(currentText);
            System.out.println(currentText);
            textToBeWrittenInFile += "\n" + currentText;
        }
        // Oprim serverele și afișăm informații finale
        scheduler.stopServers();
        currentText = "Average waiting time: " + averageWaitingTime + "\n";
        currentText += "Peak hour: " + peakHour;
        textToBeWrittenInFile += "\n" + currentText;
        System.out.println(currentText);
        simulationFrame.writeResult(currentText);
        // Salvăm rezultatele într-un fișier
        WriteInFile file = new WriteInFile("sim.txt", textToBeWrittenInFile);
        file.write();
    }
}

