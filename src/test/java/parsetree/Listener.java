package parsetree;

import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.io.File;
import java.util.ArrayList;

public interface Listener extends ParseTreeListener {

    ArrayList<Class> getClasses();

    void setFile(File f);
}
