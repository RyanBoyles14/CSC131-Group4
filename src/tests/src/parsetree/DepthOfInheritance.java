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

        JavaListener jExtractor = new JavaListener();
        //TODO: give the Class list to the listener to check for existing classes
        //TODO: give the File to the listener to prevent classes with the same name from causing conflicts
        //TODO: be able to add a defined parent class to the child.
        ParseTreeWalker.DEFAULT.walk(jExtractor, jTree); // initiate walk of tree with listener in use of default walker

        jClasses.addAll(jExtractor.getClasses());
    }

    void cppParse(File f) throws IOException {
        CharStream input = CharStreams.fromFileName(f.toString());
        CPP14Lexer cLexer = new CPP14Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(cLexer);
        CPP14Parser cParser = new CPP14Parser(tokens);
        CPP14Parser.TranslationunitContext cTree = cParser.translationunit();

        CPPListener cppExtractor = new CPPListener();
        ParseTreeWalker.DEFAULT.walk(cppExtractor, cTree); // initiate walk of tree with listener in use of default walker

        ArrayList<Class> classes = cppExtractor.getClasses();

        cppClasses.addAll(classes);
    }

    // Run through all the classes, finding their depths and displaying them
    void displayDepth(){
        for (Class c : jClasses) {
            c.setDepth();
            System.out.println(c.getName() + ": " + c.getDepth());
        }

        for (Class c : cppClasses) {
            c.setDepth();
            System.out.println(c.getName() + ": " + c.getDepth());
        }

    }
}