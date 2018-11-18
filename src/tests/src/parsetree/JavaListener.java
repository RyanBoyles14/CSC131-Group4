package parsetree;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import src.main.antlr4.Java8BaseListener;
import src.main.antlr4.Java8Parser;

import java.util.ArrayList;

public class JavaListener extends Java8BaseListener implements ParseTreeListener {
    //https://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree

    private ArrayList<Class> classes = new ArrayList<>();

    /*
    Given a normal class declaration, find the class name and any superclasses
    Add the child class to an ArrayList and set its parent to it superclass
    After walking through all classes, return.
    */
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        String child = ctx.Identifier().getText();
        Class c = new Class(child);
        Class p = null;
        classes.add(c);

        if(ctx.superclass() != null) {
            Java8Parser.SuperclassContext parent = ctx.superclass();
            p = new Class(parent.classType().getText());

        }else if(ctx.superinterfaces() != null){
            Java8Parser.SuperinterfacesContext parent = ctx.superinterfaces();
            p = new Class(parent.interfaceTypeList().getText());
        }

        for(Class cl: classes){
            if(p == null)
                break;

            // If the superclass is in the ArrayList, set the current parent to the superclass in the list
            if(p.getName().equals(cl.getName())){
                p = cl;
                break;
            }
        }

        c.setParent(p);
    }

    // Run through all the classes, finding their depths and displaying them
    void displayDepth(){
        for(Class c: classes){
            c.setDepth();
            System.out.println(c.getName() + ": " + c.getDepth());
        }
    }
}