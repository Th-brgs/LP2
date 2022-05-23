package figures;


import java.io.PrintWriter;
import java.io.Serializable;
import java.awt.*;
import ivisible.IVisible;

public abstract class Figure implements IVisible, Serializable {
	private boolean figfocus=false;
    public int x, y;
   // int p2x, p2y, p3x, p3y;
    public int w=100, h=50; //definindo comprimento e altura inicial
    public int intDrawColor, intFillColor, intDrawColorSemFocus; //inicializando cores para serialização
    public Color drawColor, fillColor; //definindo cores iniciais - branco e preto
    /* Color drawColor = Color.black, fillColor = Color.lightGray; //definindo cores iniciais - branco e preto  */
    Color drawColorSemFocus = Color.black; //definindo cor para Foco
    
    public Figure (int x, int y, int w, int h, Color drawColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.intDrawColor = 11;
        this.intFillColor = 10;
        this.intDrawColorSemFocus = 11;
    }
    
    public Figure  (int x, int y) {
    
    	 this.x = x;
    	 this.y = y;
         this.drawColor = Color.black;
         this.fillColor = Color.lightGray;
         this.intDrawColor = 11;
         this.intFillColor = 10;
         this.intDrawColorSemFocus = 11;    	 
    }
    
    public void AjustaFigura (int x, int y, int w, int h){
    	this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
   
    public void print () {
        System.out.format("figura de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public boolean clicked(int mousex, int mousey) {
        
    	return ((mousex >= x) && (mousey >= y)
                && ((mousex) <= (x + w))
                && ((mousey) <= (y + h)));
    }
    
    public void perdeFocus() {
     this.drawColor = drawColorSemFocus;	
     this.intDrawColor = intDrawColorSemFocus;
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
              intDrawColorSemFocus = this.intDrawColor;
              figfocus = true; // assumir que está com o foco
              this.changeCollor (9,0); //alterar a cor da borda
       }
    }

    public void changeCollor  (int numDrawColor, int numFillColor) {
    /*  0 - não realiza nenhum tratamento para o equivalente ao tipo
     *  1- cor de fundo Azul 4- cor de borda Azul
     *  2- cor de fundo Verde 5- cor de borda Verde
     *  3- cor de fundo Amarelo 6- cor de borda Laranja
     *  9- Vermelho para a cor de FOCUS
     *  10 - Cor de fundo light gray 11 - cor de borda preta
     */
 	 
 	 if (numFillColor == 1)
   	 	{ this.fillColor = Color.cyan; }
 	 
 	 if (numFillColor == 2)
 	 	{ this.fillColor = Color.pink; }
 	 
 	 if (numFillColor == 3)
   	 	{ this.fillColor = Color.yellow; }
 	 
 	if (numFillColor == 10) //cor inicial para FILL
	 	{ this.fillColor = Color.lightGray; }
	 
 	 if (numDrawColor == 4)
	 	{ this.drawColor = Color.blue;}
	 
	 if (numDrawColor == 5)
	 	{ this.drawColor = Color.magenta; }
	 
	 if (numDrawColor == 6)
	 	{ this.drawColor = Color.orange; }
 	 
 	 if (numDrawColor == 9)
	 	{ this.drawColor = Color.red; }
 	 
 	 if (numDrawColor == 11) //cor inicial para Draw
	 	{ this.drawColor = Color.black; }
 	 
     if ((numDrawColor >=4 && numDrawColor <=6 ) || (numDrawColor == 11)) {
    	 drawColorSemFocus = this.drawColor;
    	 intDrawColor = numDrawColor;
    	 intDrawColorSemFocus = this.intDrawColor;
     }
     //registrando as cores em int para serialização
     
     if (numFillColor != 0) {
    	 intFillColor = numFillColor;
     }

   }
   

    public void paint (Graphics g) {
    }
    
    public void paint (Graphics g, boolean focus) {
    }
    public void gravasvg(PrintWriter outfile) {
    	
    }
    
    protected String retornaCoremString  (Color c) {
        /*  0 - Draw Black e para fill - Gray
         *  1- cor de fundo Azul 4- cor de borda Azul
         *  2- cor de fundo Verde 5- cor de borda Verde
         *  3- cor de fundo Amarelo 6- cor de borda Laranja
         *  9- Vermelho para a cor de FOCUS
         */
     	 
     	 if (c == Color.cyan) {
       	    return "cyan";
       	 	  }
     	 else { 
     	      if (c == Color.pink) {
     	    	  return "pink";
     	      }
     	      else {
     	    	  if (c == Color.yellow) {
     	    		  return "yellow";
     	    	  }
     	    	  else
     	    		  if (c == Color.lightGray) {
     	    			  return "lightGray";
     	    		  }
     	    		  else {
     	    			  if (c == Color.blue) {
     	    				  return "blue";
     	    			  }
     	    			  else {
     	    				  if (c == Color.magenta) {
     	    					  return "magenta";
     	    				  }
     	    				  else {
     	    					  if (c == Color.orange) {
     	    						  return "orange";
     	    					  }
     	    					  else {
     	    						  if (c == Color.black) {
     	    							  return "black";
     	    						  }
     	    						  else {	  
     	    							  if (c == Color.red) {
     	    								  return retornaCoremString(drawColorSemFocus);
     	    							  }
     	    							  else {
     	    								  return "grey";
     	    							  }
     	    						  }
     	    					  }
     	    				  }
     	    			  }
     	    		  }
     	      }
     	 }
    
     	 } 
    }
       
