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

	public Halstead.Metrics metrics = this.new Metrics();

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
					this.metrics.totalOperators++;
					count++;
				}
				tokenList.add(token);
			}
			if (count > 0) {
				count++;
				this.metrics.totalOperands += count;
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

		this.metrics.vocab = this.distinctOperators.size() + this.distinctOperands.size();
		this.metrics.length = this.metrics.totalOperators + this.metrics.totalOperands;
		this.metrics.difficulty = (this.distinctOperators.size() / 2) * (this.metrics.totalOperands / distinctOperands.size());
		this.metrics.volume = (int)(this.metrics.length * (Math.log(this.metrics.vocab) / Math.log(2.0)));
		this.metrics.effort = (int)(this.metrics.difficulty * this.metrics.volume);
		this.metrics.calcLength = (this.distinctOperators.size() * (Math.log((this.distinctOperators.size()) / Math.log(2.0))
				+ this.distinctOperands.size() * (Math.log(this.distinctOperators.size()) / Math.log(2.0))));
		this.metrics.time = this.metrics.effort / 18;
		this.metrics.bugs = (int)(this.metrics.volume / 3000);
	}
}
