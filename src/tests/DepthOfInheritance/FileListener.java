import org.antlr.v4.runtime.ParserRuleContext;
import src.main.antlr4.Java8BaseListener;
import src.main.antlr4.Java8Parser;

public class FileListener extends Java8BaseListener {
    //https://stackoverflow.com/questions/15050137/once-grammar-is-complete-whats-the-best-way-to-walk-an-antlr-v4-tree

    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        String child = ctx.Identifier().getText();
        if(ctx.superclass() != null) {
            Java8Parser.SuperclassContext parent = ctx.superclass();
            displayClassName(parent.classType().getText(), child);
        }else if(ctx.superinterfaces() != null){
            Java8Parser.SuperinterfacesContext parent = ctx.superinterfaces();
            displayClassName(parent.interfaceTypeList().getText(), child);
        } else {
            displayClassName("Object", child);
        }
    }

    private void displayClassName(String parent, String child) {
        System.out.println(parent + "->" + child);
    }
}
