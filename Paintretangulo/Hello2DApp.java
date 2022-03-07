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
    void drawRectangle1(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.YELLOW);
        g2d.drawRect(205,205,40,40);
	g2d.fillRect(205,205,40,40);
    }
    void drawRectangle2(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.GREEN);
        g2d.drawRect(10,120,20,90);
	g2d.fillRect(10,120,20,90);
    }
    void drawRectangle3(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.BLACK);
        g2d.drawRect(112,52,90,20);
	g2d.fillRect(112,52,90,20);
    }
    public void paint (Graphics g) {
        super.paint(g);
	drawRectangle1(g);
	drawRectangle2(g);
	drawRectangle3(g);
    }

}
