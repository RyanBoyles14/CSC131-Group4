/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    Interface for JavaListener and CPPListener

    Java8 Grammar: https://github.com/antlr/grammars-v4/tree/master/java8

*/

package com.CSC131Fall2018.Group4;

import org.antlr.v4.runtime.tree.ParseTreeListener;

import java.io.File;
import java.util.ArrayList;

public interface Listener extends ParseTreeListener {

    ArrayList<Class> getClasses();

    void setFile(File f);
}
