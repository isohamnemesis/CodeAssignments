import javafx.util.Pair;

import java.util.*;

public class Driver {

    Map<String, Directory> directories;

    Driver(){
        directories = new HashMap<>();
    }

    void addDirectory(String name, String directoryName){

        Directory directory;
        if(!directories.containsKey(directoryName)){
            directory = new Directory(directoryName, null);
        }
        else{
            directory = directories.get(directoryName);
        }

        if(!directories.containsKey(name)){
            Directory currDirectory = new Directory(name, directory);
            directory.addDirectory(currDirectory);
            directories.put(name, currDirectory);
            if(!directories.containsKey(directoryName)) {
                directories.put(directoryName, directory);
            }
            else{
                directories.replace(directoryName, directory);
            }

        }
        else{
            Directory currDirectory = directories.get(name);
            directory.addDirectory(currDirectory);

            if(!directories.containsKey(directoryName)) {
                directories.put(directoryName, directory);
            }
            else{
                directories.replace(directoryName, directory);
            }
        }

    }

    void addFile(String name, int size, String directoryName){

        Directory directory;
        if(!directories.containsKey(directoryName)){
            directory = new Directory(directoryName, null);
            directory.addFile(name, size);
            directories.put(directoryName, directory);
        }
        else{
            directory = directories.get(directoryName);
            directory.addFile(name, size);
            directories.replace(directoryName, directory);
        }


    }

    List<Directory> topKCollection(int k){

        if(k>directories.size()){
            System.out.println("Too less directories");
        }

        k = (k<directories.size())?k:directories.size();

        List<Directory> result = new ArrayList<>();
        PriorityQueue<Pair<Integer, Directory>> pq = new PriorityQueue<>(new PairComparator());

        for(Map.Entry<String, Directory> e:directories.entrySet()){
            pq.add(new Pair(e.getValue().getSize(), e.getValue()));
        }

        while(k>0){
            k--;
            Pair<Integer, Directory> pair = pq.poll();
            result.add(pair.getValue());
        }

        return result;
    }
}
