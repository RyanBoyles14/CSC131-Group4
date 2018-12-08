package CSC131Fall2018.Group4;

import java.io.*;
import java.util.ArrayList;

public class TimeComplexity extends AbstractMetricsCalculator {
    ArrayList<File> fileArrayList;
    ArrayList<String> loops;

    @Override
    protected void newCalculation(File f) throws Exception {
        this.fileArrayList.add(f);

    }

    @Override
    protected void newCalculation(Repository r) throws Exception {
        this.fileArrayList = r.getList();


    }

    public TimeComplexity(Repository r) throws Exception {
        super(r);
    }

    public void getTimeComplexity() throws IOException {
        for (int i = 0; i < fileArrayList.size(); i++) {
            int nested = 1;
            BufferedReader br = new BufferedReader(new FileReader(fileArrayList.get(i).toString()));
            StreamTokenizer st = new StreamTokenizer(br);
            setStreamTokenizerSyntaxTable(st);
            String pt = null;
            int type;
            boolean loop = false;
            boolean nest = false;
            while(st.ttype != st.TT_EOF) {
                type = st.nextToken();
                switch (type) {
                    case StreamTokenizer.TT_WORD:
                        if(st.sval.equals("while") || st.sval.equals("for")) {
                            loops.add(st.sval);
                            loop = true;
                            if(pt.equals("{")) {
                                nest = true;
                            }
                        }
                        pt = st.sval;
                        break;

                }

            }

            if(!nest) {
                System.out.println("The estimate worst case time complexity of this file is O(n^" + nested + ")"  );
            } else if(nest) {
                for(String loops: loops) {
                    nested++;

                }
                System.out.println("The estimate worst case time complexity of this file is O(n^" + nested + ")");
            }





        }
    }

        public void setStreamTokenizerSyntaxTable (StreamTokenizer st){
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

}
