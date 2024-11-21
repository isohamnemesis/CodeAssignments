
import java.time.*;

public class RateLimiter {

    private int bucketSize;
    private int windowSize;
    private int remTokens;
    LocalTime timer;

    RateLimiter(int bucketSize, int windowSize){
        timer = getLocalTime();
        this.bucketSize = bucketSize;
        this.windowSize = windowSize;
        remTokens = bucketSize;
    }

    private LocalTime getLocalTime() {
        return LocalTime.now();
    }

    boolean request(){
        LocalTime currentTime = getLocalTime();
        Duration diff = Duration.between(currentTime, timer);
        if(diff.toHours()>0 || (diff.toMinutes()%60)>0 || (diff.getSeconds()%60)>windowSize){
            remTokens = bucketSize-1;
            return true;
        }
        else{
            if(remTokens>0)
            {
                remTokens--;
                return true;
            }
            else{
                return false;
            }
        }
    }

}
