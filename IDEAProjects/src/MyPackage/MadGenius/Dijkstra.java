package MyPackage.MadGenius;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Dijkstra {
    public static void main(String[] args) throws IOException {
        int MAX = Integer.MAX_VALUE;
        int current;
        int[][] weight = new int [100][100];
        int[] isFound = new int [100];
        int[] length = new int [101];
        int[] previous = new int [101];
        FileReader file = new FileReader("E:\\IDEA Projects\\src\\MyPackage\\MadGenius\\Dijkstra 1.txt");
        BufferedReader br = new BufferedReader(file);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int finish = Integer.parseInt(st.nextToken());
        start--;
        finish--;
        int m = 0;
        int k = 0;
        while (br.ready()) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                weight[m][k] = Integer.parseInt(st.nextToken());
                k++;
            }
            m++;
            k = 0;
        }
        for (int i = 0; i < num; i++) {
            length[i] = MAX;
            isFound[i] = 0;
        }
        previous[start] = 0;
        length[start] = 0;
        isFound[start] = 1;
        current = start;
        while (true) {
            for (int i = 0; i < num; i++) {
                if (weight[current][i] == 0)
                    continue;      // Вершины i и current не смежные
                if (isFound[i] == 0 && length[i] > length[current] + weight[current][i]) {
                    length[i] = length[current] + weight[current][i];
                    previous[i] = current;
                }
            }
            int w = MAX;
            current = -1;
            for (int i = 0; i < num; i++)
                if (isFound[i] == 0 && length[i] < w) {
                    current = i;
                    w = length[i];
                }
            if (current == -1) {
                System.out.println("There is no way from " + (start + 1) + " to " + (finish + 1) + ".");
                break;
            }
            if (current == finish) {
                System.out.print("The shortest way from " + (start + 1) + " to " + (finish + 1) + " is ");
                int u = finish;
                int[] way = new int [101];
                int i = 0;
                while (u != start) {
                    way[i++] = u + 1;
                    u = previous[u];
                }
                way[i] = start + 1;
                for (int j = i; j >= 0; j--)
                    System.out.print(way[j] + " ");
                System.out.println();
                System.out.println("Length is " + length[finish] + ".");
                break;
            }
            isFound[current] = 1;
        }
    }
}