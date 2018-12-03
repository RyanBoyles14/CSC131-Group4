package parsetree;

import java.io.File;
import java.util.ArrayList;

public class Class{
    private String name;
    private File file;
    private ArrayList<Class> parent = new ArrayList<>();
    private ArrayList<String> parentNames = new ArrayList<>();
    private int depth = 1;
    private boolean foundDepth = false;

    public Class(String name){
        this.name = name;
    }

    public void setFile(File file){this.file = file;}

    public void setParent(Class parent) {this.parent.add(parent);}

    public void setParent(String parent){
        String[] parts = parent.split("\\.");
        // In the case where the parent name contains its package directory, get just the parent name
        if(parts.length > 0)
            parent = parts[parts.length - 1];
        this.parentNames.add(parent);
    }

    // Recursively run through a class' parents until you find the root
    // Add 1 for each recursive iteration
    int findDepth(){
        if(foundDepth)
            return depth;

        int max = 0;

        // If a class has multiple parents, find the parent with the largest depth
        // Happens when classes have either multiple derived classes or both extends and implements a super class)
        while(!parent.isEmpty()){
            Class p = parent.remove(0);

            if(p == null)
                return 1;

            // After finding the parent's depth, save it to the parent
            int depth = p.findDepth();

            if(depth > max)
                max = depth;

        }

        foundDepth = true;
        depth = 1 + max;

        return depth;
    }

    File getFile(){return file;}

    ArrayList<String> getParent(){
        return parentNames;
    }

    String getName(){
        return name;
    }

    int getDepth(){
        return depth;
    }
}
