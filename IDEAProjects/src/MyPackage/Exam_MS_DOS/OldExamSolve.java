package MyPackage.Exam_MS_DOS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OldExamSolve {
    StringComparator sc = new StringComparator();
    public OldExamSolve() throws IOException {
        solve();
    }
    public void solve() throws IOException {
        ArrayList <String> list = data();
        ArrayList <Boolean> isUsed = use();
        ArrayList <String> names = getNames(list);
        ArrayList <String> extensions = getExtensions(list);
        PrintWriter pw1 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_MS_DOS\\Out1.txt"));
        outData(list, pw1);
        pw1.close();
        PrintWriter pw2 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_MS_DOS\\Out2.txt"));
        outSort(list, pw2);
        pw2.close();
        PrintWriter pw3 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_MS_DOS\\Out3.txt"));
        outData(names, pw3);
        pw3.close();
        PrintWriter pw4 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_MS_DOS\\Out4.txt"));
        outData(extensions, pw4);
        pw4.close();
    }
    ArrayList <String> data() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\IDEA Projects\\src\\MyPackage\\Exam_MS_DOS\\Input.txt"));
        ArrayList <String> list = new ArrayList <String>();
        String buf;
        while (br.ready()) {
            buf = br.readLine();
            list.add(buf.substring(1, buf.indexOf(";")));
        }
        br.close();
        return list;
    }
    ArrayList <Boolean> use() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\IDEA Projects\\src\\MyPackage\\Exam_MS_DOS\\Input.txt"));
        ArrayList <Boolean> isUsed = new ArrayList <Boolean>();
        String buf;
        while (br.ready()) {
            buf = br.readLine();
            if (buf.charAt(0) == '+')
                isUsed.add(true);
            else
                isUsed.add(false);
        }
        br.close();
        return isUsed;
    }
    ArrayList <String> getNames(ArrayList <String> al) {
        ArrayList <String> names = new ArrayList <String>();
        for (String s: al)
            names.add(s.substring(0, s.indexOf(".")));
        return names;
    }
    ArrayList <String> getExtensions(ArrayList <String> al) {
        ArrayList <String> extensions = new ArrayList <String>();
        for (String s: al)
            extensions.add(s.substring(s.indexOf(".")));
        return extensions;
    }
    void outData(ArrayList <String> list, PrintWriter pw) throws IOException {
        for (String s: list)
            pw.println(s);
        pw.flush();
    }
    void outSort(ArrayList <String> list, PrintWriter pw) throws IOException {
        ArrayList <String> bufList = new ArrayList <String>();
        for (String s: list)
            bufList.add(s);
        Collections.sort(bufList, sc);
        for (String s: bufList)
            pw.println(s);
        pw.flush();
    }
    static class StringComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((String)o1).compareToIgnoreCase((String)o2);
        }
    }
}