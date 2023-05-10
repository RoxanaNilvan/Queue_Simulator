package ro.tuc.Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int id;
    private boolean isRunning = true;
    private int totalWaitedTime;

    public Server(int id, int maxTask){
        this.id = id;
        tasks = new LinkedBlockingQueue<Task>(maxTask);
        waitingPeriod = new AtomicInteger(0);
        totalWaitedTime = 0;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public Task getFirstTask() {
        return tasks.peek();
    }

    public int getTotalWaitedTime() {
        return totalWaitedTime;
    }

    public void setTotalWaitedTime(int totalWaitedTime) {
        this.totalWaitedTime = totalWaitedTime;
    }

    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }


    public void run() {
        while (isRunning) {
            try {
                Task currentTask = tasks.peek();
                if (currentTask != null) {
                    int currentTaskServiceTime = currentTask.getServiceTime();
                    Thread.sleep(currentTaskServiceTime * 1000L);

                    currentTask.setServiceTime(0);

                    int auxiliary = waitingPeriod.intValue();
                    auxiliary -= currentTaskServiceTime;
                    waitingPeriod.set(auxiliary);

                    tasks.remove();
                } else {
                    Thread.sleep(100L); // Wait a bit before checking for new tasks again
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        this.isRunning = false;
    }

    public void updateWaitingTime(){
        waitingPeriod.getAndDecrement();
    }

    @Override
    public String toString() {
        if (tasks.isEmpty())
            return "Queue " + id +
                    ": closed\n";
        else
            return "Queue " + id +
                    ": " + tasks + "\n";
    }
}
