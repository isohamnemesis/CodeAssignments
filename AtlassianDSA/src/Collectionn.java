public class Collectionn {

    private String collectionName;
    private int collectionSize;

    public Collectionn(String collectionName){
        this.collectionName = collectionName;
        this.collectionSize = 0;
    }

    public void addFile(File file){
        file.setParent(this);
        this.collectionSize += file.getFileSize();
    }

    public String getCollectionName() {
        return collectionName;
    }

    public int getCollectionSize() {
        return collectionSize;
    }

}
