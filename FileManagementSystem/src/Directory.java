import java.util.ArrayList;
import java.util.List;

public class Directory {

    private String directoryName;
    private int directorySize;
//    private List<File> files;
//    private List<Directory> directories;
    private Directory parent;

    Directory(String directoryName, Directory parent){
        this.directoryName = directoryName;
        this.directorySize = 0;
//        this.files = new ArrayList<>();
//        this.directories = new ArrayList<>();
        this.parent = parent;
    }

    public static Directory getInstance(String directoryName, Directory parent){
        return new Directory(directoryName, parent);
    }

    void addFile(File file){
//        files.add(file);
        file.setParent(this);
        Directory ancestor = this;
        while(ancestor!=null){
            ancestor.addSize(file.getFileSize());
            ancestor = ancestor.parent;
        }
    }

    void addDirectory(Directory directory){

//        directories.add(directory);
        directory.setParent(this);
        Directory ancestor = this;
        while(ancestor!=null){
            ancestor.addSize(directory.getDirectorySize());
            ancestor = ancestor.parent;
        }
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public int getDirectorySize() {
        return directorySize;
    }

    public void addSize(int size){
        this.directorySize += size;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }
}
