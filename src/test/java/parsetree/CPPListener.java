package parsetree;

import parsetree.antlr4.CPP14BaseListener;
import parsetree.antlr4.CPP14Parser;

import java.io.File;
import java.util.ArrayList;

public class CPPListener extends CPP14BaseListener implements Listener{

    private ArrayList<Class> classes = new ArrayList<>();
    private Class aClass;

    public void enterClasshead(CPP14Parser.ClassheadContext ctx) {
        CPP14Parser.ClassheadnameContext name = ctx.classheadname();
        String child = name.classname().getText();

        aClass = new Class(child);
        classes.add(aClass);

        if(ctx.baseclause() != null) {
            CPP14Parser.BasespecifierlistContext list = ctx.baseclause().basespecifierlist();
            aClass.setParent(list.basespecifier().getText());
            while(list.basespecifierlist() != null) {
                aClass.setParent(list.basespecifier().getText());
                list = list.basespecifierlist();
            }
        }
    }

    public void updateParent(Class child){
        if(child.getParent().isEmpty())
            return;

        ArrayList<String> parent = child.getParent();
        for(Class cl: classes){
            if(parent.contains(cl.getName()))
                child.setParent(cl);
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

