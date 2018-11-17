import src.main.antlr4.CPP14BaseListener;
import src.main.antlr4.CPP14Parser;

import java.util.ArrayList;

public class CPPListener extends CPP14BaseListener {

    private ArrayList<Class> classes = new ArrayList<>();

    public void enterClasshead(CPP14Parser.ClassheadContext ctx) {
        CPP14Parser.ClassheadnameContext name = ctx.classheadname();
        String child = name.classname().getText();

        Class childClass = new Class(child);
        Class parentClass = null;
        classes.add(childClass);

        if(ctx.baseclause() != null) {
            CPP14Parser.BaseclauseContext parent = ctx.baseclause();
            //TODO: implement finding the inherited class with the most depth
            parentClass = new Class(parent.basespecifierlist().getText());
        }

        for(Class cl: classes){
            if(parentClass == null)
                break;

            // If the superclass is in the ArrayList, set the current parent to the superclass in the list
            if(parentClass.getName().equals(cl.getName())){
                parentClass = cl;
                break;
            }
        }

        childClass.setParent(parentClass);
    }

    // Run through all the classes, finding their depths and displaying them
    void displayDepth(){
        for(Class c: classes){
            c.setDepth();
            System.out.println(c.getName() + ": " + c.getDepth());
        }
    }
}
