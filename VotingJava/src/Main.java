import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> b1 = new ArrayList<>();
        List<String> b2 = new ArrayList<>();
        List<String> b3 = new ArrayList<>();
        List<String> b4 = new ArrayList<>();

        b1.add("candidate2");
        b1.add("candidate3");
        b1.add("candidate1");

        b2.add("candidate4");
        b2.add("candidate2");
        b2.add("candidate1");

        b3.add("candidate3");
        b3.add("candidate2");
        b3.add("candidate1");

        b4.add("candidate2");
        b4.add("candidate4");
        b4.add("candidate3");

        Ballot ballot1 = new Ballot(3, b1);
        Ballot ballot2 = new Ballot(3, b2);
        Ballot ballot3 = new Ballot(3, b3);
        Ballot ballot4 = new Ballot(3, b4);

        List<Ballot> ballots = new ArrayList<>();
        ballots.add(ballot1);
        ballots.add(ballot2);
        ballots.add(ballot3);
        ballots.add(ballot4);

        BallotOrder ballotOrder = new BallotOrder(ballots);
        List<String> result = ballotOrder.rankIdeas();
        //System.out.println(result);

        String str = "I live in Bangalore. I studied at NITK.";
        String[] splits = str.split("\\s+");
        List<String> words = new ArrayList<>();
        for(String s:splits){
            words.add(s);
        }

        System.out.println(words.size());
        for(String w:words){
            System.out.println(w);
        }
    }
}
