package figures;

import java.awt.*;

public class Rect extends Figure {
	private boolean rectfocus=false;
    //private int x, y;
    //private int w=100, h=50; //definindo comprimento e altura inicial
    //Color drawColor = Color.black, fillColor = Color.gray; //definindo cores iniciais - branco e preto
    //Color drawColorSemFocus = Color.black; //definindo cor para Foco
    
    public Rect (int x, int y, int w, int h, Color drawColor, Color fillColor) {
    	super(x, y, w, h, drawColor, fillColor);
    }
    
    public Rect  (int x, int y) {
    	super (x, y);
    
    }
   
    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            w, h, x, y);
    }

    public boolean clicked(int mousex, int mousey) {
        
    	return super.clicked(mousex, mousey);
    }
    
    public void perdeFocus() {
     super.perdeFocus();	
     rectfocus = false;
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
    	rectfocus = true;
     
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
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(this.fillColor);
        g2d.fillRect(this.x+1, this.y+1, this.w-1, this.h-1);
    }
}