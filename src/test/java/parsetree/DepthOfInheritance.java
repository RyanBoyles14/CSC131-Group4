/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    When passing in a group a files, use ANTLR4 to build a Parse Tree for the all project files
    For each class, recursively get the class' parent to find the total depth of inheritance
    Depending on the file extension (in this case, Java or C++), use the necessary ANTLR
    grammar to parse the file.
    All documentation and files came from antlr.org
    Grammar: https://github.com/antlr/grammars-v4

    Steps:
    1) Grab the file(s). Determine if they are Java or C++
    2) Create a ParseTree using ANTLR.
    3) Go through each class and compute its depth of inheritance
       a) Grab a class and check for extensions or implementation
       b) Create nodes showing the relationship between child and parent
       c) Find each node's depth by recursively finding the parent
       d) If a class does not extend anything, it's depth is 1, as each class inherits from Object
    4) Gather results to return

*/

package parsetree;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parsetree.antlr4.CPP14Lexer;
import parsetree.antlr4.CPP14Parser;
import parsetree.antlr4.Java8Lexer;
import parsetree.antlr4.Java8Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DepthOfInheritance {

    private ArrayList<Class> classes = new ArrayList<>();

    public DepthOfInheritance(ArrayList<File> files){
        for(File f: files){
            String[] parts = f.toString().split("\\.");
            String extension = parts[parts.length - 1];
            try {
                if (extension.equals("java") || extension.equals("cpp") || extension.equals("hpp")) {
                    CharStream input = CharStreams.fromFileName(f.toString());
                    parse(f, extension, input);
                }
            } catch(IOException e){
                System.out.println("Error parsing Depth of Inheritance for " + f.toString());
            }
        }

        displayDepth();
    }

    void parse(File f, String extension, CharStream input) throws IOException{
        Listener extractor;

        switch (extension) {
            case "java": {
                Lexer lexer = new Java8Lexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                extractor = new JavaListener();
                Java8Parser jParser = new Java8Parser(tokens);
                ParseTreeWalker.DEFAULT.walk(extractor, jParser.compilationUnit());
                break;
            }
            case "cpp":
            case "hpp": {
                Lexer lexer = new CPP14Lexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                extractor = new CPPListener();
                CPP14Parser cppParser = new CPP14Parser(tokens);
                ParseTreeWalker.DEFAULT.walk(extractor, cppParser.translationunit());
                break;
            }
            default:
                throw new IOException();
        }

        extractor.setFile(f);
        classes.addAll(extractor.getClasses());
    }

    // Run through all the classes, finding their depths and displaying them
    private void displayDepth(){
        updateParent();

        String file = "";
        for (Class child : classes) {
            if(!file.equals(child.getFile().toString())) {
                file = child.getFile().toString();
                System.out.println(file);
            }
            child.findDepth();

            System.out.println("\t" + child.getInheritance().toString());
        }
    }

    private void updateParent(){
        for(Class child: classes) {

            if(child.getParent().isEmpty())
                continue;

            ArrayList<String> parent = child.getParent();

            // Check if a child's parent already has a Class
            // Used for linking depth among classes
            for (Class cl : classes) {
                if (parent.contains(cl.getName()) && fileCheck(cl, child)) {
                    child.setParent(cl);
                    parent.remove(cl.getName());
                }
            }

            // If a class still has parents in its ArrayList, create new parents to show inheritance
            for (String p : parent)
                child.setParent(new Class(p));
        }
    }

    //Ensure the child can access the potential parent by making sure they're within the same directory
    boolean fileCheck(Class parent, Class child){
        String childFile = child.getFile().toString();
        String parentFile = parent.getFile().toString();

        if(parent == child)
            return false;

        String[] childParts = childFile.split("\\\\");
        String[] parentParts = parentFile.split("\\\\");

        if(childParts.length > parentParts.length)
            return false;

        //check that the file path for child and parent match
        for(int i = 0; i < childParts.length - 1; i++){
            if(!childParts[i].equals(parentParts[i]))
                return false;
        }

        return true;
    }
}