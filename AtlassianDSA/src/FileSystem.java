import javafx.util.Pair;

import java.util.*;

public class FileSystem {
    private int totalFileSize;
    Map<String, File> files;
    Map<String, Collectionn> collections;
    private static FileSystem fileSystem;

    private FileSystem(){
        this.totalFileSize = 0;
        this.files = new HashMap<>();
        this.collections = new HashMap<>();
    }

    public static FileSystem getInstance(){
        if(fileSystem==null){
            fileSystem = new FileSystem();
        }

        return fileSystem;
    }

    public void addFile(String fileName, int fileSize){

        if(!files.containsKey(fileName)){
            files.putIfAbsent(fileName, new File(fileName, fileSize, null));
            totalFileSize += fileSize;
        }
    }

    public void addFile(String fileName, int fileSize, String parent){
        if(parent==null){
            addFile(fileName, fileSize);
        }

        collections.putIfAbsent(parent, new Collectionn(parent));
        if(!files.containsKey(fileName)){
            files.putIfAbsent(fileName, new File(fileName, fileSize, collections.get(parent)));
            totalFileSize += fileSize;
        }

        collections.get(parent).addFile(files.get(fileName));
    }

    public List<Collectionn> rankingSystem(int N){

        if(N>collections.size())
        {
            N = Integer.min(N, collections.size());
        }

        PriorityQueue<Pair<Integer, Collectionn>> ranker = new PriorityQueue<>((p1, p2) -> p1.getKey().compareTo(p2.getKey()));

        int n = N;
        for(Map.Entry<String, Collectionn> e:collections.entrySet()){
            if(n==0){
                Collectionn minCollection = ranker.peek().getValue();
                Collectionn newCollection = e.getValue();

                if (!ranker.isEmpty() && newCollection.getCollectionSize() >= minCollection.getCollectionSize()) {
                    ranker.poll();
                    ranker.add(new Pair<>(newCollection.getCollectionSize(), newCollection));
                }
            }
            else{
                ranker.add(new Pair<>(e.getValue().getCollectionSize(), e.getValue()));
                n--;
            }

        }

        List<Collectionn> rankedCollections = new ArrayList<>();

        while(N>0){
            rankedCollections.add(ranker.poll().getValue());
            N--;
        }

        Collections.reverse(rankedCollections);
        return rankedCollections;
    }

    public int getTotalFileSize(){
        return totalFileSize;
    }
}
