package MyPackage.Exam_Six;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Exam_Solve extends Thread {

    public Exam_Solve() throws IOException {
        solve();
    }

    NameComparator nc = new NameComparator();
    SizeComparator sc = new SizeComparator();

    public void solve() throws IOException {
        ArrayList <String> list = readData();
        ArrayList <Array> allArrays = new ArrayList <Array>();
        Iterator it = list.listIterator();
        while (it.hasNext()) {
            Object element = it.next();
            Array buf = getArray((String)element);
            allArrays.add(buf);
        }
        ArrayList <Array> sortByName = allArrays;
        ArrayList <Array> sortBySize = allArrays;
        PrintWriter pw1 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_Six\\bytes1.out"));
        PrintWriter pw2 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_Six\\bytes2.out"));
        PrintWriter pw3 = new PrintWriter(new FileWriter("E:\\IDEA Projects\\src\\MyPackage\\Exam_Six\\bytes3.out"));
        Collections.sort(sortByName, nc);
        for (Array myArray: sortByName)
            myArray.printList(pw1);
        Collections.sort(sortBySize, sc);
        for (Array myArray: sortBySize)
            myArray.printSize(pw2);
        for (Array myArray: allArrays)
            myArray.printMemory(pw3);
        pw1.close();
        pw2.close();
        pw3.close();
    }

    ArrayList <String> readData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\IDEA Projects\\src\\MyPackage\\Exam_Six\\arrays.in"));
        ArrayList <String> list = new ArrayList <String>();
        String buf;
        StringTokenizer st;
        while (br.ready()) {
            buf = br.readLine();
            st = new StringTokenizer(buf, ";");
            while (st.hasMoreTokens())
                list.add(st.nextToken());
        }
        br.close();
        return list;
    }

    Array getArray(String s) {
        String declaration = s.substring(0, s.indexOf("=") - 1);
        declaration = declaration.trim();
        String name = getName(declaration);
        String type = getType(declaration);
        String initialization = s.substring(s.indexOf("=") + 1).trim();
        int[] dimensions = getDimensions(initialization);
        int size = 1;
        for (int i = 0; i < dimensions.length; i++)
            size *= dimensions[i];
        int memory = getMemory(dimensions, type);
        Array myArray = new Array(declaration, name, type, size, memory);
        return myArray;
    }

    String getName(String declaration) {
        StringTokenizer st = new StringTokenizer(declaration, "[] ");
        String name = st.nextToken();
        name = st.nextToken();
        return name;
    }

    String getType(String declaration) {
        StringTokenizer st = new StringTokenizer(declaration, "[] ");
        String type = st.nextToken().trim();
        return type;
    }

    int[] getDimensions(String initialization) {
        if (initialization.startsWith("new")) {
            String buf = initialization.substring(initialization.indexOf("["));
            StringTokenizer st = new StringTokenizer(buf, "[]");
            int[] dim = new int[st.countTokens()];
            for (int i = 0; i < dim.length; i++)
                dim[i] = Integer.parseInt(st.nextToken());
            return dim;
        }
        else {
            StringTokenizer st = new StringTokenizer(initialization, ",{}");
            int[] dim = new int[1];
            dim[0] = st.countTokens();
            return dim;
        }
    }

    int getMemory(int[] dimensions, String type) {
        int size = dimensions.length;
        int memory = 0;
        int multiplier = getMultiplier(type);
        memory = 28 + multiplier * dimensions[size - 1];
        if (memory % 8 != 0)
            memory = ((memory / 8) + 1) * 8;
        if (size > 1) {
            for (int i = size - 2; i >= 0; i--) {
                memory *= dimensions[i];
                memory += 28;
                if (memory % 8 != 0)
                    memory = ((memory / 8) + 1) * 8;
            }
        }
        return memory;
    }

    int getMultiplier (String type) {
        int multiplier;
        if (type.equals("long") || type.equals("double"))
            multiplier = 8;
        else if (type.equals("int") || type.equals("float"))
            multiplier = 4;
        else if (type.equals("char") || type.equals("short"))
            multiplier = 2;
        else
            multiplier = 1;
        return multiplier;
    }

    static class NameComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((Array)o1).getName().compareTo(((Array)o2).getName());
        }
    }

    static class SizeComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((Array)o2).getSize() - ((Array)o1).getSize();
        }
    }

}

class Array {
    private String declaration;
    private String name;
    private String type;
    private int size;
    private int memory;
    public Array(String declaration, String name, String type, int size, int memory) {
        this.declaration = declaration;
        this.name = name;
        this.type = type;
        this.size = size;
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void printList(PrintWriter pw) throws IOException {
        pw.println(name);
        pw.flush();
    }

    public void printSize(PrintWriter pw) throws IOException {
        pw.println(name + " - " + size);
        pw.flush();
    }

    public void printMemory(PrintWriter pw) throws IOException {
        pw.println(declaration + " - " + memory);
        pw.flush();
    }
}