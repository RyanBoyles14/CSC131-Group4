package CSC131Fall2018.Group4;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class TimeComplexityMetricsCalculator extends AbstractMetricsCalculator
{
    ArrayList<File> fileArrayList;
    ArrayList<String> loop;
    private int loonum;
    private int count;
    private int newin;
    String[] loops;
	IMetrics metrics;

    @Override
    protected void newCalculation(File f)
			throws Exception
	{
        this.fileArrayList.add(f);
    }

    @Override
    protected void newCalculation(Repository r)
			throws Exception
	{
        this.fileArrayList = r.getList();
        this.metrics = r.getTimeComplexityMetrics();
		((TimeComplexity.Metrics)this.metrics).worstCase = "O(n^" + (this.getTimeComplexity()) + ")";
    }

    public TimeComplexityMetricsCalculator(Repository r)
			throws Exception
	{
        super(r);
    }

    private int getTimeComplexity()
			throws IOException
	{
        int index;


        for(int i = 0; i < fileArrayList.size(); i++) {
            BufferedReader br = new BufferedReader(new FileReader(fileArrayList.get(i)));
            StreamTokenizer st = new StreamTokenizer(br);
            this.setStreamTokenizerSyntaxTable(st);
            while (st.nextToken() != st.TT_EOF) {
                if (st.sval != null) {
                    if (st.sval.equals("while") || st.sval.equals("for") || st.sval.equals("{") || st.sval.equals("}")) {
                        loop.add(st.sval);

                    }
                }
            }
            ListIterator<String> it = loop.listIterator();
            for (int j = 0; j < loop.size(); j++) {
                loops[j] = it.next();
            }

            for (int j = 0; j < loops.length; j++) {
                if (loops[j].equals("while") || loops[j].equals("for")) {
                    index = j;
                    if (loops[index + 1].equals("{") && loops[index + 2].equals("}")) {
                        loonum = 1;
                    } else if (loops[index+1].equals("{") && loops[index + 2].equals("{")) {
                        newin = index + 3;
                        for (int h = newin; h < loops.length; h++) {
                            if(loops[h].equals("{")) {
                                loonum++;
                            }
                        }
                        //count = 1;

                        //for (int k = index + 2; k < loops.length - (index + 2); j++) {
                        //    if (!(loops[k].equals("while") || loops[k].equals("for"))) {
                        //        count++;
                        //        newin = k;
                        //    } else if(loops[newin + 1].equals("}") && count%2==0 ){
                        //        loonum++;

                        //    }
                        }


                    }

                }
            }


        return loonum;
    }

	private void setStreamTokenizerSyntaxTable (StreamTokenizer st)
	{
		//set the whitespace/delimiters
		st.parseNumbers();
		st.whitespaceChars(32, 96);
		st.whitespaceChars(124, 124);
		st.whitespaceChars(126, 126);
		st.whitespaceChars(127, 127);
		//st.wordChars(33,62);
		st.wordChars(123, 123);
		st.wordChars(125, 125);
		st.whitespaceChars(97, 100);
		st.whitespaceChars(103, 103);
		st.whitespaceChars(106, 107);
		st.whitespaceChars(109, 110);
		st.whitespaceChars(112, 113);
		st.whitespaceChars(115, 118);
		st.whitespaceChars(120, 122);
		//st.wordChars(40,43);

		//st.wordChars(34,34);
		//st.wordChars(58,62);
		st.eolIsSignificant(true);
	}

	public int getLoonum() {
		return loonum;
	}

	public String toString() {
		String s = "";
		s = "The estimate worst case time complexity of this file is O(n" + loonum + ")";
		return s;

	}
}
