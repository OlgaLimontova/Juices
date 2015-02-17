package MyPackage.Prim_Algorythm;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.Comparator;

public class WeightComp implements Comparator{
    @Override
    public int compare(Object o1, Object o2) {
        return ((MyPair)o1).getWeight() - ((MyPair)o2).getWeight();
    }
}

class MyPair {
    private int number; // номер вершины
    private int weight; // вес ребра, входящего в неё
    public MyPair(int number, int weight) {
        this.number = number;
        this.weight = weight;
    }
    public void setNumberOfVertex(int number) {
        this.number = number;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getNumberOfVertex(){
        return number;
    }
    public int getWeight() {
        return weight;
    }
}

class MyPrimFrame extends JFrame {
    private final int width = 550;
    private final int height = 550;
    GraphicsPanel panel;
    public MyPrimFrame() throws /*HeadlessException,*/ IOException {
        super();
        createPanel();
    }
    public void createPanel() throws IOException {
        String title = "Prim algorythm";
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(null);
        panel = new GraphicsPanel();
        panel.setBounds(0, 0, 550, 550);
        add(panel);
        getContentPane().setLayout(null);
        setVisible(true);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}