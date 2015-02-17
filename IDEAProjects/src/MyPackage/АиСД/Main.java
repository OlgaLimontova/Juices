

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        TreeSet<Integer> numbers = new TreeSet<Integer>();
        long sum = 0;
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String buf;
        while (br.ready()) {
            buf = br.readLine();
            numbers.add(Integer.parseInt(buf));
        }
        br.close();
        for (Integer number : numbers)
            sum += number;
        PrintWriter fr = new PrintWriter(new FileWriter("output.txt"));
        fr.print(sum);
        fr.flush();
        fr.close();
    }
}
