package MyPackage;

import java.io.*;
import java.util.*;

/**
 * Created by NotePad on 06.09.2014.
 */
public class SumDouble {
           public static void main(String [] args) throws IOException {
            new SumDouble().run();
        }
        StreamTokenizer in;
        PrintWriter out;
        double nextDouble() throws IOException {
            in.nextToken();
            return (double)in.nval;
        }
        void run() throws IOException {
            in = new StreamTokenizer (new BufferedReader(new InputStreamReader(System.in)));
            out = new PrintWriter (new OutputStreamWriter(System.out));
            solve();
            out.flush();
        }
        void solve()throws IOException {
            double a = nextDouble();
            double b = nextDouble();
            out.print(a + b);
            out.print(" ");
            out.println(a - b);
        }
}
