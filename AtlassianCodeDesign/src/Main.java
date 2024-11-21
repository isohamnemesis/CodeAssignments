import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {

        Router router = new Router();
        System.out.println(router.callRoute("/bar"));

        router.addRoute("/bar", "result");
        router.addRoute("/bar", "result1");
        System.out.println(router.callRoute("/bar"));


        router.addRoute("/foo/*/bar", "result2");
        router.addRoute("/foo/*/par", "result5");
        System.out.println(router.callRoute("/foo/loo/bar"));
        System.out.println(router.callRoute("/foo/loo/par"));

        router.addRoute("/foo/tar/*", "result6");
        System.out.println(router.callRoute("/foo/tar/look"));

        router.addRoute("/foo/bar/lab", "result3");
        System.out.println(router.callRoute("/foo/bar/lab"));
        router.addRoute("/cd/de/ef", "result4");
        System.out.println(router.callRoute("/cd/de/ef"));

        System.out.println(router.callRoute("/of/lp"));

        System.out.println(router.callRoute(""));
        System.out.println(router.callRoute("/"));

    }
}