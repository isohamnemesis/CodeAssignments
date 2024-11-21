import java.util.List;

public class Main {
    public static void main(String[] args) {

        FileManagement fileManagement = FileManagement.getInstance();

        fileManagement.addFile("file1.txt", 100);
        fileManagement.addFile("file2.txt", 200, "collection1");
        fileManagement.addFile("file3.txt", 200, "collection1");
        fileManagement.addFile("file4.txt", 300, "collection2");
        fileManagement.addFile("file5.txt", 100, "collection3");
        fileManagement.addDirectory("collection2", "collection1");
        fileManagement.addFile("file1.txt", 100, "collection1");
        fileManagement.addFile("file6.txt", 1000, "collection3");
        fileManagement.addDirectory("collection1", "collection3");

        List<Directory> rank = fileManagement.rankDirectories(3);
        for(var dir:rank){
            System.out.println(dir.getDirectoryName()+" : "+dir.getDirectorySize());
        }

        System.out.println(fileManagement.getTotalFileSize());
    }
}