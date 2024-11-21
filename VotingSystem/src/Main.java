import java.util.List;

public class Main {
    public static void main(String[] args) {

        int totalVotes, totalTeams;
        totalVotes = 5;
        totalTeams = 4;

        String[][] input = new String[totalVotes][totalTeams];

        input[0] = new String[]{"Team1", "Team2", "Team3", "Team4"};
        input[1] = new String[]{"Team1", "Team4", "Team2", "Team3"};
        input[2] = new String[]{"Team2", "Team3", "Team1", "Team4"};
        input[3] = new String[]{"Team3", "Team1", "Team4", "Team2"};
        input[4] = new String[]{"Team3", "Team4", "Team2", "Team1"};

        VoteSystem voteSystem = new VoteSystem(input);
        List<String> result = voteSystem.rankingSystem();
        System.out.println(result);

        totalVotes = 5;
        totalTeams = 3;

        String[][] input1 = new String[totalVotes][totalTeams];

        input1[0] = new String[]{"A", "B", "C"};
        input1[1] = new String[]{"A", "C", "B"};
        input1[2] = new String[]{"A", "B", "C"};
        input1[3] = new String[]{"A", "C", "B"};
        input1[4] = new String[]{"A", "C", "B"};

        VoteSystem voteSystem1 = new VoteSystem(input1);
        result = voteSystem1.rankingSystem();
        System.out.println(result);

        totalVotes = 2;
        totalTeams = 4;

        String[][] input2 = new String[totalVotes][totalTeams];

        input2[0] = new String[]{"W","X","Y","Z"};
        input2[1] = new String[]{"X","Y","Z","W"};

        VoteSystem voteSystem2 = new VoteSystem(input2);
        result = voteSystem2.rankingSystem();
        System.out.println(result);

        totalVotes = 1;
        totalTeams = 26;

        String[][] input3 = new String[totalVotes][totalTeams];

        input3[0] = new String[]{"Z","M","N","A","G","U","E","D","S","J","Y","L","B","O","P","H","R","Q","I","C","W","F","X","T","V","K"};

        VoteSystem voteSystem3 = new VoteSystem(input3);
        result = voteSystem3.rankingSystem();
        System.out.println(result);

        totalVotes = 6;
        totalTeams = 3;

        String[][] input4 = new String[totalVotes][totalTeams];

        input4[0] = new String[]{"B","C","A"};
        input4[1] = new String[]{"C","A","B"};
        input4[2] = new String[]{"C","B","A"};
        input4[3] = new String[]{"A","B","C"};
        input4[4] = new String[]{"A","C","B"};
        input4[5] = new String[]{"B","A","C"};

        VoteSystem voteSystem4 = new VoteSystem(input4);
        result = voteSystem4.rankingSystem();
        System.out.println(result);

    }
}