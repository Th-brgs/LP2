package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figure {
	boolean elipsefocus = false;
	
	public Ellipse (int x, int y, int w, int h, Color drawColor, Color fillColor) {
    	super(x, y, w, h, drawColor, fillColor);
    }
    
    public Ellipse  (int x, int y) {
    	super (x, y);
    }
   
    public void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            w, h, x, y);
    }

    public boolean contemMouse(int mousex, int mousey) {        
    	return super.contemMouse(mousex, mousey);
    }
    
    public void perdeFocus() {
     super.perdeFocus();	
     elipsefocus = false;
    }
    
    public void move(int velx, int vely) {
        super.move(velx, vely);
       }
    
    public void dragged(int posx, int posy) {
        super.dragged(posx, posy);
       }
    
    public void resize(int amount) {
        super.resize(amount);
       }

    public void growFigure() {
        super.growFigure();
       }
    
    public void shrink() {
        super.shrink();
       }

    
    public void recebeFocus() {
    	super.recebeFocus();
    	elipsefocus = true;
     
    }

    public void changeCollor  (int numDrawColor, int numFillColor) {
    /*  0 - Draw Black e para fill - Gray
     *  1- cor de fundo Azul 4- cor de borda Azul
     *  2- cor de fundo Verde 5- cor de borda Verde
     *  3- cor de fundo Amarelo 6- cor de borda Amarelo
     *  9- Vermelho para a cor de FOCUS
     */
 	 
 	 super.changeCollor(numDrawColor, numFillColor);
   }
   

    public void paint (Graphics g) {
    	super.paint(g);
    	Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.drawColor);
        g2d.drawOval(x, y, w, h);
        g2d.drawOval(x-1, y-1, w+1, h+1);
        g2d.setColor(this.fillColor);
        g2d.fillOval(x, y, w-1, h-1);
    }
}