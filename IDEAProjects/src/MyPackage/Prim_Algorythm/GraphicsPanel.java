package MyPackage.Prim_Algorythm;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GraphicsPanel extends JPanel {
    private Graph G;
    public GraphicsPanel() throws IOException {
        G = new Graph();
        G.readData();
        G.PrimAlgorythm();
        G.createPoints();
        PrintWriter out = new PrintWriter(new File("E:\\IDEA Projects\\src\\MyPackage\\Prim_Algorythm\\Output.txt").getAbsoluteFile());
        try {
            out.println("Edges of tree:");
            for (int i = 0; i < G.getCount() - 1; i++)
                out.println(G.start[i] + " -> " + G.finish[i]);
        }
        finally {
            out.close();
        }
    }
    public void paint(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setPaintMode();
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2.2f));
        G.PrimAlgorythm();
        G.createPoints();
        MyPair mp;
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(new Color(0, 0, 0));
        for (int i = 0; i < G.getCount(); i++)
            for (int k = 0; k < G.pairs[i].size(); k++) {
                mp = ((MyPair)(G.pairs[i].get(k)));
                g.drawLine((int)G.getXCoord(i) + 5, (int)G.getYCoord(i) + 25,
                        (int)G.getXCoord(mp.getNumberOfVertex()) + 5, (int)G.getYCoord(mp.getNumberOfVertex()) + 25);
                g.drawString(Integer.toString(mp.getWeight()), (int)((G.getXCoord(i) + G.getXCoord(mp.getNumberOfVertex()) + 10) / 2),
                        (int)((G.getYCoord(i) + G.getYCoord(mp.getNumberOfVertex()) + 40)) / 2);
            }
        g.setFont(new Font("Arial", Font.BOLD, 16));
        showGraph(g, g2d);
        g.drawString("The weight of tree is " + Integer.toString(G.getResultWeight()) + ".", 330, 490);
    }
    @Override
    public void paintComponent(Graphics g) {
        paint(g);
    }
    public void showGraph(Graphics g, Graphics2D g2d) {
        G.PrimAlgorythm();
        g2d.setStroke(new BasicStroke(2.0f));
        g.setColor(new Color(255, 0, 0));
        g.drawLine((int)G.getXCoord(G.start[0]) + 5, (int)G.getYCoord(G.start[0]) + 25,
                (int)G.getXCoord(G.finish[0]) + 5, (int)G.getYCoord(G.finish[0]) + 25);
        for (int i = 1; i < G.getCount() - 1; i++)
            g.drawLine((int)G.getXCoord(G.start[i]) + 5, (int)G.getYCoord(G.start[i]) + 25,
                    (int)G.getXCoord(G.finish[i]) + 5, (int)G.getYCoord(G.finish[i]) + 25);
        for (int i = 0; i < G.getCount(); i++) {
            g.setColor(new Color(253, 233, 16));
            g.fillOval((int)G.getXCoord(i) - 10, (int)G.getYCoord(i) + 5, 35, 35);
            g.setColor(new Color(0, 0, 0));
            g.drawString(Integer.toString(i + 1), (int)G.getXCoord(i) + 4, (int)G.getYCoord(i) + 30);
        }
    }
}