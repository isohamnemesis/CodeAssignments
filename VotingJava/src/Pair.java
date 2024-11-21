
import java.util.*;

public class Pair {

    private int weight;
    private String idea;


    Pair(int weight, String idea){
        this.weight = weight;
        this.idea = idea;
    }


    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
