package parsetree;

import java.io.File;
import java.util.ArrayList;

public class Class{

    private File file;
    private String name;
    private ArrayList<Class> parent = new ArrayList<>();
    private int depth = 1;

    public Class(String name){
        this.name = name;
    }

    void setFile(File file){
        this.file = file;
    }

    public void setParent(Class parent){
        this.parent.add(parent);
    }

    void setDepth(){
        depth = findDepth();
    }

    // Recursively run through a class' parents until you find the root
    // Add 1 for each recursive iteration
    private int findDepth(){
        int max = 0;

        // If a class has multiple parents, find the parent with the largest depth
        // Happens when classes have either multiple derived classes or both extends and implements a super class)
        while(!parent.isEmpty()){
            Class p = parent.remove(0);

            if(p == null)
                return 1;

            int depth = p.findDepth();
            if(depth > max)
                max = depth;

        }

        return 1 + max;
    }

    public File getFile(){
        return file;
    }

    String getName(){
        return name;
    }

    int getDepth(){
        return depth;
    }
}
