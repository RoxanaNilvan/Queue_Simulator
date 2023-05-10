package ro.tuc.BusinessLogic;

import ro.tuc.Model.Server;
import ro.tuc.Model.Task;
import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task task);
}
