
import java.util.*;

public class Driver {
    private Map<Integer, RateLimiter> rateLimiterMap;
    private int bucketSize;
    private int windowSize;

    Driver(int bucketSize, int windowSize){
        rateLimiterMap = new HashMap<>();
        this.bucketSize = bucketSize;
        this.windowSize = windowSize;
    }

    boolean rateLimit(int customerID){
        if(!rateLimiterMap.containsKey(customerID)){
            addRateLimiter(customerID);
        }
        RateLimiter rateLimiter = rateLimiterMap.get(customerID);
        boolean response = rateLimiter.request();
        rateLimiterMap.replace(customerID, rateLimiter);
        return response;
    }

    void addRateLimiter(int customerID){
        rateLimiterMap.put(customerID, new RateLimiter(bucketSize, windowSize));
    }
}
