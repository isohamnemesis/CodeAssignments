import javafx.util.Pair;

import java.util.*;

public class Badging {

    private static List<String> findIfEmployeeExceededBadgingLimitInAnHour(Set<Integer> badgeTimings){

        List<String> result = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        ArrayDeque<Integer> resultQ = new ArrayDeque<>();

        for(Integer timing:badgeTimings){

            Integer windowStartTiming = Math.max((timing-100), 0);
            while(!q.isEmpty() && q.peek()<windowStartTiming){
                if(q.size()>=3){
                    resultQ = q;
                    break;
                }
                else{
                    q.poll();
                }
            }

            if(!resultQ.isEmpty())
                break;

            q.add(timing);
        }

        if(resultQ.isEmpty() && q.size()>=3)
            resultQ = q;

        while(!resultQ.isEmpty()){
            result.add(String.valueOf(resultQ.poll()));
        }

        return result;
    }

    public static Map<String, List<String>> badgingSystem(List<Pair<String, String>> badges){

        Map<String, Set<Integer>> badgeStore = new HashMap<>();

        for(Pair<String, String> e:badges){

            String employee = e.getKey();
            String badgeTiming = e.getValue();

            badgeStore.putIfAbsent(employee, new TreeSet<>());

            badgeStore.get(employee).add(Integer.parseInt(badgeTiming));
        }

        Map<String, List<String>> result = new HashMap<>();

        for(Map.Entry<String, Set<Integer>> e:badgeStore.entrySet()){
            String employee = e.getKey();
            Set<Integer> badgeTimings = e.getValue();

            List<String> oneHourBadging = findIfEmployeeExceededBadgingLimitInAnHour(badgeTimings);
            if(!oneHourBadging.isEmpty())
                result.put(employee, oneHourBadging);
        }

        return result;
    }
}
