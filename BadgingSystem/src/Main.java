import javafx.util.Pair;

import java.util.*;

import static java.util.Map.*;


public class Main {
    public static void main(String[] args) {

        List<Pair<String, String>> input = List.of(
                new Pair<>("Paul", "1355"),
                new Pair<>("Jennifer", "1910"),
                new Pair<>("Jose", "835"),
                new Pair<>("Jose", "830"),
                new Pair<>("Paul", "1315"),
                new Pair<>("Chloe", "0"),
                new Pair<>("Chloe", "1910"),
                new Pair<>("Jose", "1615"),
                new Pair<>("Jose", "1640"),
                new Pair<>("Paul", "1405"),
                new Pair<>("Jose", "855"),
                new Pair<>("Jose", "930"),
                new Pair<>("Jose", "915"),
                new Pair<>("Jose", "730"),
                new Pair<>("Jose", "940"),
                new Pair<>("Jennifer", "1335"),
                new Pair<>("Jennifer", "730"),
                new Pair<>("Jose", "1630"),
                new Pair<>("Jennifer", "5"),
                new Pair<>("Chloe", "1909"),
                new Pair<>("Zhang", "1"),
                new Pair<>("Zhang", "10"),
                new Pair<>("Zhang", "109"),
                new Pair<>("Zhang", "110"),
                new Pair<>("Amos", "1"),
                new Pair<>("Amos", "2"),
                new Pair<>("Amos", "400"),
                new Pair<>("Amos", "500"),
                new Pair<>("Amos", "503"),
                new Pair<>("Amos", "504"),
                new Pair<>("Amos", "601"),
                new Pair<>("Amos", "602"),
                new Pair<>("Paul", "1416")
        );

        var result = Badging.badgingSystem(input);
        System.out.println(result);
    }
}