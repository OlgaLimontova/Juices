package OldLabs;

import java.io.IOException;
import java.io.*;
import java.util.*;

/*
  Лабораторная работа №2. Задача №6.
  Выведите номера столбцов, элементы каждого из которых образуют
  монотонную последовательность (монотонно убывающую или монотонно возрастающую).

  Лимонтова Ольга, 2 курс, 8 группа.
*/

public class Lab2_1 {
    public static void main(String[] args) throws IOException {
        new Lab2_1().run();
    }
    void run() throws IOException {
        solve();
    }
    void solve() throws IOException, FileNotFoundException {
        int n = 0;
        int m = 0;
        File f = new File("E:\\IDEA Projects\\src\\MyPackage\\Lab2_1.txt"); //открываем файл
        Scanner sc = new Scanner(f);
        StringTokenizer dim = new StringTokenizer(sc.nextLine());  //считываем размерности матрицы
        n = Integer.parseInt(dim.nextToken());
        m = Integer.parseInt(dim.nextToken());
        System.out.println("Dimensions are " + n + " " + m + '.');
        System.out.println("Matrix is");
        int[][] Matrix = new int[n][m];
        int dimN = 0;
        int dimM = 0;
        while (sc.hasNext()) {
            dimM = 0;
            StringTokenizer numbers = new StringTokenizer(sc.nextLine());
            while (numbers.hasMoreTokens()) {
                Matrix[dimN][dimM] = Integer.parseInt(numbers.nextToken());   //заполняем матрицу числами из файла
                dimM++;
            }
            dimN++;
        }
        sc.close();
        int count = 0;
        int countBuf = 0;
        for (int i = 0; i < n; i++) {   //выводим матрицу на консоль
            for (int j = 0; j < m; j++) {
                System.out.print(Matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.print("Higher: ");   //вывод номеров столбцов для возрастающих последовательностей
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                if (Matrix[i][j] <= Matrix[i + 1][j]) {
                    count++;
                }
            }
            if (count == n - 1) {
                countBuf++;
                System.out.print(j + " ");
            }
            count = 0;
        }
        if (countBuf == 0) {
            System.out.print("not found.");  //вывод сообщения, если нет возрастающих последовательностей
        }
        System.out.println();
        countBuf = 0;
        System.out.print("Lower: ");    //вывод номеров столбцов для убывающих последовательностей
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n - 1; i++) {
                if (Matrix[i][j] >= Matrix[i + 1][j]) {
                    count++;
                }
            }
            if (count == n - 1) {
                countBuf++;
                System.out.print(j + " ");
            }
            count = 0;
        }
        if (countBuf == 0) {
            System.out.print("not found.");  //вывод сообщения, если нет убывающих последовательностей
        }
    }
}