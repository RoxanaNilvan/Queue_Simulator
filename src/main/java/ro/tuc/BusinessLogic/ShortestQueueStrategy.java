package ro.tuc.BusinessLogic;

import ro.tuc.Model.Server;
import ro.tuc.Model.Task;
import java.util.List;


public class ShortestQueueStrategy implements Strategy {
    public void addTask(List<Server> servers, Task task) {
        if(!servers.isEmpty()){
            Server shortestQueueServer = servers.get(0);
            int shortestQueueServerSize = shortestQueueServer.getTasks().size();

            for(Server currentServer : servers){
                if(currentServer.getTasks().size() < shortestQueueServerSize){
                    shortestQueueServer = currentServer;
                    shortestQueueServerSize = currentServer.getTasks().size();
                }
            }

            if(shortestQueueServer != null)
            shortestQueueServer.addTask(task);
        }
    }
}
