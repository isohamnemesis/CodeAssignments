
public class Team {

    private String label;
    private int[] ranks;

    public Team(String label, int totalTeams){
        this.label = label;
        this.ranks = new int[totalTeams];
    }

    void incrementRank(int pos){
        ranks[pos]++;
    }

    int[] getRanks(){
        return ranks;
    }

    public String getLabel() {
        return label;
    }
}
