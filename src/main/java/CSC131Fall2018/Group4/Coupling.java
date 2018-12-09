package CSC131Fall2018.Group4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.util.*;
import java.io.*;

public class Coupling extends AbstractMetricsCalculator {
	ArrayList<File> fileList = new ArrayList<File>();
	ArrayList<ClassStats> classes = new ArrayList<ClassStats>();
	//data structure to hold all of the coupling
	//information for a class
	class ClassStats{
		String classname;
		int index;
		ArrayList<InteractionEntry> interactionCoupling;
		ArrayList<ComponentEntry> componentCoupling;
		public ClassStats(String classname, int index) {
			this.classname = classname;
			this.index = index;
		}
	}
	
	//data type to represent a Component Coupling entry
	class ComponentEntry{
		String targetClassname;
		String value;
		//Constructor to assign values to data type fields
		public ComponentEntry(String name, String value) {
			this.targetClassname = name;
			this.value = value;
		}
	}
	
	//data type to represent an Interaction Coupling entry
	class InteractionEntry{
		String targetClassname;
		String value;
		//Constructor to assign values to data type fields
		public InteractionEntry(String name, String value) {
			this.targetClassname = name;
			this.value = value;
		}
	}
	//constructor sets the files to be used for the metric
	public Coupling(Repository r)
		throws Exception
	{
		super(r);
	}

	protected void newCalculation(File f)
			throws Exception
	{
		throw new UnsupportedOperationException();
	}

	protected void newCalculation(Repository r)
			throws Exception
	{
		this.fileList = r.getList();
	}

	//This method creates the list of ClassStats Objects for the project
	public void setClassStats() throws IOException{
		//iterate across all of the files
		for(int i = 0; i < this.fileList.size(); i++) {
			int type;
			BufferedReader buffRead;
			buffRead = new BufferedReader(new FileReader(fileList.get(i)));
			StreamTokenizer st = new StreamTokenizer(buffRead);
			setTokenizerSyntaxTable(st);
			String previousToken = null;
			do {
				type = st.nextToken();
				switch(type) {
				
					case StreamTokenizer.TT_WORD:
						//gets the classname. class will be in previousToken
						//and st.sval will contain the class name
						if(previousToken != null && previousToken.equals("class")) {
							//creates a ClassStats entry with the name
							//of the class and index of the ArrayList<File>
							//where the class is found
							classes.add(new ClassStats(st.sval, i));
							previousToken = null;
						}
						previousToken = st.sval;
						break;
					default:	
				}
			}while(st.ttype != StreamTokenizer.TT_EOF);
		}
	}
	
	//sets the options for the stream tokenizer
	public void setTokenizerSyntaxTable(StreamTokenizer tokenizer) {
		//set the whitespace/delimiters
		tokenizer.whitespaceChars(33, 33);
		tokenizer.whitespaceChars(35, 46);
		tokenizer.whitespaceChars(58, 59);
		tokenizer.whitespaceChars(61, 61);
		tokenizer.whitespaceChars(63, 64);
		tokenizer.whitespaceChars(92, 92);
		tokenizer.whitespaceChars(94, 94);
		tokenizer.whitespaceChars(123, 126);
		
		//set word values
		tokenizer.wordChars(48, 57);//sets 0-9 ascii as word characters
		tokenizer.wordChars(95, 95);//sets '_' ascii as word character
		tokenizer.wordChars(60, 60);// sets <
		tokenizer.wordChars(62, 62);// sets >
		tokenizer.wordChars(91, 91);// sets [
		tokenizer.wordChars(93, 93);// sets ]
		
		//set comment values to ignore comments
		tokenizer.slashStarComments(true);
		tokenizer.slashSlashComments(true);
		
		//set quote delimiter
		tokenizer.quoteChar(34);
	}
	
