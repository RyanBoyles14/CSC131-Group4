/*
    Group 4 - CSC 131
    Developer: Ryan Boyles

    When passing in a group a files, use ANTLR4 to build a Parse Tree for the all project files
    For each class, recursively get the class' parent to find the total depth of inheritance
    Depending on the file extension (in this case, Java or C++), use the necessary ANTLR
    grammar to parse the file.
    All documentation and files came from antlr.org
    Grammar: https://github.com/antlr/grammars-v4

*/

package CSC131Fall2018.Group4;

import java.util.ArrayList;

public class DepthOfInheritance
{
    public class Metrics implements IMetrics
    {
        public ArrayList<Class> classes = new ArrayList<>();
    }

    private IMetrics metrics;

    DepthOfInheritance()
    {
        this.metrics = new DepthOfInheritance.Metrics();
    }

    public IMetrics getMetrics()
    {
        return this.metrics;
    }
}