import java.util.HashMap;
import java.util.Map;

public class Node {

    private Map<String, Node> pathNames;
    private String value;

    Node(){
        this.pathNames = new HashMap<>();
        this.value = "";
    }

    public boolean containsPath(String path){
        return pathNames.containsKey(path);
    }

    public Node getPathNode(String path){
        return pathNames.get(path);
    }

    public void setPath(String path, Node node){
        pathNames.put(path, node);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Node getInstance(){
        return new Node();
    }

    public Map<String, Node> getPathNames(){
        return pathNames;
    }
}
