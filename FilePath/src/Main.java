import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Router router = Router.getInstance();


        System.out.println(router.addRoute("/bar", "result"));
        System.out.println(router.callRoute("/bar"));

        System.out.println(router.addRoute("/foo/bar", "result2"));
        System.out.println(router.callRoute("/foo/bar"));

        System.out.println(router.addRoute("/bar/abc", "abc"));
        System.out.println(router.callRoute("/bar/abc"));

        System.out.println(router.callRoute("/bar/abc/dd"));

        System.out.println(router.addRoute("/bar/abc/dd", "dd"));
        System.out.println(router.addRoute("/bar/abc/cde/dd", "ee"));
        System.out.println(router.callRoute("/bar/abc/dd"));
        System.out.println(router.callRoute("/bar/*/dd"));
    }
}


//  Router.withRoute("/bar", "result")
//  Router.route("/bar") -> "result"
//
//  Router.withRoute("/bar/abc", "abc")
//  Router.route("/bar/abc") -> "abc"
//  Router.route("/bar/abc/dd") -> null
//
//  Router.withRoute("/bar/abc/dd", "dd")
//  Router.withRoute("/bar/abc/cde/dd") -> "ee"
//  Router.route("/bar/abc/dd") -> "dd"
//  Router.route("/bar/*/dd") -> "dd"
