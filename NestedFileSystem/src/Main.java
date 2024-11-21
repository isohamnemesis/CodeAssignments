import java.util.List;

public class Main {
    public static void main(String[] args) {

        Driver driver = new Driver();
        //driver.addFile("file1.txt", 100);
        driver.addFile("file2.txt", 200, "collection1");
        driver.addFile("file3.txt", 200, "collection1");
        driver.addFile("file4.txt", 300, "collection2");
        //driver.addFile("file5.txt", 100);
        driver.addFile("file6.txt", 300, "collection2");
        driver.addFile("file7.txt", 900, "collection3");
        driver.addFile("file8.txt", 600, "collection4");
        driver.addFile("file9.txt", 700, "collection5");
        driver.addDirectory("collection1", "collection2");
        driver.addDirectory("collection3", "collection4");

        List<Directory> result = driver.topKCollection(3);
        for(var res:result) {
            System.out.println(res.getName() + " " + res.getSize());
        }
    }
}