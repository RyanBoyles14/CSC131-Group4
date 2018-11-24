package parsetree;

import parsetree.antlr4.Java8BaseListener;
import parsetree.antlr4.Java8Parser;

import java.util.ArrayList;

public class JavaListener extends Java8BaseListener implements Listener {
    //https://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree

    private ArrayList<Class> classes;
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

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}
