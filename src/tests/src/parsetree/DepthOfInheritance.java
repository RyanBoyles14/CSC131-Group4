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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import parsetree.antlr4.CPP14Lexer;
import parsetree.antlr4.CPP14Parser;
import parsetree.antlr4.Java8Lexer;
import parsetree.antlr4.Java8Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DepthOfInheritance {

    private ArrayList<Class> jClasses = new ArrayList<>();
    private ArrayList<Class> cppClasses = new ArrayList<>();

    public DepthOfInheritance(ArrayList<File> files){
        for(File f: files){
            String[] parts = f.toString().split("\\.");
            try {
                switch (parts[parts.length - 1]) {
                    case "java":
                        javaParse(f);
                        continue;
                    case "cpp":
                    case "hpp":
                        cppParse(f);
                    default:
                        continue;
                }
            } catch(IOException e){
                System.out.println("Error parsing " + f.toString());
            }
        }

        displayDepth();
    }

    void javaParse(File f) throws IOException {
        CharStream input = CharStreams.fromFileName(f.toString());
        Java8Lexer jLexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(jLexer);
        Java8Parser jParser = new Java8Parser(tokens);
        Java8Parser.CompilationUnitContext jTree = jParser.compilationUnit(); // parse a compilationUnit

        Listener jExtractor = new JavaListener();
        ParseTreeWalker.DEFAULT.walk(jExtractor, jTree); // initiate walk of tree with listener in use of default walker

        jExtractor.setFile(f);
        jClasses.addAll(jExtractor.getClasses());
    }

    void cppParse(File f) throws IOException {
        CharStream input = CharStreams.fromFileName(f.toString());
        CPP14Lexer cLexer = new CPP14Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(cLexer);
        CPP14Parser cParser = new CPP14Parser(tokens);
        CPP14Parser.TranslationunitContext cTree = cParser.translationunit();

        Listener cppExtractor = new CPPListener();
        ParseTreeWalker.DEFAULT.walk(cppExtractor, cTree); // initiate walk of tree with listener in use of default walker

        cppExtractor.setFile(f);
        cppClasses.addAll(cppExtractor.getClasses());
    }

    // Run through all the classes, finding their depths and displaying them
    private void displayDepth(){
        updateParent();

        String file = "";
        for (Class child : jClasses) {
            if(!file.equals(child.getFile().toString())) {
                file = child.getFile().toString();
                System.out.println(file);
            }
            child.findDepth();
            System.out.println("\t" + child.getName() + ": " + child.getDepth());
        }

        for (Class child : cppClasses) {
            child.findDepth();
            System.out.println(child.getName() + ": " + child.getDepth());
        }
    }

    private void updateParent(){
        for(Class child: jClasses) {

            if(child.getParent().isEmpty())
                continue;

            ArrayList<String> parent = child.getParent();
            for (Class cl : jClasses) {
                if (parent.contains(cl.getName()) && fileCheck(cl, child)) {
                    child.setParent(cl);
                    parent.remove(cl.getName());
                }
            }

            for (String p : parent)
                child.setParent(new Class(p));
        }

        for (Class child : cppClasses) {
            if(child.getParent().isEmpty())
                continue;

            ArrayList<String> parent = child.getParent();
            for (Class cl : cppClasses) {
                if (parent.contains(cl.getName())) {
                    child.setParent(cl);
                    parent.remove(cl.getName());
                }
            }

            for (String p : parent)
                child.setParent(new Class(p));
        }
    }

    boolean fileCheck(Class parent, Class child){
        String childFile = child.getFile().toString();
        String parentFile = parent.getFile().toString();

        if(childFile.equals(parentFile))
            return false;

        String[] childParts = childFile.split("\\\\");
        String[] parentParts = parentFile.split("\\\\");

        if(childParts.length > parentParts.length)
            return false;

        for(int i = 0; i < childParts.length - 1; i++){
            if(!childParts[i].equals(parentParts[i]))
                return false;
        }

        return true;
    }
}