import java.util.*;

public class Coupling {
	ArrayList<FileObject> fileList;
	ArrayList<String> classNames;
	//constructor sets the files to be used for the metric
	public Coupling(/*filearray*/) {
		this.fileList = filearray;
	}
	//This method sets all of the classnames for the project
	public ArrayList<String> setClasses(){
		BufferedReader buffRead;
		buffRead = new BufferedReader(new FileReader(/*file variable*/));
		StreamTokenizer st = new StreamTokenizer(buffRead);
		setTokenizerSyntaxTable(st);
		
	}
	//sets the options for the stream tokenizer
	public void setTokenizerSyntaxTable(StreamTokenizer tokenizer) {
		//set the whitespace/delimiters
		tokenizer.whitespaceChars(33, 33);
		tokenizer.whitespaceChars(35, 47);
		tokenizer.whitespaceChars(58, 63);
		tokenizer.whitespaceChars(91, 94);
		tokenizer.whitespaceChars(123, 126);
		
		//set word values
		tokenizer.wordChars(48, 57);//sets 0-9 ascii as word characters
		tokenizer.wordChars(95, 95);//sets '_' ascii as word character
		
		//set comment values
		tokenizer.slashStarComments(true);
		tokenizer.slashSlashComments(true);
		
		//set quote delimiter
		tokenizer.quoteChar(34);
	}
}
