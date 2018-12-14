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
public class HalsteadMetricsCalculator extends AbstractMetricsCalculator
{
	private IMetrics metrics;

	Set<String> distinctOperators = new HashSet<String>();
	Set<String> distinctOperands = new HashSet<String>();
	// defined as operators
	String[] operatorArray = { "=", ">", "<", "!", "~", "?", "->", "==", ">=", "<=", "!=", "&&", "||", "++", "--", "+",
			"-", "*", "/", "&", "|", "^", "%", "<<", ">>", ">>>", "+=", "-=", "*=", "/=", "&=", "|=", "^=", "%=", "<<=",
			">>=", ">>>=" };
	List<String> operators = Arrays.asList(operatorArray);
	File f;
	Scanner s;

	public HalsteadMetricsCalculator(Repository r)
			throws Exception
	{
		super(r);
	}

	@Override
	protected void newCalculation(File f)
			throws Exception
	{
		throw new UnsupportedOperationException();
	}

	@Override
	protected void newCalculation(Repository r)
			throws Exception
	{
		this.metrics = r.getHalsteadMetrics();
		ArrayList<File> files = r.getList();

		for(File f: files) {
			String[] parts = f.toString().split("\\.");
			int index = parts.length-1;
			if(!parts[index].equals("java") && !parts[index].equals("c") && !parts[index].equals("h")
					&& !parts[index].equals("cpp") && !parts[index].equals("hpp") && !parts[index].equals("cxx"))
				continue;
			HalsteadBuilder hb = new HalsteadBuilder();
			hb.f = f;
			((Halstead.Metrics) this.metrics).halstead.add(hb);
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
						hb.totalOperators++;
						count++;
					}
					tokenList.add(token);
				}
				if (count > 0) {
					count++;
					hb.totalOperands += count;
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

			hb.vocab = this.distinctOperators.size() + this.distinctOperands.size();
			hb.length = hb.totalOperators + hb.totalOperands;
			hb.difficulty = (this.distinctOperators.size() / 2) * (hb.totalOperands / distinctOperands.size());
			hb.volume = (int) (hb.length * (Math.log(hb.vocab) / Math.log(2.0)));
			hb.effort = (int) (hb.difficulty * (hb.volume));
			hb.calcLength = (this.distinctOperators.size() * (Math.log((this.distinctOperators.size()) / Math.log(2.0))
					+ this.distinctOperands.size() * (Math.log(this.distinctOperators.size()) / Math.log(2.0))));
			hb.time = (hb.effort / 18);
			hb.bugs = (int) (hb.volume / 3000);
		}
	}
}
