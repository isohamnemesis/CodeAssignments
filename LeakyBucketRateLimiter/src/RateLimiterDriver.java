import java.util.HashMap;
import java.util.Map;

public class RateLimiterDriver {
    private Map<String, RateLimiter> customerRateLimiterStore;
    private Map<String, Integer> customerRating;
    private int queueSize;
    private int windowSize;
    private int processingRate;

    private static RateLimiterDriver rateLimiterDriver;

    RateLimiterDriver(int queueSize, int processingRate, int windowSize){
        this.queueSize = queueSize;
        this.windowSize = windowSize;
        this.processingRate = processingRate;
        this.customerRateLimiterStore = new HashMap<>();
        this.customerRating = new HashMap<>();
    }

    public static RateLimiterDriver getInstance(int queueSize, int windowSize, int processingRate){
        if(rateLimiterDriver==null){
            rateLimiterDriver = new RateLimiterDriver(queueSize, windowSize, processingRate);
        }
        return rateLimiterDriver;
    }

    public boolean rateLimit(String customerID){

        customerRateLimiterStore.putIfAbsent(customerID, RateLimiter.getInstance(queueSize, windowSize, processingRate));
        customerRating.putIfAbsent(customerID, 0);
        RateLimiter rateLimiter = customerRateLimiterStore.get(customerID);
        boolean flag = rateLimiter.rateLimit();

        if(flag==true){
            customerRating.put(customerID, customerRating.get(customerID)+1);
        }
        else{
            customerRating.put(customerID, customerRating.get(customerID)-1);
        }

        return flag;
    }

    public int getCustomerRating(String customerID){
        return customerRating.get(customerID);
    }
}
