package parsetree;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import parsetree.antlr4.CPP14BaseListener;
import parsetree.antlr4.CPP14Parser;

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
            setParent(new Class(list.basespecifier().getText()));
            while(list.basespecifierlist() != null) {
                setParent(new Class(list.basespecifier().getText()));
                list = list.basespecifierlist();
            }
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

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}

