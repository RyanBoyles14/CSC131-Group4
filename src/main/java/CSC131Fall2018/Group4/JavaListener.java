/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    When passed in a parse tree for a Java8 file, look for all instances
    of classes and interfaces. Create an object Class for each one and add it to a list.
    Find a Class' inheritance by finding any class extensions or
    inheritance implementation. Set the Class' parent to what it
    inherits. Repeat for all classes in the file.

    Java8 Grammar: https://github.com/antlr/grammars-v4/tree/master/java8

*/

package CSC131Fall2018.Group4;

import com.CSC131Fall2018.Group4.antlr4.Java8BaseListener;
import com.CSC131Fall2018.Group4.antlr4.Java8Parser;

import java.io.File;
import java.util.ArrayList;

public class JavaListener extends Java8BaseListener implements Listener {
    //https://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree

    private ArrayList<Class> classes = new ArrayList<>();
    private Class aClass;

    /*
    Given a either a normal class or interface declaration, find the identifer and any superclasses or superinterfaces
    Add the child class to an ArrayList and add any inherited classes/interfaces to its list of parents
    After walking through all normal classes and interfaces, return.
    */


    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx){
        aClass = new Class(ctx.Identifier().getText());
        classes.add(aClass);

        if(ctx.superclass() != null) {
            String superClass = ctx.superclass().classType().getText();
            aClass.setParent(superClass);
        }
    }

    public void enterNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx){
        aClass = new Class(ctx.Identifier().getText());
        classes.add(aClass);
    }

    public void enterInterfaceTypeList(Java8Parser.InterfaceTypeListContext ctx){
        if(ctx.interfaceType() == null)
            return;

        int i = 0;
        while(ctx.interfaceType(i) != null){
            Java8Parser.InterfaceTypeContext iType = ctx.interfaceType(i);
            aClass.setParent(iType.getText());
            i++;
        }
    }

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public void setFile(File file){
        for(Class c: classes){
            c.setFile(file);
        }
    }
}
