package CSC131Fall2018.Group4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DepthOfInheritanceMetricsCalculator extends AbstractMetricsCalculator
{
	private IMetrics metrics;

	public DepthOfInheritanceMetricsCalculator(Repository r)
			throws Exception
	{
		super(r);
	}

	@Override
	protected void newCalculation(File f) throws Exception
	{
		throw new UnsupportedOperationException();
	}

	@Override
	protected void newCalculation(Repository r) throws Exception
	{
		this.metrics = r.getDepthOfInheritanceMetrics();

		ArrayList<File> files = r.getList();

		for(File f: files){
			String[] parts = f.toString().split("\\.");
			String extension = parts[parts.length - 1];
			try {
				if (extension.equals("java") || extension.equals("cpp") || extension.equals("hpp")
						|| extension.equals("cxx") || extension.equals("hxx") || extension.equals("h")) {
					CharStream input = CharStreams.fromFileName(f.toString());
					parse(f, extension, input);
				}
			} catch(IOException e){
				System.out.println("Error parsing Depth of Inheritance for " + f.toString());
			}
		}

		depth();
	}

	private void parse(File f, String extension, CharStream input)
			throws IOException
	{
		Listener extractor;

		switch (extension) {
			case "java": {
				Lexer lexer = new com.CSC131Fall2018.Group4.antlr4.Java8Lexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				extractor = new JavaListener();
				com.CSC131Fall2018.Group4.antlr4.Java8Parser jParser = new com.CSC131Fall2018.Group4.antlr4.Java8Parser(tokens);
				ParseTreeWalker.DEFAULT.walk(extractor, jParser.compilationUnit());
				break;
			}
			case "cpp":
			case "cxx":
			case "hpp":
			case "hxx":
			case "h": {
				Lexer lexer = new com.CSC131Fall2018.Group4.antlr4.CPP14Lexer(input);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				extractor = new CPPListener();
				com.CSC131Fall2018.Group4.antlr4.CPP14Parser cppParser = new com.CSC131Fall2018.Group4.antlr4.CPP14Parser(tokens);
				ParseTreeWalker.DEFAULT.walk(extractor, cppParser.translationunit());
				break;
			}
			default:
				throw new IOException();
		}

		extractor.setFile(f);
		((DepthOfInheritance.Metrics) this.metrics).classes.addAll(extractor.getClasses());
	}

	// Run through all the classes, finding their depths and displaying them
	private void depth(){
		updateParent();

		String file = "";
		for (Class child : ((DepthOfInheritance.Metrics) this.metrics).classes) {
			if(!file.equals(child.getFile().toString())) {
				file = child.getFile().toString();
				System.out.println(file);
			}
			child.findDepth();
		}
	}

	/*
		Necessary for distinguishing external and local extensions
		Connect Class objects with any other Class object it inherits from
	 */
	private void updateParent(){
		for(Class child: ((DepthOfInheritance.Metrics) this.metrics).classes) {

			if(child.getParent().isEmpty())
				continue;

			ArrayList<String> parent = child.getParent();
			// Check if a child's parent already has a Class
			// Used for linking depth among classes
			for (Class cl : ((DepthOfInheritance.Metrics) this.metrics).classes) {
				if (parent.contains(cl.getName()) && fileCheck(cl, child)) {
					child.setParent(cl);
					parent.remove(cl.getName());
				}
			}

			// If a class still has parents in its ArrayList, create new parents to show inheritance
			for (String p : parent)
				child.setParent(new Class(p));
		}
	}

	//Ensure the child can access the potential parent by making sure they're within the same directory
	private boolean fileCheck(Class parent, Class child){
		String childFile = child.getFile().toString();
		String parentFile = parent.getFile().toString();

		if(parent == child)
			return false;

		String[] childParts = childFile.split("\\\\");
		String[] parentParts = parentFile.split("\\\\");

		if(childParts.length > parentParts.length)
			return false;

		//check that the file path for child and parent match
		for(int i = 0; i < childParts.length - 1; i++){
			if(!childParts[i].equals(parentParts[i]))
				return false;
		}

		return true;
	}
}