	//get the component coupling for the classes
	public void getComponentCoupling() throws IOException{
		int type;
		for(int i = 0; i < classes.size(); i++) {
		BufferedReader buffRead;
		buffRead = new BufferedReader(new FileReader(fileList.get(classes.get(i).index)));
		StreamTokenizer st = new StreamTokenizer(buffRead);
		setTokenizerSyntaxTable(st);
		st.ordinaryChar(123);
		st.ordinaryChar(125);
		st.ordinaryChar(59);
		st.whitespaceChars(60, 60);
		st.whitespaceChars(62, 62);
		st.whitespaceChars(91, 91);
		st.whitespaceChars(93, 93);
		boolean classParsed = false;
		boolean inClass = false;
		boolean inOtherClass = false;
		//-1 denotes that we have not yet increased the bracket
		//number
		int bracketNumber = -1;
		int otherClassBracketNumber = -1;
		String previousToken = null;
		//evaluates whether the class has finished parsing
		//or the end of file has been reached
		do {
			type = st.nextToken();
			switch(type) {
			
				case StreamTokenizer.TT_WORD:
					if(previousToken != null && previousToken.equals("class") && st.sval.equals(classes.get(i).classname)) {
						inClass = true;
					}
					else if(previousToken != null && previousToken.equals("class") && !st.sval.equals(classes.get(i).classname) && inClass) {
						inOtherClass = true;
					}
					//here we check for component coupling
					else if(inClass && !inOtherClass) {
						for(int j = 0; j < classes.size(); j++) {
							//if the parsed value is equal to the name of one of
							//our classes, except the one we are currently in,
							//then we create a ComponentCoupling entry
							if(st.sval != null && st.sval.equals(classes.get(j).classname) && !st.sval.equals(classes.get(i).classname)) {
								st.nextToken();
								classes.get(i).componentCoupling.add(new ComponentEntry(classes.get(j).classname, st.sval));
								//need to skip to the semicolon
								do {
									type = st.nextToken();
								}while((char)st.ttype != ';' && st.ttype != StreamTokenizer.TT_EOF);
							}
						}
					}
					//more to add in
					previousToken = st.sval;
					break;
				//handles whitespace character case
				//we also use '.' and '(' as whitespaces to evaluate
				default:
					//case handling opening brackets as a way to determine
					//beginning of scope of class
					if((char)type == '{') {
						if(!inOtherClass && inClass) {
							if(bracketNumber == -1) bracketNumber = 1;
							else bracketNumber++;
						}
						else if(inOtherClass && inClass) {
							if(bracketNumber == -1) otherClassBracketNumber = 1;
							else otherClassBracketNumber++;
						}
					}
					//case handling closing brackets as a way to determine
					//end of scope of a class
					else if((char)type == '}') {
						if(!inOtherClass && inClass) {
							bracketNumber--;
							if(bracketNumber == 0) {
								classParsed = true;
								bracketNumber = -1;
								inClass = false;
							}
						}
						if(inOtherClass && inClass) {
							otherClassBracketNumber--;
							if(otherClassBracketNumber == 0) {
								otherClassBracketNumber = -1;
								inOtherClass = false;
							}
						}
					}
			}
		}while(!classParsed && st.ttype != StreamTokenizer.TT_EOF);
		
	}
	}
	
	//get the interaction coupling for all classes
	public void getInteractionCoupling() throws IOException{
		int type;
		for(int i = 0; i < classes.size(); i++) {
		BufferedReader buffRead;
		buffRead = new BufferedReader(new FileReader(fileList.get(classes.get(i).index)));
		StreamTokenizer st = new StreamTokenizer(buffRead);
		setTokenizerSyntaxTable(st);
		boolean classParsed = false;
		boolean inClass = false;
		boolean inOtherClass = false;
		//-1 denotes that we have not yet increased the bracket
		//number
		int bracketNumber = -1;
		int otherClassBracketNumber = -1;
		String previousToken = null;
		String methodName = null;
		//evaluates whether the class has finished parsing
		//or the end of file has been reached
		do {
			type = st.nextToken();
			switch(type) {
			
				case StreamTokenizer.TT_WORD:
					if(previousToken != null && previousToken.equals("class") && st.sval.equals(classes.get(i).classname)) {
						inClass = true;
					}
					else if(previousToken != null && previousToken.equals("class") && !st.sval.equals(classes.get(i).classname) && inClass) {
						inOtherClass = true;
					}
					//more to add in
					previousToken = st.sval;
					break;
				//handles whitespace character case
				//we also use '.' and '(' as whitespaces to evaluate
				default:
					//case handling opening brackets as a way to determine
					//beginning of scope of class
					if(type == '{') {
						if(!inOtherClass && inClass) {
							if(bracketNumber == -1) bracketNumber = 1;
							else bracketNumber++;
						}
						else if(inOtherClass && inClass) {
							if(bracketNumber == -1) otherClassBracketNumber = 1;
							else otherClassBracketNumber++;
						}
					}
					//case handling closing brackets as a way to determine
					//end of scope of a class
					else if(type == '}') {
						if(!inOtherClass && inClass) {
							bracketNumber--;
							if(bracketNumber == 0) {
								classParsed = true;
								bracketNumber = -1;
								inClass = false;
								//once the end of class is reached there's no
								//more need for parsing
								return;
							}
						}
						if(inOtherClass && inClass) {
							otherClassBracketNumber--;
							if(otherClassBracketNumber == 0) {
								otherClassBracketNumber = -1;
								inOtherClass = false;
							}
						}
					}
					//case to evaluate whether there is interaction coupling
					//and creates an interactionEntry if there is
					else if (type == '.' && inClass && !inOtherClass) {
						type = st.nextToken();
						if(type == StreamTokenizer.TT_WORD) {
							methodName = st.sval;
							type = st.nextToken();
							if(type == '(') {
								classes.get(i).interactionCoupling.add(new InteractionEntry(previousToken, methodName + "()"));
							}
						}
					}
					
			}
		}while(!classParsed && st.ttype != StreamTokenizer.TT_EOF);
		
	}
	}
}
