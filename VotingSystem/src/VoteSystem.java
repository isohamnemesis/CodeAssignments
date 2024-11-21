import java.util.*;

public class VoteSystem {
    private String[][] votes;

    public VoteSystem(String[][] votes){
        this.votes = votes;
    }

    public List<String> rankingSystem(){

        int totalVotes = votes.length;
        int totalTeams = votes[0].length;

        Map<String, Team> teams = new HashMap<>();

        for(int i=0; i<totalTeams; i++){
            teams.putIfAbsent(votes[0][i], new Team(votes[0][i], totalTeams));
        }

        for(int i=0; i<totalVotes; i++){
            for(int j=0; j<totalTeams; j++){
                teams.get(votes[i][j]).incrementRank(j);
            }
        }

        PriorityQueue<Team> pq = new PriorityQueue<>(new TeamComparator());

        for(Map.Entry<String, Team> e:teams.entrySet()){
            pq.add(e.getValue());
        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll().getLabel());
        }

        return result;
    }
}
