import sun.applet.Main;

import javax.swing.*;

/**
 * Created by janka on 31.10.2014.
 */

import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class MainFrame extends JFrame
{
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private ArrayList list = new ArrayList();

    public MainFrame() throws HeadlessException
    {
        super("Кінуць спасылку на JavaDoc Віце!");
        setSize(WIDTH, HEIGHT);
        // Toolkit вызначае памер экрана.
        setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().width/2 - WIDTH/2),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height/2 - HEIGHT/2));
        setTitle("Test Frame");
        Container content = getContentPane();
        TestPanel tp = new TestPanel(list, this);
        content.add(tp);
    }

    public void addPoint(Point2D p)
    {
        list.add(p);
    }
}