import java.io.StreamTokenizer;
import java.util.*;

import javax.tools.FileObject;

public class Coupling {
	ArrayList<FileObject> fileList;
	ArrayList<ClassStats> classes;
	//data structure to hold all of the coupling
	//information for a class
	class ClassStats{
		String classname;
		int index;
		ArrayList<InteractionEntry> interactionCoupling;
	}
	//data type to represent an Interaction Coupling entry
	class InteractionEntry{
		String targetClassname;
		String value;
		//Constructor to assign values to data type fields
		public Interaction Entry(String name, String value) {
			this.targetClassname = name;
			this.value = value;
		}
	}
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
	//get the interaction coupling for a class
	public void getInteractionCoupling(ClassStats A) {
		BufferedReader buffRead;
		buffRead = new BufferedReader(new FileReader(fileList.get(A.index)));
		StreamTokenizer st = new StreamTokenizer(buffRead);
		setTokenizerSyntaxTable(st);
		boolean classParsed = false;
		boolean inClass = false;
		boolean inSubClass = false;
		int bracketNumber = 0;
		int subClassBracketNumber = 0;
		String tempString;
		String methodName;
		//evaluates whether the class has finished parsing
		//or the end of file has been reached
		while (!classParsed && !st.nextToken() == TT_EOF) {
			//determines whether the class is the one we are
			//looking for
			if(st.sval.equals("class")){
				st.nextToken();
				if(st.sval.equals(A.classname)) {
					inClass = true;
					//parses until it finds an open bracket
					while(!st.nextToken() == TT_EOF && !st.sval.equals("{")) {
						
					}
				}
				else if(inclass) {
					inSubClass = true;
					while(!st.nextToken() == TT_EOF && !st.sval.equals("{")) {
						
					}
				}
			}
			if(inClass && !inSubClass && st.sval.equals("{")) bracketNumber++;
			if(inClass && !inSubClass && st.sval.equals("}")) bracketNumber--;
			if(inSubClass && st.sval.equals("{")) subClassBracketNumber++;
			if(inSubClass && st.sval.contentEquals("}")) subClassBracketNumber--;
			tempString = st.sval;
			st.nextToken();
			//if we parse a scope operator,
			//meaning we are at a method in our target class
			if(!inSubClass && inClass && st.sval.equals(".") && !tempString.equals("this")) {
				st.nextToken();
				methodName = st.sval;
				st.nextToken();
				if(st.sval.contains('(')) A.interactionCoupling.add(new InteractionEntry(tempString, methodName + "()")); 
			}
			
			if(bracketNumber == 0 && inClass) {
				classParsed = true;
				inClass = false;
			}
			if(subClassBracketNumber == 0 && inSubClass) inSubClass = false;
		}
		
	}
}
