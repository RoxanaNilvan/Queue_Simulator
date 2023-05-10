package ro.tuc.BusinessLogic;

import ro.tuc.Model.Task;
import ro.tuc.Model.Server;
import java.util.List;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShortestTimeStrategy implements Strategy{

    public void addTask(List<Server> servers, Task task) {
       if(!servers.isEmpty()){
           Server shortestTimeServer = servers.get(0);
           int minWait = shortestTimeServer.getWaitingPeriod().intValue();

           for(Server currentServer : servers){
               int currentWait = currentServer.getWaitingPeriod().intValue();
               if(currentWait < minWait){
                   minWait = currentWait;
                   shortestTimeServer = currentServer;
               }
           }
           if(shortestTimeServer!= null)
           shortestTimeServer.addTask(task);

       }
    }
}
