import java.util.HashMap;
import java.util.Map;

public class RateLimiterDriver {
    private Map<String, RateLimiter> customerRateLimiterStore;
    private int maximumRequestsAllowed;
    private int windowSize;

    private static RateLimiterDriver rateLimiterDriver;

    private RateLimiterDriver(int maximumRequestsAllowed, int windowSize){
        this.maximumRequestsAllowed = maximumRequestsAllowed;
        this.windowSize = windowSize;
        customerRateLimiterStore = new HashMap<>();
    }

    boolean rateLimit(String customerId){
        if(!customerRateLimiterStore.containsKey(customerId)){
            customerRateLimiterStore.put(customerId, RateLimiter.getInstance(maximumRequestsAllowed, windowSize));
        }

        RateLimiter rateLimiter = customerRateLimiterStore.get(customerId);
        return rateLimiter.rateLimit();
    }

    public static RateLimiterDriver getInstance(int maximumRequestsAllowed, int windowSize){
        if(rateLimiterDriver==null){
            rateLimiterDriver = new RateLimiterDriver(maximumRequestsAllowed, windowSize);
        }

        return rateLimiterDriver;
    }
}
