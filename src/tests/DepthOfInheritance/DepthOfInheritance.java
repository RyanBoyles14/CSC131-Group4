/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    When passing in a group a files, use ANTLR to build a Parse Tree for the all project files
    For each class, recursively get the class' parent to find the total depth of inheritance
    Depending on the file extension (in this case, Java or C++), use the necessary ANTLR
    grammar to parse the file.

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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import src.main.antlr4.CPP14Lexer;
import src.main.antlr4.CPP14Parser;
import src.main.antlr4.Java8Lexer;
import src.main.antlr4.Java8Parser;

import java.io.IOException;

public class DepthOfInheritance {
    public static void main(String args[]) throws IOException {
        // https://search.proquest.com/openview/788320e2eb5cf34b858a450075ef6511/1.pdf?pq-origsite=gscholar&cbl=18750&diss=y
        // A project that used ANTLR for various metrics. Plan on switching over

        // https://stackoverflow.com/questions/2206065/java-parse-java-source-code-extract-methods
        // JavaParser seems easy to work with, but can't work with C++. Maybe try ANTLR?

        DepthOfInheritance doi = new DepthOfInheritance();
    }

    public DepthOfInheritance() throws IOException {
        // TODO grab all files and sort based on extension
        CharStream input = CharStreams.fromFileName("DepthOfInheritance.java");
        Java8Lexer jLexer = new Java8Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(jLexer);
        Java8Parser jParser = new Java8Parser(tokens);
        Java8Parser.CompilationUnitContext jTree = jParser.compilationUnit(); // parse a compilationUnit

        JavaListener jExtractor = new JavaListener();
        ParseTreeWalker.DEFAULT.walk(jExtractor, jTree); // initiate walk of tree with listener in use of default walker

        jExtractor.displayDepth();

        input = CharStreams.fromFileName("helloworld.cpp");
        CPP14Lexer cLexer = new CPP14Lexer(input);
        tokens = new CommonTokenStream(cLexer);
        CPP14Parser cParser = new CPP14Parser(tokens);
        CPP14Parser.TranslationunitContext cTree = cParser.translationunit();

        CPPListener cExtractor = new CPPListener();
        ParseTreeWalker.DEFAULT.walk(cExtractor, cTree); // initiate walk of tree with listener in use of default walker

        cExtractor.displayDepth();
    }
}

// ---------- Test classes --------------
class test extends DepthOfInheritance{

    public test() throws IOException{

    }
}

class Exception1 extends IOException{

}

class Exception2 extends Exception1{

}