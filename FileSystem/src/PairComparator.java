import javafx.util.Pair;
import java.util.Comparator;

public class PairComparator implements Comparator<Pair<Integer, Directory>> {
    @Override
    public int compare(Pair<Integer, Directory> p1, Pair<Integer, Directory> p2){
        return p2.getKey().compareTo(p1.getKey());
    }
}
