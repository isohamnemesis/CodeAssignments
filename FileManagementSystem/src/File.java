public class File {

    private String fileName;
    private int fileSize;
    private Directory parent;

    File(String fileName, int fileSize, Directory parent){
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.parent = parent;
    }

    public static File getInstance(String fileName, int fileSize, Directory parent){
        return new File(fileName, fileSize, parent);
    }

    public String getFileName() {
        return fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent){
        this.parent = parent;
    }
}
