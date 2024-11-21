public class RateLimiter {
    private int maximumRequestsAllowed;
    private int windowSize;
    private int previousRequests;
    private int currentRequests;
    private long windowStartTimeInMillis;

    RateLimiter(int maximumRequestsAllowed, int windowSize){
        this.maximumRequestsAllowed = maximumRequestsAllowed;
        this.windowSize = windowSize;
        this.previousRequests = 0;
        this.currentRequests = 0;
        this.windowStartTimeInMillis = System.currentTimeMillis();
    }

    public static RateLimiter getInstance(int maximumRequestsAllowed, int windowSize){
        return new RateLimiter(maximumRequestsAllowed, windowSize);
    }

    public boolean rateLimit(){

        long currentTimeInMillis = System.currentTimeMillis();
        long windowEndTimeInMillis = windowStartTimeInMillis + (windowSize*1000L);
        long rollingWindowStartTimeInMillis = currentTimeInMillis - (windowSize*1000L);

        if(currentTimeInMillis>=windowEndTimeInMillis){

            long timeFramesSpent = (currentTimeInMillis - windowEndTimeInMillis)/(windowSize*1000L);
            windowStartTimeInMillis = windowEndTimeInMillis + (windowSize*timeFramesSpent*1000L);

            if(timeFramesSpent==0){
                previousRequests = currentRequests;
                currentRequests = 0;
            }
            else{
                previousRequests = 0;
                currentRequests = 0;
            }
        }

        double netRequests = (previousRequests*((double)(windowStartTimeInMillis - rollingWindowStartTimeInMillis)/(double)(windowSize*1000L))) + currentRequests;
        int totalRequests = (int)(Math.ceil(netRequests));

        if(totalRequests < maximumRequestsAllowed){
            currentRequests++;
            return true;
        }
        else{
            return false;
        }
    }
}
