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

                ls.add(st.sval);
            }
            if (st.ttype == StreamTokenizer.TT_WORD) {

                //System.out.println(st.sval);
            } else if (st.ttype == StreamTokenizer.TT_NUMBER) {

                //System.out.println(st.nval);
            }

        }
        for (int i = 0; i < ls.size(); i++) {
            s = ls.get(i);
            if (s.equals("while")) {
                ls2.add(s);
            } else if (s.equals("for")) {
                ls2.add(s);
            } else if (s.equals("{")) {
                ls2.add(s);
            } else if (s.equals("}")) {
                ls2.add(s);
            }

        }
        System.out.println(ls2);
        ListIterator<String> it = ls2.listIterator();
        int ind = ls2.indexOf("while") - ls2.indexOf("for");
        int firIndex = 0;
        if (ind < 0) {
            firIndex = ls2.indexOf("for");
        } else {
            firIndex = ls2.indexOf("while");
        }
        String g;
        while (it.hasNext()) {
            g = it.next();
            if (g.equals("while") || g.equals("for")) {
                System.out.println(it.previousIndex());
                index = it.previousIndex();

                System.out.println(ls2.get(index + 1));
            }

        }


        //System.out.println(ls);

    }
}

