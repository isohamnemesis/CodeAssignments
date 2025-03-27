package utils;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice 3 sets of push-ups with 15 reps each.";
    }
}
