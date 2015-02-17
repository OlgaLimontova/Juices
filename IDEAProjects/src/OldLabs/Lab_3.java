package MyPackage.MadGenius;

import java.io.*;
import java.lang.*;

/*
  Лабораторная работа №3.
  В зависимости от признака (0 или 1) в каждой строке текста удалить
  указанный символ везде, где он встречается, или вставить его после k-гo символа.

  Лимонтова Ольга, 2 курс, 8 группа.
*/

public class Lab_3 {
    public static void main(String[] args) throws IOException {
        new Lab_3().run();
    }
    StreamTokenizer in;
    void run() throws IOException {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        solve();
    }
    int NextInt() throws IOException {  //считывание варианта выбора
        in.nextToken();
        return (int)in.nval;
    }
    void solve() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Input your text.");
        String[] lines = new String[100];
        boolean bool = true;
        int i = 0;
        while (bool) {
            lines[i] = br.readLine();
            if (lines[i].equals(""))
                bool = false;
            else
                i++;
        }
        System.out.println(i + " lines in your text.");
        String[] line = new String [i];
        for (int p = 0; p < i; p++)
            line[p] = lines[p];
        String res = "";
        for (int p = 0; p < i; p++)
            res += (lines[p] + " ");
        System.out.print("Input the symbol: ");
        char[] c = new char[1];
        c[0] = (char)br.read();
        String cBuf = new String(c);
        System.out.println("You may choose one variant:");
        System.out.println("0. Delete the element.");
        System.out.println("1. Insert the symbol on the position k + 1.");
        System.out.print("Input your choice: ");
        int choice = NextInt();
        switch (choice) {
            case 0: {
                System.out.println("You chose variant 0: delete the element " + cBuf + ".");
                int index = 0;
                int er = 0;
                int smth = 0;
                for (int p = 0; p < i; p++) {
                    StringBuffer stb = new StringBuffer(line[p]);
                    for (int m = 0; m < line[p].length(); m++) {
                        index = stb.indexOf(cBuf);
                        if (index != -1)
                            stb.deleteCharAt(index);    //удаление, если есть
                        else
                            if (index == -1) {
                                er++;
                                break;
                            }
                    }
                    if (er == 1)
                        smth++;
                    System.out.println(stb);
                }
                System.out.println();
                if (smth == 1)
                    System.out.println(cBuf + " is not found.");
                break;
            }
            case 1: {
                System.out.println("You chose variant 1: insert the element " + cBuf + ".");
                System.out.print("Input the position: ");
                int pos = NextInt();
                int index = 0;
                for (int p = 0; p < i; p++) {
                    if (pos < line[p].length()) {
                        StringBuffer stb = new StringBuffer(line[p]);
                        stb.insert(pos + 1, c); //вставка
                        System.out.println(stb);
                    }
                    else
                        System.out.println(line[p]);
                }
                break;
            }
            default: {
                System.out.println("Incorrect input!");
                break;
            }
        }
    }
}