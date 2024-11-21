import javafx.util.Pair;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = FileSystem.getInstance();

        fileSystem.addFile("file1.txt", 100, null);
        fileSystem.addFile("file2.txt", 200, "collection1");
        fileSystem.addFile("file3.txt", 200, "collection1");
        fileSystem.addFile("file3.txt", 200, "collection2");
        fileSystem.addFile("file4.txt", 300, "collection2");
        fileSystem.addFile("file5.txt", 10, null);

        List<Collectionn> rankedCollections = fileSystem.rankingSystem(2);
        for(Collectionn collectionn:rankedCollections){
            System.out.println(collectionn.getCollectionName()+" "+collectionn.getCollectionSize());
        }

        System.out.println(fileSystem.getTotalFileSize());
    }
}