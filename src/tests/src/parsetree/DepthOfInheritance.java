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
import src.main.antlr4.CPP14Lexer;
import src.main.antlr4.CPP14Parser;
import src.main.antlr4.Java8Lexer;
import src.main.antlr4.Java8Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DepthOfInheritance {

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
    }

    void javaParse(File f) throws IOException {
        CharStream input = CharStreams.fromFileName(f.toString());
        Java8Lexer jLexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(jLexer);
        Java8Parser jParser = new Java8Parser(tokens);
        Java8Parser.CompilationUnitContext jTree = jParser.compilationUnit(); // parse a compilationUnit

        JavaListener jExtractor = new JavaListener();
        ParseTreeWalker.DEFAULT.walk(jExtractor, jTree); // initiate walk of tree with listener in use of default walker

        jExtractor.displayDepth();
    }

    void cppParse(File f) throws IOException {
        CharStream input = CharStreams.fromFileName(f.toString());
        CPP14Lexer cLexer = new CPP14Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(cLexer);
        CPP14Parser cParser = new CPP14Parser(tokens);
        CPP14Parser.TranslationunitContext cTree = cParser.translationunit();

        CPPListener cExtractor = new CPPListener();
        ParseTreeWalker.DEFAULT.walk(cExtractor, cTree); // initiate walk of tree with listener in use of default walker

        cExtractor.displayDepth();
    }
}