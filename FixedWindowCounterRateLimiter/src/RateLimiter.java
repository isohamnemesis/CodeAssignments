public class RateLimiter {
    private int maximumRequestsAllowed;
    private int windowSize;
    private int counter;
    private long windowStartTimeInMillis;

    RateLimiter(int maximumRequestsAllowed, int windowSize){
        this.maximumRequestsAllowed = maximumRequestsAllowed;
        this.windowSize = windowSize;
        this.counter = 0;
        this.windowStartTimeInMillis = System.currentTimeMillis();
    }

    public boolean rateLimit(){
        long currentTimeInMillis = System.currentTimeMillis();
        long windowEndTimeInMillis = windowStartTimeInMillis + (windowSize*1000L);
        
        if(currentTimeInMillis < windowEndTimeInMillis){
            if(counter< maximumRequestsAllowed){
                counter++;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            long timeFrameSpent = ((long)(currentTimeInMillis - windowEndTimeInMillis)/(long)(windowSize));
            windowStartTimeInMillis = windowEndTimeInMillis + (timeFrameSpent*windowSize);
            counter = 0;
            counter++;
            return true;
        }
    }

    public static RateLimiter getInstance(int maximumRequestsAllowed, int windowSize){
        return new RateLimiter(maximumRequestsAllowed, windowSize);
    }

}
