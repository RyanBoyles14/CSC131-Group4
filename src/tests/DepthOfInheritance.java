import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DepthOfInheritance {
    public static void main(String args[]){
        // https://stackoverflow.com/questions/2206065/java-parse-java-source-code-extract-methods
        // JavaParser seems easy to work with, but can't work with C++. Maybe try ANTLR?
        InputStream in = null;
        CompilationUnit cu = null;
        try
        {
            in = new FileInputStream("DepthOfInheritance.java");
            cu = JavaParser.parse(in);
        }
        catch(FileNotFoundException x)
        {
            // handle parse exceptions here.
        }
        finally
        {
            try {
                in.close();
            }
            catch(IOException e){

            }
        }

        //MethodVisitor visitor = new MethodVisitor();
        //visitor.visit(cu, null);
    }
}

/*
class MethodVisitor extends VoidVisitorAdapter
{
    // https://github.com/mauricioaniche/ck/blob/master/src/main/java/com/github/mauricioaniche/ck/metric/DIT.java
    // https://github.com/javaparser/javaparser/wiki/Manual
    int dit = 1;

    public void visit(TypeDeclaration node, Object arg)
    {
        ITypeBinding binding = node.resolveBinding();
        if(binding!=null) calculate(binding);

        return super.visit(node);
    }

    private void calculate(ITypeBinding binding) {
        ITypeBinding father = binding.getSuperclass();
        if (father != null) {
            String fatherName = father.getQualifiedName();
            if (fatherName.endsWith("Object")) return;
            dit++;

            calculate(father);
        }

    }
}*/
