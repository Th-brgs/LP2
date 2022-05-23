package figures;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.PrintWriter;

public class Line extends Figure {
	int p1x, p1y, p2x, p2y, mreta;
	double area, s, t;

	protected Line (int x, int y, int w, int h, Color drawColor, Color fillColor) {
		super(x, y, w, h, drawColor, fillColor);
		pontosLine(x, y, w, h);
	}

	public Line(int x, int y) {
		super (x, y);
		//w = 50;
		//h = 100;
		pontosLine(x, y, w, h);
		mreta = h/w;

	}

	public void print () {
		System.out.format("line");
	}

	private void pontosLine (int x, int y, int w, int h) {
		this.p1x = x;
		this.p1y = y;
		this.p2x = x + w;
		this.p2y = y - h;
	}

	public boolean clicked(int mousex, int mousey) {
		double dist;
		dist = Math.abs((p2x - p1x) * (p1y - mousey)- (p1x - mousex) * (p2y - p1y))/ Math.sqrt(Math.pow(p2x - p1x, 2) + Math.pow(p2y - p1y, 2));
		if (dist<5) {
			return true;
		}
		else {
			return false;
		}
	}

	public void perdeFocus() {
		super.perdeFocus();
	}

	public void move(int velx, int vely) {
		super.move(velx, vely);
		this.p1x = x;
		this.p1y = y;
		this.p2x = this.p2x + velx;
		this.p2y = this.p2y + vely;
	}

	public void dragged(int posx, int posy) {
		super.dragged(posx, posy);
		this.p1x = x;
		this.p1y = y;
		this.p2x = this.p2x + posx;
		this.p2y = this.p2y + posy;
	}

	public void resize(int amount) {
		super.resize((amount * (mreta + 1)));
		pontosLine(x, y, w, h);
	}

	public void growFigure() {
		super.growFigure();
		pontosLine(x, y, w, h);
	}

	public void shrink() {
		super.shrink();
		pontosLine(x, y, w, h);
	}


	public void recebeFocus() {
		super.recebeFocus();
	}
	
	 public void AjustaFigura (int x, int y, int w, int h){
		 this.x = x;
	     this.y = y+h;
	     this.w = w;
	     this.h = h;
		 pontosLine(this.x, this.y, this.w, this.h); 	
	 }

	public void changeCollor  (int numDrawColor, int numFillColor) {
		/*  0 - Draw Black e para fill - Gray
		 *  1- cor de fundo Ciano 4- cor de borda Azul
		 *  2- cor de fundo Rosa 5- cor de borda Magenta
		 *  3- cor de fundo Amarelo 6- cor de borda Laranja
		 *  9- Vermelho para a cor de FOCUS
		 */

		super.changeCollor(numDrawColor, numFillColor);
	}

	public void paint (Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(this.drawColor);
		g2d.draw(new Line2D.Double(this.p1x,this.p1y, this.p2x,this.p2y));
		g2d.setColor(this.fillColor);

	}
	
	public void gravasvg(PrintWriter outfile) {
    	String linha;
    	outfile.println("<svg>");
       	linha = "   <line x1=\"" + Integer.toString(p1x) + "\"  y1=\"" + Integer.toString(p1y) + "\" x2=\"" + Integer.toString(p2x) + "\" y2=\"" + Integer.toString(p2y) + "\" " ;
    	outfile.println(linha);
      	linha = "   style=fill:" + retornaCoremString(this.fillColor) + " stroke-width:1; stroke=" + retornaCoremString(this.drawColor) + " />"; 

      	outfile.println(linha);
 
    	outfile.println("</svg>");
    }
}