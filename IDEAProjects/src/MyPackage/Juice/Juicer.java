package MyPackage.Juice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

class Juice {
    private int number;
    private ArrayList <String> components;
    public Juice(int number, ArrayList <String> components) {
        this.number = number;
        this.components = components;
    }
    public int getNumber() {
    return number;
}
    public ArrayList <String> getComponents() {
        return components;
    }
}

public class Juicer extends Thread {
    ArrayList<String> caseComponents;
    NumberComparator number = new NumberComparator();
    CodeComparator code = new CodeComparator();
    public Juicer() throws IOException {
        solve();
    }
    public void solve() throws IOException {
        ArrayList <String> allComponents = new ArrayList<String>();
        ArrayList<String> juices = getData("juice_1.in");
        ArrayList<Juice> allJuices = juiceList(juices);
        caseComponents = getComponents("juice_1.in");
        for (int i = 0; i < caseComponents.size(); i++)
            allComponents.add(caseComponents.get(i));
        allComponents = getListOfComponents(allComponents);
        outData(allComponents, "juice1.out");
        start();
        Collections.sort(allJuices, number);
        outNumber(getCount(allJuices), "juice3.out");
        caseComponents = getListOfComponents(caseComponents);
        outData(caseComponents, "juice2.out");
    }
    ArrayList<String> getData(String way) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(way));
        ArrayList<String> list = new ArrayList<String>();
        String buf;
        while (br.ready()) {
            buf = br.readLine();
            list.add(buf);
        }
        br.close();
        return list;
    }
    ArrayList<String> getComponents(String way) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(way));
        ArrayList<String> list = new ArrayList<String>();
        String buf;
        StringTokenizer st;
        while (br.ready()) {
            buf = br.readLine();
            st = new StringTokenizer(buf, " ");
            while (st.hasMoreTokens())
                list.add(st.nextToken());
        }
        br.close();
        return list;
    }
    ArrayList<String> getListOfComponents(ArrayList<String> al) {
        String buf;
        for (int i = 0; i < al.size(); i++) {
            buf = al.get(i);
            for (int j = i + 1; j < al.size(); j++)
                if (buf.equalsIgnoreCase(al.get(j)))
                    al.remove(j--);
        }
        return al;
    }
    ArrayList<Juice> juiceList(ArrayList<String> al) {
        StringTokenizer strTok;
        String buf;
        ArrayList<Juice> list = new ArrayList<Juice>();
        for (String s: al) {
            ArrayList<String> components = new ArrayList<String>();
            int i = 0;
            strTok = new StringTokenizer(s, " ");
            while (strTok.hasMoreTokens()) {
                buf = strTok.nextToken().toLowerCase();
                components.add(buf);
                i++;
            }
            Collections.sort(components);
            Juice temp = new Juice(i, components);
            list.add(temp);
        }
        return list;
    }
    int getCount(ArrayList<Juice> juices) {
        HashMap<ArrayList<String>, ArrayList<String>> hm = new HashMap<ArrayList<String>, ArrayList<String>>();
        ArrayList<String> list1;
        ArrayList<String> list2;
        for (int i = 0; i < juices.size(); i++)
            for (int j = i; j < juices.size(); j++) {
                list1 = juices.get(i).getComponents();
                list2 = juices.get(j).getComponents();
                if (((list1.containsAll(list2)) || (list2.containsAll(list1))) && (j != i)) {
                    if (!hm.containsKey(list2)) {
                        if (list2.containsAll(list1)) {
                            hm.put(list2, list1);
                            juices.remove(i--);
                            break;
                        }
                    }
                    else {
                        if (hm.get(list2).containsAll(list1))
                            juices.remove(j--);
                        if (list1.containsAll(hm.get(list2))) {
                            hm.put(list1, list2);
                            juices.remove(j--);
                        }
                    }
                }
            }
        return juices.size();
    }
    public void outData(ArrayList<String> al, String way) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(way));
        for (String s: al)
            pw.println(s);
        pw.flush();
        pw.close();
    }
    public void outNumber(int number, String way) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(way));
        pw.print(number + " cleans.");
        pw.flush();
        pw.close();
    }
    @Override
    public void run() {
        Collections.sort(caseComponents, code);
    }

    static class CodeComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((String)o1).compareTo((String)o2);
        }
    }

    static class NumberComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((Juice)o1).getNumber() - ((Juice)o2).getNumber();
        }
    }
}