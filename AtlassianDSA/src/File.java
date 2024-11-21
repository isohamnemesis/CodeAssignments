public class File {

    private String fileName;
    private int fileSize;
    Collectionn parent;

    public File(String fileName, int fileSize, Collectionn parent){
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.parent = parent;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setParent(Collectionn parent) {
        this.parent = parent;
    }

    public String getFileName() {
        return fileName;
    }
}
