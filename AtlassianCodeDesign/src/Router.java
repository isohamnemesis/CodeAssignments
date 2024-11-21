import java.util.Map;

public class Router {

    private Node root;

    Router(){
        root = Node.getInstance();
        root.setPath("", Node.getInstance());
    }

    public void addRoute(String path, String value){

        Node node = root;

        String[] paths = path.split("/");

        for(int i=0; i<paths.length; i++){

            if(!node.containsPath(paths[i])){
                node.setPath(paths[i], Node.getInstance());
            }

            node = node.getPathNode(paths[i]);
        }

        node.setValue(value);
    }

    public String callRoute(String path){

        if(path.equals("") || path.equals(" ") || path.equals("/"))
            return "Path not found!";

        Node node = root;

        String[] paths = path.split("/");

//        for(int i=0; i<paths.length; i++){
//
//            if(!node.containsPath(paths[i])){
//                return "PathName not found!";
//            }
//            else{
//                node = node.getPathNode(paths[i]);
//            }
//        }
//
//        return node.getValue();

        return recursePath(paths, 0, node);
    }

    private String recursePath(String[] paths, int index, Node node){

        if(index==paths.length){
            return node.getValue();
        }
        else {
            if (node.containsPath(paths[index])) {
                node = node.getPathNode(paths[index]);
                return recursePath(paths, index + 1, node);
            }
            else if (node.containsPath("*")) {

                node = node.getPathNode("*");

                if(index==paths.length-1)
                    return node.getValue();

                for (Map.Entry<String, Node> e : node.getPathNames().entrySet()) {
                    String nextPath = e.getKey();
                    if(nextPath.equals(paths[index+1])){
                        return recursePath(paths, index + 1, node);
                    }
                }
                return "Path not found!";
            }
            else {
                return "Path not found!";
            }
        }
    }
}