import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private int size;
    private List<File> files;

    Directory(String name){
        this.name = name;
        this.size = 0;
        this.files = new ArrayList<>();
    }

    void addFile(File file){
        size += file.getSize();
        files.add(file);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<File> getFiles() {
        return files;
    }
}
