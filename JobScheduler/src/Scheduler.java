import java.util.*;

public class Scheduler {

    private List<Job> jobs;
    private int threads;

    Scheduler(int threads){
        this.threads = threads;
        this.jobs = new ArrayList<>();
    }

    Scheduler(int threads, List<Job> jobs){
        this.jobs = jobs;
        this.threads = threads;
    }

    void addJobs(Job job){
        jobs.add(job);
    }

    List<List<Job>> scheduleJobs(){

        Map<Integer, List<Job>> store = new HashMap<>();
        for(int i=1; i<=threads; i++){
            store.put(i, new ArrayList<>());
        }

        for(Job j:jobs){
            List<Job> oldjobs = store.get(j.getPriority());
            oldjobs.add(j);
            store.replace(j.getPriority(), oldjobs);
        }

        List<List<Job>> schedule = new ArrayList<>();

        for(int i=0; i<threads; i++)
            schedule.add(new ArrayList<>());

        for(Map.Entry<Integer, List<Job>> e: store.entrySet()){
            int key = e.getKey();
            List<Job> pjobs = e.getValue();
            pjobs.sort(new JobComparator());
            store.replace(key, pjobs);
        }

        int pos = 0;
        for(Map.Entry<Integer, List<Job>> e: store.entrySet()) {

            List<Job> pjobs = e.getValue();
            for (Job j : pjobs) {
                schedule.get(pos).add(j);
                pos = (pos + 1) % threads;
            }
        }

        return schedule;
    }
}
