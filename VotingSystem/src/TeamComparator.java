import java.util.Arrays;
import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {
    @Override
    public int compare(Team o1, Team o2) {
        if(!Arrays.equals(o1.getRanks(), o2.getRanks())){
            return Arrays.compare(o2.getRanks(), o1.getRanks());
        }
        else{
            return o1.getLabel().compareTo(o2.getLabel());
        }
    }
}
