import java.util.*;

public class JobComparator implements Comparator<Job>{
    @Override
    public int compare(Job j1, Job j2){
        if(j1.getUsertype()==j2.getUsertype())
            return (j2.getDuration() - j1.getDuration());
        else{
            return j1.getUsertype().compareTo(j2.getUsertype());
        }
    }
}
