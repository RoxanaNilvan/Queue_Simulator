package ro.tuc.BusinessLogic;

import ro.tuc.Model.Server;
import ro.tuc.Model.Task;

import java.util.List;
import java.util.ArrayList;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;
    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        this.strategy = new ShortestTimeStrategy();
        servers = new ArrayList<Server>(maxNoServers);
        for (int i = 0; i < maxNoServers; i++) {
            servers.add(new Server( i+1, maxTasksPerServer));
        }
        for (Server server : servers) {
            new Thread(server).start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ShortestQueueStrategy();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ShortestTimeStrategy();
        }
    }

    public void dispatchTask(Task t){
        strategy.addTask(servers, t);
    }

    public void stopServers(){
        for (Server server : servers){
            server.stop();
        }
    }

    public void updateServers(){
        for (Server server : servers){
            server.updateWaitingTime();
        }
    }

    public List<Server> getServers(){
        return servers;
    }

    public boolean workingServers() {
        boolean running = false;
        for(Server server : servers){
            if(server.getTasks().size() != 0){
                running = true;
                break;
            }
        }
        return running;
    }

    public int waitedTime(){
        int waited= 0;
        for(Server server : servers){
            waited = waited + server.getTotalWaitedTime();
        }
        return waited;
    }
}
