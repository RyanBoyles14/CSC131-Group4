package CSC131Fall2018.Group4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

//halstead metrics class
public class Halstead {
	Set<String> distinctOperators = new HashSet<String>();
	Set<String> distinctOperands = new HashSet<String>();
	int totalOperators, totalOperands, vocab, length, time, bugs, effort, difficulty;
	double calcLength, volume;
	// defined as operators
	String[] operatorArray = { "=", ">", "<", "!", "~", "?", "->", "==", ">=", "<=", "!=", "&&", "||", "++", "--", "+",
			"-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=",
			">>=", ">>>=" };
	List<String> operators = Arrays.asList(operatorArray);
	File f;
	Scanner s;

	public Halstead(File f) throws FileNotFoundException {
		this.f = f;
		StringTokenizer tk;
		String token;
		List<String> tokenList = new ArrayList<String>();
		this.s = new Scanner(f);
		int count = 0;
		while (s.hasNextLine()) {
			count = 0;
			tk = new StringTokenizer(s.nextLine());
			while (tk.hasMoreTokens()) {
				token = tk.nextToken();
				if (operators.contains(token)) {
					distinctOperators.add(token);
					totalOperators++;
					count++;
				}
				tokenList.add(token);
			}
			if (count > 0) {
				count++;
				totalOperands += count;
			}
		}
		boolean firstOperand = true;
		for (int i = 0; i < tokenList.size(); i++) {
			if (operators.contains(tokenList.get(i))) {
				if (firstOperand) {
					distinctOperands.add(tokenList.get(i - 1));
					distinctOperands.add(tokenList.get(i + 1));
					firstOperand = false;
				} else {
					distinctOperands.add(tokenList.get(i + 1));
				}
			}
		}
	}

	public int getTotalOperators() {
		return totalOperators;
	}

	public int getTotalOperands() {
		return totalOperands;
	}

	public int getProgramVocabulary() {
		vocab = distinctOperators.size() + distinctOperands.size();
		return vocab;
	}

	public int getProgramLength() {
		length = totalOperators + totalOperands;
		return length;
	}

	public int getDifficulty() {
		difficulty = (distinctOperators.size() / 2) * (totalOperands / distinctOperands.size());
		return difficulty;
	}

	public int getVolume() {
		volume = (getProgramLength() * (Math.log(getProgramVocabulary()) / Math.log(2.0)));
		return (int) volume;
	}

	public int getEffort() {
		effort = getDifficulty() * getVolume();
		return effort;
	}

	public int getCalculatedLength() {
		calcLength = (distinctOperators.size() * (Math.log((distinctOperators.size()) / Math.log(2.0))
				+ distinctOperands.size() * (Math.log(distinctOperators.size()) / Math.log(2.0))));
		return (int) calcLength;
	}

	public int getTime() {
		time = getEffort() / 18;
		return time;
	}

	public int getBugs() {
		bugs = getVolume() / 3000;
		return bugs;
	}
}
