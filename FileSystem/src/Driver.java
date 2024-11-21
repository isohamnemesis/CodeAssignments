import javafx.util.Pair;

import java.util.*;

public class Driver {

    Map<String, Directory> directories;
    Map<String, File> files;

    Driver(){
        directories = new HashMap<>();
        files = new HashMap<>();
    }

    Directory addDirectory(String name){
        Directory directory = new Directory(name);
        directories.put(name, directory);
        return directory;
    }

    void addFile(String name, int size, String directoryName){

        Directory directory;
        if(!directories.containsKey(directoryName)){
            directory = addDirectory(directoryName);
        }
        else{
            directory = directories.get(directoryName);
        }

        File file = new File(size, name);
        directory.addFile(file);
        directories.replace(directoryName, directory);
        files.put(name, file);
    }

    void addFile(String name, int size){
        File file = new File(size, name);
        files.put(name, file);
    }

    List<Directory> topKCollection(int K){
        if(K>directories.size()) {
            System.out.println("Not enough directories");
        }

        PriorityQueue<Pair<Integer, Directory>> pq = new PriorityQueue<>(new PairComparator());
        for(Map.Entry<String, Directory> e: directories.entrySet()){
            Directory directory = e.getValue();
            pq.add(new Pair<>(directory.getSize(), directory));
        }

        List<Directory> result = new ArrayList<>();
        K = ((K<directories.size())?K: directories.size());
        while(K>0){
            K--;
            Pair<Integer, Directory> pair = pq.poll();
            result.add(pair.getValue());
        }

        return result;
    }
}
