import javafx.util.Pair;
import java.util.*;

public class FileManagement {

    private int totalFileSize;
    private Map<String, File> files;
    private Map<String, Directory> directories;
    private static FileManagement fileManagement = new FileManagement();

    private FileManagement(){
        totalFileSize = 0;
        files = new HashMap<>();
        directories = new HashMap<>();
    }

    public static FileManagement getInstance(){
        return fileManagement;
    }

    public void addFile(String fileName, int fileSize){
        if(!files.containsKey(fileName)){
            files.put(fileName, File.getInstance(fileName, fileSize, null));
            totalFileSize += fileSize;
        }
    }

    public void addFile(String fileName, int fileSize, String directoryName){

        directories.putIfAbsent(directoryName, Directory.getInstance(directoryName, null));

        if(!files.containsKey(fileName)){

            files.put(fileName, File.getInstance(fileName, fileSize, directories.get(directoryName)));
            totalFileSize += fileSize;
        }

        directories.get(directoryName).addFile(files.get(fileName));
    }

    public int getTotalFileSize(){
        return totalFileSize;
    }

    public void addDirectory(String parentDirectory, String childDirectory){

        directories.putIfAbsent(parentDirectory, Directory.getInstance(parentDirectory, null));
        directories.get(parentDirectory).addDirectory(directories.get(childDirectory));
    }

    public List<Directory> rankDirectories(int k){
        PriorityQueue<Pair<Integer, Directory>> rankSystem = new PriorityQueue<>( (p1, p2) -> p2.getKey().compareTo(p1.getKey()) );
        List<Directory> rank = new ArrayList<>();

        for(String key:directories.keySet()){
            Directory directory = directories.get(key);
            rankSystem.add(new Pair<>(directory.getDirectorySize(), directory));
        }

        k = Integer.min(directories.size(), k);

        while(k>0){
            rank.add(rankSystem.poll().getValue());
            k--;
        }

        return rank;
    }
}
