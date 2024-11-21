public class Main {
    public static void main(String[] args) {

        Driver driver = new Driver(5, 2);
        System.out.println(driver.rateLimit(1));
        System.out.println(driver.rateLimit(1));
        System.out.println(driver.rateLimit(1));
        System.out.println(driver.rateLimit(1));
        System.out.println(driver.rateLimit(1));
        System.out.println(driver.rateLimit(1));

        System.out.println(driver.rateLimit(2));
        System.out.println(driver.rateLimit(2));
        System.out.println(driver.rateLimit(2));
        System.out.println(driver.rateLimit(2));

        System.out.println(driver.rateLimit(3));
        System.out.println(driver.rateLimit(2));
        System.out.println(driver.rateLimit(2));
    }
}