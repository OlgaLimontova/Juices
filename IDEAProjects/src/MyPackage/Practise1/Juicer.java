package MyPackage.Practise1;

import java.io.*;
import java.util.*;

public class Juicer extends Thread {
    MyComparator comp = new MyComparator();
    Juice juice = new Juice();

    public Juicer() throws IOException {
        work();
    }

    public void work() throws IOException {
        ArrayList<Juice> juices = readData();
//        writeAll(juices);
        for (int i = 0; i < juices.size(); i++) {

        }
    }

    public ArrayList<Juice> readData() throws IOException {
        ArrayList<Juice> juices = new ArrayList<Juice>();
        BufferedReader br = new BufferedReader(new FileReader("juice.in"));

        return juices;
    }

    TreeSet<String> addComponents(ArrayList<Juice> juices) {
        TreeSet<String> allComponents = new TreeSet<String>();
        for (int j = 0; j < juices.size(); j++) {
            for (int i = 0; i < juice.components.size(); i++)
                allComponents.add(juices.get(j).components.get(i));
        }
        return allComponents;
    }

    public void writeAll(TreeSet<String> allComponents) throws IOException {

        PrintWriter pw = new PrintWriter(new FileWriter("juice1.out"));
//        for (int i = 0; i < allComponents.size(); i++)
//            pw.println(allComponents.);

    }

    static class MyComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return ((String)o1).compareTo(((String)o2));
        }
    }

    @Override
    public void run() {
        Collections.sort(juice.components, comp);
    }

    class Juice {
        ArrayList<String> components;
        int number;
    }
}