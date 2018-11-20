import parsetree.DepthOfInheritance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String args[]) throws IOException {
        ArrayList<File> files = new ArrayList<>();
        files.add(new File("src/parsetree/Listener.java"));
        files.add(new File("src/parsetree/JavaListener.java"));
        files.add(new File("src/parsetree/CPPListener.java"));
        files.add(new File("src/helloworld.cpp"));
        DepthOfInheritance doi = new DepthOfInheritance(files);
    }
}
