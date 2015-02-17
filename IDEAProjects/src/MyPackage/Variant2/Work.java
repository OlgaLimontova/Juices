package MyPackage.Variant2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Work {
    StringComparator comp = new StringComparator();

    public Work() throws IOException {
        solve();
    }

    public void solve() throws IOException {
        Scanner sc = new Scanner(new File("E:\\IDEA Projects\\src\\MyPackage\\Variant2\\Input.txt"));
        int number = sc.nextInt();
        ArrayList <String> buf = readData();
        int ind = 0;
        String first = buf.get(0);
        Collections.sort(buf);
        String[] wordsSort = new String[number];
        for (String s: buf)
            wordsSort[ind++] = s;
        int index = findIndex(wordsSort, number, first);
        ArrayList <String> resList = getList(wordsSort, first, number, index);
        int sum = getSum(resList);
        PrintWriter pw = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Variant2\\output1.txt"));
        outData(resList, pw, sum);
        sortList(resList);
        pw = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Variant2\\output2.txt"));
        outByLength(resList, pw);
    }

    int findIndex(String[] wordsSort, int number, String first) {
        int index = -1;
        for (int i = 0; i < number; i++) {
            if (wordsSort[i].equals(first) && index < 0) {
                index = i;
                break;
            }
        }
        return index;
    }

    ArrayList <String> getList(String[] wordsSort, String first, int number, int index) {
        ArrayList <String> resList = new ArrayList <String>();
        resList.add(first);
        for (int i = index + 1; i < number; i++)
            if (wordsSort[i].charAt(0) == first.charAt(0))
                resList.add(wordsSort[i]);
        for (int i = 0; i < index; i++)
            if (wordsSort[i].charAt(0) == first.charAt(0))
                resList.add(wordsSort[i]);
        for (int i = 0; i < number; i++)
            if (wordsSort[i].charAt(0) != first.charAt(0))
                resList.add(wordsSort[i]);
        return resList;
    }

    int min(int num1, int num2) {
        if (num1 <= num2)
            return num1;
        return num2;
    }

    int subLength(String str1, String str2, int number) {
        int count = 0;
        if (str1.charAt(0) != str2.charAt(0))
            return 0;
        else {
            for (int i = 0; i < number; i++) {
                if (str1.charAt(i) == str2.charAt(i))
                    count++;
            }
        }
        return count;
    }

    ArrayList <String> readData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\IDEA Projects\\src\\MyPackage\\Variant2\\Input.txt"));
        ArrayList <String> list = new ArrayList <String>();
        String buf = br.readLine();
        while (br.ready()) {
            buf = br.readLine();
            list.add(buf);
        }
        br.close();
        return list;
    }

    int getSum(ArrayList <String> resList) {
        int sum = resList.get(0).length();
        for (int index = 0; index < resList.size() - 1; index++) {
            String str1 = resList.get(index);
            String str2 = resList.get(index + 1);
            int len = subLength(str1, str2, min(str1.length(), str2.length()));
            if (len > 0) {
                String substr = str1.substring(0, len);
                sum += str2.length() - substr.length();
            }
            else
                sum += str2.length();
        }
        return sum;
    }

    void outData(ArrayList <String> resList, PrintWriter pw, int sum) throws IOException {
        pw.println(sum);
        for (String s: resList)
            pw.println(s);
        pw.flush();
    }

    void outByLength(ArrayList <String> resList, PrintWriter pw) throws IOException {
        for (String s: resList)
            pw.println(s);
        pw.flush();
    }

    void sortList(ArrayList <String> resList) {
        Collections.sort(resList, comp);
    }

    static class StringComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((String)o1).length() - ((String)o2).length();
        }
    }
}