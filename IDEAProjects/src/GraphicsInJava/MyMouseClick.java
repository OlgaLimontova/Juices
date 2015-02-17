/**
 * Created by janka on 31.10.2014.
 */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

// У адаптэрах прапісваюцца заглушкі для метадаў,
// якія мы не хочам рэалізоўваць у інтэрфейсе.

public class MyMouseClick extends MouseAdapter
{
    private MainFrame frame;
    public MyMouseClick(MainFrame frame)
    {
        super();
        this.frame = frame;
    }

    public void mouseClicked(MouseEvent e)
    {
        super.mouseClicked(e);
        frame.addPoint(new Point2D.Float((float)e.getX(),(float)e.getY()));
        frame.repaint();
    }
}