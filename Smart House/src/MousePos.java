import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MousePos implements MouseMotionListener{

    private int mx=-1, my=-1;

    public int getMx() { return mx; }

    public int getMy() { return my; }

    @Override
    public void mouseDragged(MouseEvent e) {
        mx = e.getX(); my = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX(); my = e.getY();
    }
}
