package ro.tuc.Model;

public class Task implements Comparable<Task>{
    private int taskID;
    private int arrivalTime;
    private int serviceTime;

    public Task(int taskTD, int arrivalTime, int serviceTime) {
        this.taskID = taskTD;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "(" + taskID +
                ", " + arrivalTime +
                ", " + serviceTime +
                ")\n";
    }

    public int compareTo(Task o) {
        return this.getArrivalTime() - o.getArrivalTime();
    }
}
