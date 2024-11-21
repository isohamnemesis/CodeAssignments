import java.util.*;

public class Job {
    private String name;
    private int duration;
    private int priority;
    private int deadline;
    private UserType usertype;

    Job(String name, int duration, int priority, int deadline, UserType usertype){
        this.name = name;
        this.duration = duration;
        this.priority = priority;
        this.deadline = deadline;
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getDeadline() {
        return deadline;
    }

    public UserType getUsertype() {
        return usertype;
    }
}
