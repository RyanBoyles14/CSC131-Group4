package parsetree;

import org.antlr.v4.runtime.tree.ParseTreeListener;
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
        Class superClass = null;
        aClass = new Class(ctx.Identifier().getText());
        classes.add(aClass);

        if(ctx.superclass() != null) {
            String superC = ctx.superclass().classType().getText();
            superClass = new Class(superC);
        }

        if(superClass != null)
            setParent(superClass);

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
            setParent(new Class(iType.getText()));
            i++;
        }
    }

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    void setParent(Class parent){
        boolean inList = false;

        for(Class cl: classes){
            if(parent.getName().equals(cl.getName())){
                aClass.setParent(cl);
                inList = true;
            }
        }

        if(!inList)
            aClass.setParent(parent);
    }
}
