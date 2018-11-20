package parsetree;

import parsetree.antlr4.Java8BaseListener;
import parsetree.antlr4.Java8Parser;

import java.util.ArrayList;

public class JavaListener extends Java8BaseListener {
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
        Class superClass = null;
        Class superInterface = null;
        classes.add(c);

        if(ctx.superclass() != null) {
            Java8Parser.SuperclassContext parent = ctx.superclass();
            superClass = new Class(parent.classType().getText());
        }

        if(ctx.superinterfaces() != null){
            Java8Parser.SuperinterfacesContext parent = ctx.superinterfaces();
            superInterface = new Class(parent.interfaceTypeList().getText());
        }

        boolean classInList = false, interfaceInList= false;
        for(Class cl: classes){
            if(superClass == null && superInterface == null)
                break;

            // If the superclass or superinterface is already in the ArrayList, add the parent to the class' parent list
            // This ensures the child can find its parent's depth
            if(superClass.getName().equals(cl.getName())){
                c.setParent(cl);
                classInList = true;
            }

            if(superInterface.getName().equals(cl.getName())){
                c.setParent(cl);
                interfaceInList = true;
            }
        }

        if(!classInList)
            c.setParent(superClass);

        if(!classInList)
            c.setParent(superInterface);
    }

    ArrayList<Class> getClasses(){
        return classes;
    }

    // Run through all the classes, finding their depths and displaying them
    void displayDepth(){
        for(Class c: classes){
            c.setDepth();
            System.out.println(c.getName() + ": " + c.getDepth());
        }
    }
}