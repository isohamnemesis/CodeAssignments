import java.util.HashMap;
import java.util.Map;

public class RateLimiterDriver {
    private int maximumRequestsAllowed;
    private int windowSize;
    private Map<String, RateLimiter> customerRateLimiterStore;

    private static RateLimiterDriver rateLimiterDriver;

    private RateLimiterDriver(int maximumRequestsAllowed, int windowSize){
        this.maximumRequestsAllowed = maximumRequestsAllowed;
        this.windowSize = windowSize;
        this.customerRateLimiterStore = new HashMap<>();
    }

    public static RateLimiterDriver getInstance(int maximumRequestsAllowed, int windowSize){
        if(rateLimiterDriver==null){
            rateLimiterDriver = new RateLimiterDriver(maximumRequestsAllowed, windowSize);
        }

        return rateLimiterDriver;
    }

    public boolean rateLimit(String customerID){

        customerRateLimiterStore.putIfAbsent(customerID, RateLimiter.getInstance(maximumRequestsAllowed, windowSize));
        RateLimiter rateLimiter = customerRateLimiterStore.get(customerID);
        return rateLimiter.rateLimit();
    }
}
