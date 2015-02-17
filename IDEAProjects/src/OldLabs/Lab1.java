package OldLabs;

import java.io.*;
import java.text.*;

/*
  Лабораторная работа №1.
  Написать разложение функции синус в ряд Тейлора с определённой точностью.
  Значение аргумента и точность вводятся с клавиатуры.

  Лимонтова Ольга, 2 курс, 8 группа.
*/

public class Lab1 {
    public static void main (String [] args) throws IOException {
        System.out.println("Enter value of x and accuracy, please.");
        new Lab1().run();
    }
    StreamTokenizer in;
    PrintWriter out;
    double NextDouble() throws IOException {        //считывание значения аргумента
        in.nextToken();
        return (double)in.nval;
    }
    int NextInt() throws IOException {      //считывание значения точности
        in.nextToken();
        return (int)in.nval;
    }
    void run() throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        solve();
        out.flush();
    }
    void solve() throws IOException {
        double x = NextDouble();
        int k = NextInt();
        if (x > 2 * Math.PI || x < -2 * Math.PI) {      //избавление от периода
            int buf = (int)(x / Math.PI);
            x -= buf * Math.PI;
        }
        double a = x;
        double Res = x;
        int i = 1;
        while (Math.abs(a) > Math.pow(10, -k)) {        //разложение функции в ряд с указанной точностью
            i++;
            a *= -x * x / (i * (i + 1));
            Res += a;
            i++;
        }
        out.print("My value is ");
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(k);
        out.println(formatter.format(Res));
        out.print("Standart value is ");
        out.println(formatter.format(Math.sin(x)));
    }
}