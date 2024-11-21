
import java.util.*;

public class BallotOrder {
    List<Ballot> ballots;

    BallotOrder(List<Ballot> ballots){
        this.ballots = ballots;
    }

    public List<String> rankIdeas(){

        List<String> result = new ArrayList<>();
        Map<String, Integer> store = new HashMap<String, Integer>();
        PriorityQueue<Pair> qstore = new PriorityQueue<Pair>((p1, p2)->(p2.getWeight()-p1.getWeight()));

        for(int i=0; i<ballots.size(); i++){
            List<String> ideas = ballots.get(i).getIdeas();

            for(int j=0; j<ideas.size(); j++){
                if(!store.containsKey(ideas.get(j))){
                    store.put(ideas.get(j), ballots.get(i).getWeight(j));
                }
                else{
                    int oldWeight = store.get(ideas.get(j));
                    store.replace(ideas.get(j), oldWeight+ballots.get(i).getWeight(j));
                }
            }
        }

        for(Map.Entry<String, Integer> e:store.entrySet()){
            String key = e.getKey();
            Integer value = e.getValue();

            qstore.add(new Pair(value, key));
        }

        while(!qstore.isEmpty()){
            Pair pair = qstore.poll();
            result.add(pair.getIdea());
        }

        return result;
    }
}
