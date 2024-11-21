import javafx.util.Pair;

import java.util.Comparator;

public class PairComparator implements Comparator<Pair<Integer, Directory>> {

    public int compare(Pair<Integer, Directory> pair1, Pair<Integer, Directory> pair2){
        return pair2.getKey().compareTo(pair1.getKey());
    }
}
