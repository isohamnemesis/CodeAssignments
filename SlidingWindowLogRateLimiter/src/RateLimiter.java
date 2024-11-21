import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter {

    private int maximumRequestsAllowed;
    private int windowSize;
    private Queue<Long> timeStamps;

    RateLimiter(int maximumRequestsAllowed, int windowSize){
        this.maximumRequestsAllowed = maximumRequestsAllowed;
        this.windowSize = windowSize;
        timeStamps = new LinkedList<>();
    }

    public boolean rateLimit(){
        long currentTimeInMillis = System.currentTimeMillis();
        long windowStartTimeInMillis = (currentTimeInMillis - (windowSize*1000L));

        while(!timeStamps.isEmpty() && timeStamps.peek()<=windowStartTimeInMillis){
            timeStamps.poll();
        }

        timeStamps.add(currentTimeInMillis);
        return (timeStamps.size()<= maximumRequestsAllowed);
    }

    public static RateLimiter getInstance(int maximumRequestsAllowed, int windowSize){
        return new RateLimiter(maximumRequestsAllowed, windowSize);
    }
}
