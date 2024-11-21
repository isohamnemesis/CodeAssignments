
import java.util.Map;

public class Router{

    private Route root;

    private static Router router = new Router();


    private Router(){
        root = Route.getInstance();
        root.setRoute("", Route.getInstance());
    }

    public static Router getInstance(){
        return router;
    }

    public boolean addRoute(String routePath, String value){
        Route route = root;

        String[] routes = routePath.split("/");

        for(int i=0; i<(routes.length); i++){
            if(!route.containsRoute(routes[i])) {
                route.setRoute(routes[i], Route.getInstance());
            }
            route = route.getRoute(routes[i]);
        }

        route.setValue(value);
        return true;
    }

    public String callRoute(String routePath){
        Route route = root;

        String[] routes = routePath.split("/");
        String result = recurseRoute(routes, 0, route);
        return (result.equals(""))?null:result;
    }

    private String recurseRoute(String[] routes, int index, Route route){

        if(routes[index].equals("*")){
            if(index==routes.length-1)
                return "";
            else{
                String result = "";
                for(Map.Entry<String, Route> e:route.routes.entrySet()){

                    Route node = e.getValue();

                    if(node.containsRoute(routes[index+1])){
                        result = recurseRoute(routes, index+1, node);
                        return result;
                    }
                    else{
                        String res = recurseRoute(routes, index, node);
                        if(!res.equals("")){
                            result = res;
                        }
                    }
                }

                return result;
            }
        }
        else{
            if(index==routes.length-1){
                if(route.containsRoute(routes[index])){
                    route = route.getRoute(routes[index]);
                    return route.getValue();
                }
                else
                    return "";
            }
            else{
                if(route.containsRoute(routes[index])){
                    route = route.getRoute(routes[index]);
                    return recurseRoute(routes, index+1, route);
                }
                else
                    return "";
            }
        }
    }
}









//        for(int i=0; i<routes.length; i++){
//            if(route.containsRoute(routes[i])){
//                route = route.getRoute(routes[i]);
//            }
//            else{
//                return "Path doesn't exist";
//            }
//        }
//
//        return route.getValue();