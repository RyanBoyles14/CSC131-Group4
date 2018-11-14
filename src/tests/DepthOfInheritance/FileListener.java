import java.util.Stack;

public class FileListener extends Java8BaseListener{
    //https://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree

    public FileListener() {

    }

    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        String className = ctx.normalClassDeclaration().getText();
        displayClassName(className);
    }

    public void exitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {

    }

    private void displayClassName(String className) {
        System.out.println(className);
    }
}
