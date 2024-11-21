import java.util.*;

public class RateLimiter {

    List<Integer> requests = new ArrayList<>();
    int rate;
    int timer;

    RateLimiter(int rate, int reqsize){
        while((reqsize--)>0)
            requests.add(0);

        this.rate = rate;

        this.timer = 1;

    }

    boolean rateLimit(int customerId, int time){

        if(time==timer){
            if(requests.get(0)>=rate)
                return false;
            else {
                requests.set(0, requests.get(0) + 1);
                return true;
            }
        }
        else if(time==timer+1){
            if(requests.get(0)+requests.get(1)>=rate){
                return false;
            }
            else{
                requests.set(1, requests.get(1)+1);
                return true;
            }
        }
        else{
            timer = time;
            requests.set(0, 1);
            requests.set(1, 0);
            return true;
        }
    }
}
