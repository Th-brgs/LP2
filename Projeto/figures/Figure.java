package figures;

import java.awt.*;

public class Figure {
	private boolean figfocus=false;
    int x, y;
   // int p2x, p2y, p3x, p3y;
    int w=100, h=50; //definindo comprimento e altura inicial
    Color drawColor = Color.black, fillColor = Color.lightGray; //definindo cores iniciais - branco e preto
    Color drawColorSemFocus = Color.black; //definindo cor para Foco
    
    public Figure (int x, int y, int w, int h, Color drawColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.drawColor = drawColor;
        this.fillColor = fillColor;
    }
    
    public Figure  (int x, int y) {
    
    	 this.x = x;
    	 this.y = y;
    	 
    }
    
   
    public void print () {
        System.out.format("figura de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public boolean contemMouse(int mousex, int mousey) {
        
    	return ((mousex >= x) && (mousey >= y)
                && ((mousex) <= (x + w))
                && ((mousey) <= (y + h)));
    }
    
    public void perdeFocus() {
     this.drawColor = drawColorSemFocus;	
     figfocus = false;
    }
    
    public void move(int velx, int vely) {
        this.x = this.x + velx;
        this.y = this.y + vely;
       }
    
    public void dragged(int posx, int posy) {
        this.x = this.x + posx;
        this.y = this.y + posy;
       }
    
    public void resize(int amount) {
        this.w = this.w - amount;
        this.h = this.h - amount;
       }

    public void growFigure() {
        this.w = this.w * 2;
        this.h = this.h * 2;
       }
    
    public void shrink() {
        this.w = this.w / 2;
        this.h = this.h / 2;
       }


    
    public void recebeFocus() {
       if ( figfocus == true ) { return; }
       else {
              drawColorSemFocus = this.drawColor;//salvar a cor de borda	
              figfocus = true; // assumir que esta com o foco
              this.changeCollor (9,0); //alterar a cor da borda
       }
    }

    public void changeCollor  (int numDrawColor, int numFillColor) {
    /*  0 - Draw Black e para fill - Gray
     *  1- cor de fundo Azul 4- cor de borda Azul
     *  2- cor de fundo Verde 5- cor de borda Verde
     *  3- cor de fundo Amarelo 6- cor de borda Laranja
     *  9- Vermelho para a cor de FOCUS
     */
 	 
 	 if (numFillColor == 1)
   	 	{ this.fillColor = Color.cyan; }
 	 
 	 if (numFillColor == 2)
 	 	{ this.fillColor = Color.pink; }
 	 
 	 if (numFillColor == 3)
   	 	{ this.fillColor = Color.yellow; }
 	 
 	 if (numDrawColor == 4)
	 	{ this.drawColor = Color.blue;}
	 
	 if (numDrawColor == 5)
	 	{ this.drawColor = Color.magenta; }
	 
	 if (numDrawColor == 6)
	 	{ this.drawColor = Color.orange; }
 	 
 	 if (numDrawColor == 9)
	 	{ this.drawColor = Color.red; }
 	 
     if (numDrawColor >=4 && numDrawColor <=6 ) {
    	 drawColorSemFocus = this.drawColor;
     }
   }
   

    public void paint (Graphics g) {
    }
}
