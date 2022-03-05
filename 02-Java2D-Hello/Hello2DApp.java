import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(450, 450);
	this.getContentPane().setBackground(Color.red);
        this.setVisible(true);
    }

    void drawLine (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.blue);
        int w = getWidth();
        int h = getHeight();
        g2d.drawLine(0,0, w,h);
        g2d.drawLine(0,h, w,0);
    }
    void drawRectangles(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // code to draw rectangles goes here...
	g2d.setColor(Color.YELLOW);
        g2d.drawRect(205,205,40,40);
	g2d.fillRect(205,205,40,40);
    }
    public void paint (Graphics g) {
        super.paint(g);
	drawRectangles(g);
	drawLine(g);
    }

}
