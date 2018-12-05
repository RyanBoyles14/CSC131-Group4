import java.io.StreamTokenizer;
import java.io.*;
import java.util.*;

public class Metrix {
    public static void main(String[] args) throws IOException {
        int index = 0;

        String s;


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
        while (st.nextToken() != st.TT_EOF) {

            if (st.sval != null) {
                if (st.sval.equals("while") || st.sval.equals("for") || st.sval.equals("{") || st.sval.equals("}")) {

                    ls.add(st.sval);
                }
            }
            if (st.ttype == StreamTokenizer.TT_WORD) {

                //System.out.println(st.sval);
            } else if (st.ttype == StreamTokenizer.TT_NUMBER) {

                //System.out.println(st.nval);
            }

        }
        //System.out.println(ls);

        //System.out.println(ls2);
        ListIterator<String> it = ls.listIterator();
        int firIndex = 0;
        if(ls.indexOf("while") - ls.indexOf("for") > 0 ) {
            firIndex = ls.indexOf("for");
        } else {
            firIndex = ls.indexOf("while");
        }





        int count = 0;
        int nested = 0;
        String g;
        while (it.hasNext()) {
            g = it.next();
            if (g.equals("while") || g.equals("for")) {
                System.out.println(it.nextIndex());
                index = it.nextIndex();
                System.out.println(ls.get(index) + ls.get(index+1));






            }

        }


        //System.out.println(ls);

    }

}

