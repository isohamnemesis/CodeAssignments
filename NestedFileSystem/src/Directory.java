import java.util.HashMap;
import java.util.Map;

public class Directory {

    String name;
    int size;
    Directory parent;
    Map<String, Directory> directoryMap;
    Map<String, File> fileMap;

    Directory(String name, Directory parent){
        this.name = name;
        this.parent = parent;
        this.directoryMap = new HashMap<>();
        this.fileMap = new HashMap<>();
    }


    public int getSize() {
        return size;
    }


    public void setSize(int size) {
        this.size = size;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Directory getParent() {
        return parent;
    }


    public void setParent(Directory parent) {
        Directory par = parent;
        this.parent = par;
    }

    void addFile(String name, int fileSize){
        File file = new File(name, fileSize, this);

        Directory parent = this;
        fileMap.put(file.getName(), file);
        while(parent!=null){
            parent.setSize(parent.getSize()+fileSize);
            Directory par = parent.getParent();
            if(par!=null){
                par.addDirectory(this);
            }

            parent = parent.getParent();
        }


    }

    void addDirectory(Directory directory){
        directoryMap.replace(directory.getName(), directory);
        this.setSize(size+directory.getSize());
    }

}
