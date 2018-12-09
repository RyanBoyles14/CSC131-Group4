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
public class Halstead extends AbstractMetricsCalculator
{
	public class Metrics implements IMetrics
	{
		public int totalOperators;
		public int totalOperands;
		public int vocab;
		public int length;
		public int time;
		public int bugs;
		public int effort;
		public int difficulty;
		public double calcLength;
		public double volume;
	}

	Set<String> distinctOperators = new HashSet<String>();
	Set<String> distinctOperands = new HashSet<String>();
	// defined as operators
	String[] operatorArray = { "=", ">", "<", "!", "~", "?", "->", "==", ">=", "<=", "!=", "&&", "||", "++", "--", "+",
			"-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=",
			">>=", ">>>=" };
	List<String> operators = Arrays.asList(operatorArray);
	File f;
	Scanner s;

	public Halstead(File f) throws Exception
	{
		super(f);
	}

	@Override
	protected void newCalculation(Repository f) throws Exception
	{
		throw new UnsupportedOperationException();
	}

	@Override
	protected void newCalculation(File f) throws Exception
	{
		this.f = f;
		this.metrics = new Halstead.Metrics();

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
					((Halstead.Metrics) this.metrics).totalOperators++;
					count++;
				}
				tokenList.add(token);
			}
			if (count > 0) {
				count++;
				((Halstead.Metrics) this.metrics).totalOperands += count;
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

		((Halstead.Metrics) this.metrics).vocab = this.distinctOperators.size() + this.distinctOperands.size();
		((Halstead.Metrics) this.metrics).length = ((Halstead.Metrics) this.metrics).totalOperators + ((Halstead.Metrics) this.metrics).totalOperands;
		((Halstead.Metrics) this.metrics).difficulty = (this.distinctOperators.size() / 2) * (((Halstead.Metrics) this.metrics).totalOperands / distinctOperands.size());
		((Halstead.Metrics) this.metrics).volume = (int)(((Halstead.Metrics) this.metrics).length * (Math.log(((Halstead.Metrics) this.metrics).vocab) / Math.log(2.0)));
		((Halstead.Metrics) this.metrics).effort = (int)(((Halstead.Metrics) this.metrics).difficulty * ((Halstead.Metrics) this.metrics).volume);
		((Halstead.Metrics) this.metrics).calcLength = (this.distinctOperators.size() * (Math.log((this.distinctOperators.size()) / Math.log(2.0))
				+ this.distinctOperands.size() * (Math.log(this.distinctOperators.size()) / Math.log(2.0))));
		((Halstead.Metrics) this.metrics).time = ((Halstead.Metrics) this.metrics).effort / 18;
		((Halstead.Metrics) this.metrics).bugs = (int)(((Halstead.Metrics) this.metrics).volume / 3000);
	}
}
