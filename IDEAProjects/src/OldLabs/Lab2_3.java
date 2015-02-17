package OldLabs;

import java.io.*;
import java.util.*;

/*
  Лабораторная работа №2. Задача №34.
  Даны две действительные квадратные матрицы порядка n.
  Получить новую матрицу умножением элементов каждой строки первой матрицы
  на наибольшее из значений элементов соответствующей строки второй матрицы.

  Лимонтова Ольга, 2 курс, 8 группа.
*/

public class Lab2_3 {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter dimension, please: ");
        new Lab2_3().run();
    }
    StreamTokenizer in;
    int NextInt() throws IOException {  //считывание значения размерности
        in.nextToken();
        return (int)in.nval;
    }
    void run() throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        solve();
    }
    void solve() throws IOException {
        int n = NextInt();
        Random rand = new Random();
        int[][] Mat1 = new int[n][n];
        int[][] Mat2 = new int[n][n];
        int[][] Mat3 = new int[n][n];
        for (int i = 0; i < n; i++) {   //заполнение матрицы случайными числами, умноженными на 1 или -1
            for (int j = 0; j < n; j++) {
                Mat1[i][j] = rand.nextInt(15) * (int)Math.pow(-1, rand.nextInt(2));
            }
        }
        System.out.println();
        System.out.println("Matrix Mat1 is");
        for (int i = 0; i < n; i++) {   //вывод матрицы на консоль
            for (int j = 0; j < n; j++) {
                System.out.print(Mat1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {   //заполнение матрицы случайными числами, умноженными на 1 или -1
            for (int j = 0; j < n; j++) {
                Mat2[i][j] = rand.nextInt(15) * (int)Math.pow(-1, rand.nextInt(2));
            }
        }
        System.out.println("Matrix Mat2 is");
        for (int i = 0; i < n; i++) {   //вывод матрицы на консоль
            for (int j = 0; j < n; j++) {
                System.out.print(Mat2[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < n; i++) {   //получение третьей матрицы
            for (int j = 0; j < n; j++) {
                Mat3[i][j] = Mat1[i][j] * maximum(Mat2, i);
            }
        }
        System.out.println("Matrix Mat3 is");
        for (int i = 0; i < n; i++) {   //вывод третьей матрицы на консоль
            for (int j = 0; j < n; j++) {
                System.out.print(Mat3[i][j] + " ");
            }
            System.out.println();
        }
    }
    int maximum(int[][] A, int index) { //функция поиска максимального значения в строке index
        int buf = A[index][0];
        for (int i = 1; i < A[index].length; i++) {
            if (buf <= A[index][i]) {
                buf = A[index][i];
            }
        }
        return buf;
    }
}