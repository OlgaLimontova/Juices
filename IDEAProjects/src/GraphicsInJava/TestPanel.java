/**
 * Created by janka on 31.10.2014.
 */
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JPanel;

class TestPanel extends JPanel
{
    public TestPanel(ArrayList alist, MainFrame frame)
    {
        super();
        list = alist;
        //this.frame = frame; // TODO: Праверыць варыянт адсутнасці frame як поля
        addMouseListener(new MyMouseClick(frame));
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawString("Hello, World", 100, 100);
        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < list.size(); ++i)
        {
            g2.setColor(Color.BLACK);
            int x = (int)((Point2D)(list.get(i))).getX();
            int y = (int)((Point2D)(list.get(i))).getY();
            g.drawRect(x-1, y-1, 3, 3);
        }
    }

    private ArrayList list;
    //private MainFrame frame;
}
