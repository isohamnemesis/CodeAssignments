import javafx.util.Pair;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int X = 5;
        int Y = 3;
        RateLimiterDriver rateLimiterDriver = RateLimiterDriver.getInstance(X, Y);
        String ID1 = "Soham";
        for(int i=0; i<5; i++){
            System.out.println("The request from " + ID1 + " is " +((rateLimiterDriver.rateLimit(ID1))?"allowed!":"not allowed!") );
            Thread.sleep(500);
        }

        Thread.sleep(1000);

        for(int i=0; i<5; i++){
            System.out.println("The request from " + ID1 + " is " +((rateLimiterDriver.rateLimit(ID1))?"allowed!":"not allowed!") );
            Thread.sleep(500);
        }

        String ID2 = "Rico";
        for(int i=0; i<5; i++){
            System.out.println("The request from " + ID2 + " is " +((rateLimiterDriver.rateLimit(ID2))?"allowed!":"not allowed!") );
            Thread.sleep(300);
        }

        Thread.sleep(2000);

        for(int i=0; i<5; i++){
            System.out.println("The request from " + ID2 + " is " +((rateLimiterDriver.rateLimit(ID2))?"allowed!":"not allowed!") );
            Thread.sleep(300);
        }
    }
}