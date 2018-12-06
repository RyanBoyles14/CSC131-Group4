package parsetree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String args[]) throws IOException {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("src/test/java/parsetree/JavaListener.java"));
        files.add(new File("src/test/java/parsetree/Listener.java"));
        files.add(new File("src/test/java/testcases/helloworld.java"));
        files.add(new File("src/test/java/testcases/packagetest/helloworld.java"));
        files.add(new File("src/test/java/testcases/packagetest/nested/helloworld.java"));
        files.add(new File("src/test/java/testcases/helloworld.cpp"));
        DepthOfInheritance doi = new DepthOfInheritance(files);
    }
}


