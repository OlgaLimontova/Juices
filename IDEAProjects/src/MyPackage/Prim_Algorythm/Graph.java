package MyPackage.Prim_Algorythm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Graph {
    private int count;  // количество вершин в графе
    private int num;    // количество ребер в графе
    private int resultWeight = 0; // вес минимального остовного дерева
    ArrayList[] pairs;  // пара: инцидентная данной вершина и вес ребра, соедищяющего их
    int[] start;    // начало ребра, вошедшего в дерево
    int[] finish;   // конец ребра, вошедшего в дерево
    double[] XCoord;    // координаты вершин графа
    double[] YCoord;
    public void readData() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\IDEA Projects\\src\\MyPackage\\Prim_Algorythm\\Prim.txt"));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        count = Integer.parseInt(st.nextToken());   // количество вершин графа
        num = Integer.parseInt(st.nextToken()); // количество ребер графа
        pairs = new ArrayList[count];
        for (int i = 0; i < count; i++)
            pairs[i] = new ArrayList(); // здесь будут храниться данные из файла
        int vStart;   // номер вершины, из которой выходит ребро
        int vFinish; // номер вершины, в которую входит ребро
        int weight; // вес ребра, соединяющего вершины
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            vStart = Integer.parseInt(st.nextToken());
            vFinish = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            pairs[vStart-1].add(new MyPair(vFinish - 1, weight));
            pairs[vFinish-1].add(new MyPair(vStart - 1, weight));
        }
        for (int i = 0; i < count; i++)
            Collections.sort(pairs[i], new WeightComp());
    }
    public void PrimAlgorythm() {
        start = new int[count-1];
        finish = new int[count-1];
        boolean[] isUsed = new boolean[count];
        int countInTree = 2;
        int[] index = new int[count];
        int minElement = Integer.MAX_VALUE;
        int minIndex = -1;
        MyPair curr = null;
        for (int i = 0; i < count; i++)
            if (pairs[i].size() > index[i]) {
                curr = (MyPair)(pairs[i].get(index[i]));
                if (curr.getWeight() < minElement) {
                    minElement = curr.getWeight();
                    minIndex = i;
                }
            }
        start[0] = minIndex;
        finish[0] = ((MyPair)(pairs[minIndex].get(index[minIndex]))).getNumberOfVertex();
        isUsed[minIndex] = true;
        isUsed[((MyPair)(pairs[minIndex].get(index[minIndex]))).getNumberOfVertex()] = true;
        resultWeight = minElement;
        int ind = 1;
        while (countInTree < count) {
            minElement = Integer.MAX_VALUE;
            minIndex = -1;
            for (int i = 0; i < count; i++)
                if (isUsed[i])
                    if (pairs[i].size() > index[i]) {
                        curr = (MyPair)(pairs[i].get(index[i]));
                        if (isUsed[curr.getNumberOfVertex()]) {
                            index[i--]++;
                            continue;
                        }
                        if (curr.getWeight() < minElement) {
                            minElement = curr.getWeight();
                            minIndex = curr.getNumberOfVertex();
                            start[ind] = i;
                            finish[ind] = minIndex;
                        }
                    }
            ind++;
            isUsed[minIndex] = true;
            resultWeight += minElement;
            countInTree++;
        }
    }
//    public void solve() throws IOException {
//        readData();
//        PrimAlgorythm();
//    }
    public void createPoints() {
        XCoord = new double[count];
        YCoord = new double[count];
        double angle = 2.0 * Math.PI / count;
        double bufAngle = 0.0;
        XCoord[0] = 75.0;
        YCoord[0] = 210.0;
        for (int i = 1; i < count; i++) {
            XCoord[i] = XCoord[0] + 200.0 * (1 - Math.cos(bufAngle + angle));
            YCoord[i] = YCoord[0] + 200.0 * Math.sin(bufAngle + angle);
            bufAngle += angle;
        }
    }
    public int getCount() { return count; }
    public double getXCoord(int i) { return XCoord[i]; }
    public double getYCoord(int i) { return YCoord[i]; }
    public int getResultWeight() { return resultWeight; }
}