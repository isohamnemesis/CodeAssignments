import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter {
    private int maximumRequestsAllowed;
    private int windowSize;
    private int counter;
    private long fixedWindowStartTimeInMillis;
    private Queue<Long> timestamps;

    RateLimiter(int maximumRequestsAllowed, int windowSize){
        this.maximumRequestsAllowed = maximumRequestsAllowed;
        this.windowSize = windowSize;
        this.counter = 0;
        this.fixedWindowStartTimeInMillis = System.currentTimeMillis();
        this.timestamps = new LinkedList<>();
    }

    public static RateLimiter getInstance(int maximumRequestsAllowed, int windowSize){
        return new RateLimiter(maximumRequestsAllowed, windowSize);
    }

    public boolean rateLimit(){

        long currentTimeInMillis = System.currentTimeMillis();
        long fixedWindowEndTimeInMillis = fixedWindowStartTimeInMillis + (windowSize*1000L);
        long logWindowStartTimeInMillis = currentTimeInMillis - (windowSize*1000L);

        while(!timestamps.isEmpty() && timestamps.peek()<=logWindowStartTimeInMillis){
            timestamps.poll();
        }

        if(currentTimeInMillis<fixedWindowEndTimeInMillis){
            if(counter< maximumRequestsAllowed && timestamps.size()< maximumRequestsAllowed){
                counter++;
                timestamps.add(currentTimeInMillis);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            long timeFramesSpent = ((currentTimeInMillis - fixedWindowEndTimeInMillis)/(windowSize*1000L));
            fixedWindowStartTimeInMillis = fixedWindowEndTimeInMillis + (timeFramesSpent*windowSize*1000L);
            counter = 0;
            if(timestamps.size()< maximumRequestsAllowed){
                counter++;
                timestamps.add(currentTimeInMillis);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
