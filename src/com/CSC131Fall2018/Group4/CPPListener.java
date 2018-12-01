/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    When passed in a parse tree for a C++ file, look for all instances
    of classes. Create an object Class for each one and add it to a list.
    Find a Class' inheritance and set the Class' parent to what it
    inherits. Repeat for all classes in the file.

    CPP14 Grammar: https://github.com/antlr/grammars-v4/blob/master/cpp

*/

package com.CSC131Fall2018.Group4;

import com.CSC131Fall2018.Group4.antlr4.CPP14BaseListener;
import com.CSC131Fall2018.Group4.antlr4.CPP14Parser;

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

    public ArrayList<Class> getClasses(){
        return classes;
    }

    public void setFile(File file){
        for(Class c: classes){
            c.setFile(file);
        }
    }
}

