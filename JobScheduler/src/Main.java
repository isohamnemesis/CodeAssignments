import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Job j1 = new Job("CPU1", 10, 1, 30, UserType.ROOT);
        Job j2 = new Job("GPU1", 5, 2, 15, UserType.ROOT);
        Job j3 = new Job("Cache1", 1, 3, 23, UserType.ROOT);
        Job j4 = new Job("CPU2", 17, 1, 53, UserType.ADMIN);
        Job j5 = new Job("Cache2", 12, 3, 24, UserType.ADMIN);
        Job j6 = new Job("CPU3", 11, 1, 2, UserType.USER);
        Job j7 = new Job("GPU2", 19, 2, 3, UserType.ADMIN);
        Job j8 = new Job("GPU3", 16, 2, 21, UserType.USER);
        Job j9 = new Job("CPU4", 9, 1, 33, UserType.ROOT);
        Job j10 = new Job("Cache3", 41, 3, 29, UserType.USER);


        List<Job> jobs = new ArrayList<>();
        jobs.add(j1);
        jobs.add(j2);
        jobs.add(j3);
        jobs.add(j4);
        jobs.add(j5);
        jobs.add(j6);
        jobs.add(j7);
        jobs.add(j8);
        jobs.add(j9);
        jobs.add(j10);

        Scheduler scheduler = new Scheduler(5, jobs);
        List<List<Job>> result = scheduler.scheduleJobs();
        for(List<Job> r:result) {
            for(Job j:r){
                System.out.print(j.getName());
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}