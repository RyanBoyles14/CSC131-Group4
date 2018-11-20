package parsetree;

import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.util.ArrayList;

public interface Listener extends ParseTreeListener {

    public ArrayList<Class> getClasses();

    void setClasses(ArrayList<Class> classes);
}
