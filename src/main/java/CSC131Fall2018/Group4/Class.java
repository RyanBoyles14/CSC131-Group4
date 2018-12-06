/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    The object Class represents any class or interface.
    When a Class is made, it is given a list of classes and
    interfaces it inherits.

    After all files are parsed, link all Classes with its parent
    and find their depth.

*/

package CSC131Fall2018.Group4;

import java.io.File;
import java.util.ArrayList;

public class Class{
    private String name;
    private File file;
    private ArrayList<Class> inheritance = new ArrayList<>(); //the beginning of the list represents the lowest point of inheritance
    private ArrayList<Class> parent = new ArrayList<>();
    // parentNames allow checking a Class' inheritance with an existing Class object
    // Used in DepthOfInheritance updateParent()
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
        Class deepestInheritance = null;

        // If a class has multiple parents, find the parent with the largest depth
        // Happens when classes have either multiple derived classes or both extends and implements a super class)
        while(!parent.isEmpty()){
            Class p = parent.remove(0);

            if(p == null)
                return 1;

            // After finding the parent's depth, save it to the parent
            int depth = p.findDepth();

            if(depth > max) {
                deepestInheritance = p;
                max = depth;
            }
        }

        foundDepth = true;

        if(deepestInheritance != null) {
            inheritance.add(deepestInheritance);
            inheritance.addAll(deepestInheritance.inheritance);
        }
        depth = max + 1;

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

    ArrayList<Class> getInheritance(){ return inheritance;}
}
