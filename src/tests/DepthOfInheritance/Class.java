public class Class{

    private String name;
    private Class parent;
    private int depth = 1;

    public Class(String name){
        this.name = name;
    }

    public void setParent(Class parent){
        this.parent = parent;
    }

    public void setDepth(){
        depth = findDepth();
    }

    // Recursively run through a class' parents until you find the root
    // Add 1 for each recursive iteration
    public int findDepth(){
        if(parent == null)
            return 1;
        return 1 + parent.findDepth();
    }

    public String getName(){
        return name;
    }

    public int getDepth(){
        return depth;
    }
}
