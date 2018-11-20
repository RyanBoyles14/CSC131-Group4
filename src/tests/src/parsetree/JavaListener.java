package parsetree;

import org.antlr.v4.runtime.RuleContext;
import parsetree.antlr4.Java8BaseListener;
import parsetree.antlr4.Java8Parser;

import java.util.ArrayList;

public class JavaListener extends Java8BaseListener implements Listener{
    //https://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree

    private ArrayList<Class> classes = new ArrayList<>();
    private Class aClass;

    /*
    Given a either a noraml class or interface declaration, find the identifer and any superclasses or superinterfaces
    Add the child class to an ArrayList and add any inherited classes/interfaces to its list of parents
    After walking through all normal classes and interfaces, return.
    */
    public void enterTypeDeclaration(Java8Parser.TypeDeclarationContext ctx) {

        if(ctx.classDeclaration().normalClassDeclaration() != null)
            enterNormalClassDeclaration(ctx.classDeclaration().normalClassDeclaration());
        else if(ctx.interfaceDeclaration().normalInterfaceDeclaration() != null)
            enterNormalInterfaceDeclaration(ctx.interfaceDeclaration().normalInterfaceDeclaration());
        else
            return;
    }

    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx){
        Class superClass = null;
        aClass = new Class(ctx.Identifier().getText());
        classes.add(aClass);

        if(ctx.superclass() != null) {
            String superC = ctx.superclass().getText();
            superClass = new Class(superC);
        }

        if(superClass != null)
            setParent(superClass);

        if(ctx.superinterfaces() != null) {
            String interfaces = ctx.superinterfaces().interfaceTypeList().getText();
            setInterfaces(interfaces);
        }

    }

    public void enterNormalInterfaceDeclaration(Java8Parser.NormalInterfaceDeclarationContext ctx){
        aClass = new Class(ctx.Identifier().getText());
        classes.add(aClass);

        if(ctx.extendsInterfaces() != null) {
            String interfaces = ctx.extendsInterfaces().interfaceTypeList().getText();
            setInterfaces(interfaces);
        } else return;
    }

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public void setInterfaces(String interfaces){
        ArrayList<Class> superI = new ArrayList<>();
        if(!superI.isEmpty()){
            for(Class i: superI)
                setParent(i);
        }
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
