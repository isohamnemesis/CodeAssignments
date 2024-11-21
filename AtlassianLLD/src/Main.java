public class Main {
    public static void main(String[] args) {

        RateLimiter rateLimiter = new RateLimiter(5, 2);

        System.out.println(rateLimiter.rateLimit(1, 1));
        System.out.println(rateLimiter.rateLimit(1, 1));
        System.out.println(rateLimiter.rateLimit(1, 1));
        System.out.println(rateLimiter.rateLimit(1, 1));
        System.out.println(rateLimiter.rateLimit(1, 1));
        System.out.println(rateLimiter.rateLimit(1, 1));
        System.out.println(rateLimiter.rateLimit(1, 2));
        System.out.println(rateLimiter.rateLimit(1, 10));
        System.out.println(rateLimiter.rateLimit(1, 11));
    }
}

// Perform rate limiting logic for provided customer ID. Return true if the

// request is allowed, and false if it is not.

//Each customer can make 5 requests per 2 seconds


//boolean rateLimit(int customerId){
//
//}