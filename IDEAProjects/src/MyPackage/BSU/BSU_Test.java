package MyPackage.BSU;

import java.io.FileReader;
import java.io.FileNotFoundException;

public class BSU_Test {
    public static void main(String[] args) {
        BSU B = new BSU();
        FileReader file;
        try {
            file = new FileReader("E:\\IDEA Projects\\src\\MyPackage\\BSU\\RecordBook.txt");
            B.readData(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        B.work();
    }
}