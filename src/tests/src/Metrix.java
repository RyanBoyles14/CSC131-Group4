import java.io.StreamTokenizer;
import java.io.*;
import java.util.ArrayList;

import java.util.List;

public class Metrix {
    public static void main(String[] args) throws IOException {
        List<String> ls = new ArrayList<>();
        List<String> ls1 = new ArrayList<>();
        List<String> ls2 = new ArrayList<>();
        ls1.add("while");
        ls1.add("{");
        ls1.add("for");
        ls1.add("}");

        StreamTokenizer st = null;
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        st = new StreamTokenizer(br);
        //st.whitespaceChars(97,99);

        st.parseNumbers();
        st.whitespaceChars(32,96);
        st.whitespaceChars(124,124);
        st.whitespaceChars(126,126);
        st.whitespaceChars(127,127);
        //st.wordChars(33,62);
        st.wordChars(123, 123);
        st.wordChars(125,125);
        st.whitespaceChars(97,100);
        st.whitespaceChars(103,103);
        st.whitespaceChars(106,107);
        st.whitespaceChars(109,110);
        st.whitespaceChars(112,113);
        st.whitespaceChars(115,118);
        st.whitespaceChars(120,122);
        //st.wordChars(40,43);

        //st.wordChars(34,34);
        //st.wordChars(58,62);
        st.eolIsSignificant(true);
        while (st.nextToken() != st.TT_EOF) {
            if (st.sval != null) {

                ls.add(st.sval);
            }
            if (st.ttype == StreamTokenizer.TT_WORD) {

                //System.out.println(st.sval);
            } else if (st.ttype == StreamTokenizer.TT_NUMBER) {

                //System.out.println(st.nval);
            }

        }
        for(int i = 0; i < ls1.size(); i++) {
            for(int j = 0; j < ls.size(); j++) {
                if(ls1.get(i).equals(ls.get(j))){
                    ls2.add(ls.get(j));
                }

            }
        }
        System.out.println(ls2);










        //System.out.println(ls);
    }
}

