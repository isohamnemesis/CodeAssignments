
import java.util.List;

public class Ballot {

    private int weight;
    private List<String> ideas;

    Ballot(int weight, List<String> ideas){
        this.weight = weight;
        this.ideas = ideas;
    }


    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<String> ideas) {
        this.ideas = ideas;
    }

    public String getIdea(int pos){
        return ideas.get(pos);
    }

    public int getWeight(int pos){
        return (weight-pos);
    }

    public int getSize(){
        return ideas.size();
    }
}
