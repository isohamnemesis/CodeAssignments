public class RateLimiter {
    private int queueSize;
    private int windowSize;
    private int processingRate;
    private int queueSlotRemaining;
    private long windowStartTimeInMillis;

    RateLimiter(int queueSize, int windowSize, int processingRate){
        this.queueSize = queueSize;
        this.windowSize = windowSize;
        this.processingRate = processingRate;
        this.queueSlotRemaining = queueSize;
        this.windowStartTimeInMillis = System.currentTimeMillis();
    }

    public static RateLimiter getInstance(int queueSize, int windowSize, int processingRate){
        return new RateLimiter(queueSize, windowSize, processingRate);
    }

    public boolean rateLimit(){
        long currentTimeInMillis = System.currentTimeMillis();
        long windowEndTimeInMillis = windowStartTimeInMillis + (windowSize*1000L);

        if(currentTimeInMillis < windowEndTimeInMillis){
            if(queueSlotRemaining>0){
                queueSlotRemaining--;
                return true;
            }
            else{
                return false;
            }
        }
        else{
            long timeFrameSpent = (currentTimeInMillis - windowEndTimeInMillis)/(windowSize*1000L);
            queueSlotRemaining = Math.min((queueSlotRemaining+(processingRate*(int)(timeFrameSpent+1))), queueSize);
            queueSlotRemaining--;
            return true;
        }
    }
}
