import java.util.HashMap;
import java.util.Map;

public class Route{
    Map<String, Route> routes;
    String value;

    Route(){
        routes = new HashMap<>();
        value = "";
    }

    public boolean containsRoute(String route){
        return routes.containsKey(route);
    }

    public Route getRoute(String route){
        return routes.get(route);
    }

    public void setRoute(String route, Route nextRoute){
        routes.put(route, nextRoute);
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static Route getInstance(){
        return new Route();
    }
}