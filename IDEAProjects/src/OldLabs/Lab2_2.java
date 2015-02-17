package OldLabs;

import java.io.*;
import java.util.*;

/*
  Лабораторная работа №2. Задача №20.
  Пусть m(A, i) - это номер столбца матрицы А, в котором находится
  последний минимум i-й строки. Проверить, выполняется ли для матрицы А
  условие m(A,1) <= m(A,2) <= ... <= m(A,m).

  Лимонтова Ольга, 2 курс, 8 группа.
*/

public class Lab2_2 {
    public static void main(String[] args) throws IOException {
        new Lab2_2().run();
    }
    void run()throws IOException {
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
        System.out.println("Dimensions are " + n + ", " + m + ".");
        int[][] A = new int[n][m];
        int dimN = 0;
        int dimM = 0;
        while (sc.hasNext()) {
            dimM = 0;
            StringTokenizer numbers = new StringTokenizer(sc.nextLine());
            while (numbers.hasMoreTokens()) {
                A[dimN][dimM] = Integer.parseInt(numbers.nextToken());   //заполняем матрицу числами из файла
                dimM++;
            }
            dimN++;
        }
        sc.close();
        System.out.println("Matrix A is");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(A[i][j] + " ");    //вывод матрицы на консоль
            }
            System.out.println();
        }
        System.out.println();
        int[] numb = new int[n];
        for (int i = 0; i < n; i++) {
            numb[i] = minA(A, i);   //заполнение массива номерами столбцов, содержащих последние минимумы строк
        }
        System.out.print("Numbers of columns are ");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(numb[i] + " ");    //вывод элементов полученного массива на экран
        }
        System.out.print(numb[n - 1] + ".");
        System.out.println();
        int count = 0;
        for (int i = 0; i < n - 1; i++) {   //проверка последовательности номеров столбцов на монотонность
            if (numb[i] <= numb[i + 1]) {
                count++;
            }
        }
        if (count == n - 1) {   //если условие выполняется
            System.out.print("Yes! That's right.");
        }
        else {  //если условие не выполняется
            System.out.print("No, sorry.");
        }
    }
    int minA(int[][] A, int index) {    //функция поиска номера столбца, содержащего последний минимум в строке index
        int buf = A[index][0];
        int num = 0;
        for (int i = 1; i < A[index].length; i++) {
            if (buf >= A[index][i]) {
                buf = A[index][i];
                num = i;    //запоминаем номер столбца
            }
        }
        return num;
    }
}