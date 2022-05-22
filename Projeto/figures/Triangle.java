package figures;

import java.awt.*;
import java.io.PrintWriter;

public class Triangle extends Figure {
    
    int p1x, p1y, p2x, p2y, p3x, p3y; 
    double area, s, t;
    
    public Triangle (int x, int y, int w, int h, Color drawColor, Color fillColor) {
    	super(x, y, w, h, drawColor, fillColor);
        pontosTriangulo(x, y, w, h);
    }

    public Triangle(int x, int y) {
    	super (x, y);
    	pontosTriangulo(x, y, w, h);
    
    }
   
    public void print () {
        System.out.format("triangulo");
    }
    
    public void pontosTriangulo (int x, int y, int w, int h) {
    	this.p1x = x;
        this.p1y = y;
        this.p2x = x + w;
        this.p2y = y;
        this.p3x = x + w/2;
        this.p3y = y - h;
         area = 0.5 *(-p2y*p3x + p1y*(-p2x + p3x) + p1x*(p2y - p3y) + p2x*p3y);
    }

    public boolean clicked(int mousex, int mousey) {
        
    	//return super.clicked(mousex, mousey);
    	s = 1/(2*area)*(p1y*p3x - p1x*p3y + (p3y - p1y)*mousex + (p1x - p3x)*mousey);
        t = 1/(2*area)*(p1x*p2y - p1y*p2x + (p1y - p2y)*mousex + (p2x - p1x)*mousey);
        if (s>0 && t>0 && 1-s-t>0) {
        	return true;
        }
        else {
        	if ((mousex == p1x) && (mousey== p1y)) {
        		return true;
        	}
        	else
               return false;
         }
    }
    
    public void perdeFocus() {
     super.perdeFocus();
    }
    
    public void move(int velx, int vely) {
        super.move(velx, vely);
        pontosTriangulo(x, y, w, h);
       }
    
    public void dragged(int posx, int posy) {
        super.dragged(posx, posy);
        pontosTriangulo(x, y, w, h);
       }
    
    public void resize(int amount) {
        super.resize(amount);
        pontosTriangulo(x, y, w, h);
       }

    public void growFigure() {
        super.growFigure();
        pontosTriangulo(x, y, w, h);
       }
    
    public void shrink() {
        super.shrink();
        pontosTriangulo(x, y, w, h);
       }

    
    public void recebeFocus() {
    	super.recebeFocus();
    }
    
    public void AjustaFigura (int x, int y, int w, int h){
    	this.x = x;
        this.y = y+h;
        this.w = w;
        this.h = h;
        pontosTriangulo(this.x, this.y, this.w, this.h);
        
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
        g2d.drawPolygon(new int[]{this.p1x,this.p2x,this.p3x}, new int[]{p1y,p2y,p3y}, 3);
        g2d.setColor(this.fillColor);
        g2d.fillPolygon(new int[]{this.p1x+1,this.p2x,this.p3x}, new int[]{p1y,p2y,p3y+1}, 3);
        
    }
    
    public void gravasvg(PrintWriter outfile) {
    	String linha;
    	outfile.println("<svg>");
       	linha = "   <polygon points=\"" + Integer.toString(p1x) + "," + Integer.toString(p1y) + " " + Integer.toString(p2x) + "," + Integer.toString(p2y) + " " + Integer.toString(p3x) + "," + Integer.toString(p3y) + "\" " ;
    	outfile.println(linha);
      	linha = "   style=fill:" + retornaCoremString(this.fillColor) + " stroke-width:1; stroke=" + retornaCoremString(this.drawColor) + " />"; 

      	outfile.println(linha);
 
    	outfile.println("</svg>");
    }
}