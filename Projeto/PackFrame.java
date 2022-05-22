import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
//import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintWriter;

import figures.*;

public class PackFrame extends JFrame implements MouseListener, KeyListener{  
	// trata Lista de Figuras 
	ArrayList<Figure> figList = new ArrayList<Figure>();
	//para tratar Focus
	Figure Focus = null; 
	// trata lista de botoes
	ArrayList<Button> buttonList = new ArrayList<Button>();
	//para tratar BTfocus
	Button BTfocus = null; 
	int mousex = 0, mousey = 0; //para identificar a posição do click do mouse
	int specialKey; // para identificar uma tecla especial
	int velx, vely; // para mover as figuras com as setas
	
	PackFrame(){  
		addMouseListener(this); 
		addKeyListener(this);
		addWindowListener (
				new WindowAdapter() {
					public void windowActivated(WindowEvent e) {
						//recupera lista de figuras de arquivo previamente salvo, se tiver
						recuperalista();
					}
					public void windowClosing (WindowEvent e) {
						try {
							//salva lista de figuras trabalhadas
							if (Focus != null) {
								Focus.perdeFocus();
							}
							salvarlista();
							salvarSVG();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						System.exit(0);
					}
					
					
				}
				); 

		//Adicionado para tratar o dragged de figuras com o mouse
		addMouseMotionListener (
				new MouseAdapter() {
					public void mouseDragged (MouseEvent e) {
						int dx , dy;
						dx = e.getX() - mousex;
						dy = e.getY() - mousey; 
						if (Focus != null) {
							if (Focus.clicked(mousex, mousey)) {
								Focus.dragged(dx, dy);
								repaint();
							} 
						}    
						mousex += dx;
						mousey += dy;
					}
				}
				); 

		//Adicionado para tratar o dragged de figuras com o mouse
		addMouseWheelListener (
				new MouseWheelListener() {
					public void mouseWheelMoved (MouseWheelEvent e) {
						if (Focus != null) {
							float amount = e.getWheelRotation() * 5f;
							Focus.resize((int) amount);

							repaint();
						}
					}
				}
				);   
		setSize(800,600);  
		setLayout(null);  
		this.setTitle("Packages Figures");
		this.setFocusTraversalKeysEnabled(false);
		criabotoes();
		setVisible(true);  
	}  
	
	private void criabotoes() {

		Figure fig_n;
		Button butap;
		
		fig_n = new Rect(2, 2);
		butap = new Button(1, fig_n);
		buttonList.add(butap);
		
		fig_n = new Ellipse(2, 2);
		butap = new Button(2, fig_n);
		buttonList.add(butap);
		
		fig_n = new Triangle(2, 2);
		butap = new Button(3, fig_n);
		buttonList.add(butap);
		
		fig_n = new Line(2, 2);
		butap = new Button(4, fig_n);
		buttonList.add(butap);
		
		fig_n = new Rect(2, 2);
		fig_n.changeCollor(0, 1);
		butap = new Button(5, fig_n);
		buttonList.add(butap);
		
		fig_n = new Rect(2, 2);
		fig_n.changeCollor(0, 2);
		butap = new Button(6, fig_n);
		buttonList.add(butap);
		
		fig_n = new Rect(2, 2);
		fig_n.changeCollor(0, 3);
		butap = new Button(7, fig_n);
		buttonList.add(butap);
		
		fig_n = new Rect(2, 2);
		fig_n.changeCollor(4, 0);
		butap = new Button(8, fig_n);
		buttonList.add(butap);
		
		fig_n = new Rect(2, 2);
		fig_n.changeCollor(5, 0);
		butap = new Button(9, fig_n);
		buttonList.add(butap);
		
		fig_n = new Rect(2, 2);
		fig_n.changeCollor(6, 0);
		butap = new Button(10, fig_n);
		buttonList.add(butap);
		
		fig_n = new Ellipse(2, 2);
		fig_n.changeCollor(9, 9);
		butap = new Button(11, fig_n);
		buttonList.add(butap);
		
	}

	public void mouseClicked(MouseEvent e) {  
		int i, n;
		Figure figAp; // definindo uma figura de apoio
		Figure newFigFocus = null;
		Button butAp; // definindo um botao de apoio
		Button newBTFocus = null;

		//Capturando a posição do click do mouse para uso futuro
		mousex = e.getX();
		mousey = e.getY();

		//Verificando se o click foi sobre uma figura na tela
		n = figList.size();
		for (i=0; i<n; i++) {
			figAp = figList.get(i);
			if (figAp.clicked(mousex, mousey)) {
				newFigFocus = figAp;     	   
			};
		};
		
		//Verificando se o click foi sobre um botao na tela
		n = buttonList.size();
		for (i=0; i<n; i++) {
			butAp = buttonList.get(i);
			if (butAp.clicked(mousex, mousey)) {
				newBTFocus = butAp;     	   
			};
		};

		// Caso seja numa figura, retirar o focus da anterior - se houver - e passar para a nova

		if (newFigFocus != null) {
			trataFiguraEmFoco (newFigFocus);
		}

			// Caso seja num botao, retirar o focus do anterior - se houver - e passar para o novo

		if (newBTFocus != null) {
			trataBotaoEmFoco (newBTFocus);
		}
	
		
		if ((BTfocus != null) && (BTfocus.idx == 11)) {
			BTfocus.perdeFocus();
			BTfocus = null;
			repaint(); 
		}
		
		// tratando criação de figuras
		if ((newFigFocus == null) && (newBTFocus == null)) {
			if (BTfocus != null) {
				if (BTfocus.idx == 1) {
					criaRetangulo();
				}
				if (BTfocus.idx == 2) {
					criaEllipse();
				}
				if (BTfocus.idx == 3) {
					criaTriangulo();
				}
				if (BTfocus.idx == 4) {
					criaLinha();
				}
			}	
		}
		
		// tratando ajustes de cores
		if ((newFigFocus != null) && (BTfocus != null)) {
			
			if ((BTfocus.idx >= 5) && (BTfocus.idx <= 7)){
				trocaColorFill (BTfocus.idx - 4);
			}

			if ((BTfocus.idx >= 8) && (BTfocus.idx <= 10)){
				trocaColorDraw (BTfocus.idx - 4);
			}
		}
	}  

	public void mouseEntered(MouseEvent e) {
	}  

	public void mouseExited(MouseEvent e) {  
	}  

	public void mousePressed(MouseEvent e) {
		mouseClicked(e);
	}  
	public void mouseReleased(MouseEvent e) {
	}

	/** Handle the key typed event from the text field. */
	public void keyTyped(KeyEvent e) {
		int n;
		char letra;
		Figure fig_n;
		letra = e.getKeyChar();
		specialKey = e.getKeyCode();
		
		// Tratando criação de figuras

		if (Character.compare(letra, 'r') == 0) {
			criaRetangulo();
		}

		if (Character.compare(letra, 'e') == 0) {
			criaEllipse();
		}

		if (Character.compare(letra, 't') == 0) {
			criaTriangulo();
		}

		if (Character.compare(letra, 'l') == 0) {
			criaLinha();
		}

		
		if (Focus != null) {
			// Tratando FILL da Figura
			if (Character.compare(letra, '1') == 0|| Character.compare(letra, '2') == 0|| Character.compare(letra, '3') == 0){
				trocaColorFill (Character.getNumericValue(letra));

			}

			// Tratando Draw da Figura
			if (Character.compare(letra, '4') == 0|| Character.compare(letra, '5') == 0|| Character.compare(letra, '6') == 0){
				trocaColorDraw (Character.getNumericValue(letra));
			}

			if (Character.compare(letra, '+') == 0){
				Focus.growFigure();
				repaint();
			}

			if (Character.compare(letra, '-') == 0){
				Focus.shrink();
				repaint();
			}

			if (e.getKeyChar() == KeyEvent.VK_TAB) {
				n = figList.size();
				if ( n > 0) { 
					fig_n = figList.get(0);
					trataFiguraEmFoco (fig_n);
				}
			}
		}
	}
	
	
	
	
	public void criaRetangulo (){
		Rect rect_n;
		rect_n = new Rect(mousex, mousey); 
		figList.add(rect_n);
		trataFiguraEmFoco (rect_n);
	}
	
	public void criaEllipse (){
		Ellipse elipse_n;
		elipse_n = new Ellipse(mousex, mousey); 
		figList.add(elipse_n);
		trataFiguraEmFoco (elipse_n);
	}
	
	public void criaTriangulo (){
		Triangle tri_n;
		tri_n = new Triangle(mousex, mousey); 
		figList.add(tri_n);
		trataFiguraEmFoco (tri_n);
	}
	
	public void criaLinha (){
		Line line_n;
		line_n = new Line(mousex, mousey); 
		figList.add(line_n);
		trataFiguraEmFoco (line_n);
	}

	public void keyPressed(KeyEvent e) {
		specialKey = e.getKeyCode();

		if (Focus != null) {
			if (specialKey == KeyEvent.VK_DELETE) {
				remove (Focus);
				specialKey = 0;
				repaint();
			}

			if ( (specialKey == KeyEvent.VK_LEFT) || (specialKey == KeyEvent.VK_RIGHT) || (specialKey == KeyEvent.VK_UP) || (specialKey == KeyEvent.VK_DOWN)) {

				if (specialKey == KeyEvent.VK_LEFT) {
					velx = -1;
					vely = 0;
				}

				if (specialKey == KeyEvent.VK_RIGHT) {
					velx = 1;
					vely = 0;
				}

				if (specialKey == KeyEvent.VK_UP) {
					velx = 0;
					vely = -1;
				}

				if (specialKey == KeyEvent.VK_DOWN) {
					velx = 0;
					vely = 1;
				}

				Focus.move(velx, vely);
				repaint();
			}

		}
	}   
	/** Handle the key released event from the text field. */
	public void keyReleased(KeyEvent e) {
	}

	public void paint (Graphics g) {
		super.paint(g);

		Button butAp; // definindo um Botao de apoio
		Figure figAp; // definindo uma Figura de apoio
		int i, n;
		n = figList.size();
		for (i=0; i<n; i++) {
			figAp = figList.get(i);
			figAp.paint(g);
		};
		
	
		n = buttonList.size();
		for (i=0; i<n; i++) {
			butAp = buttonList.get(i);
			butAp.paint(g, butAp.focus);
		};
	}

	public void remove (Figure f) {
		Figure figAp; // definindo uma figura de apoio
		int i = 0, n;
		n = figList.size();
		while (i<n) {
			figAp = figList.get(i); 
			if (figAp == f) {
				figList.remove(i);
				i = n;
			}
			i++;
		};
	}

	//Responsavel por ajusta a figura em Foco
	public void trataFiguraEmFoco (Figure f) {

		if ( Focus != null ) {
			Focus.perdeFocus();}

		Focus = f;
		Focus.recebeFocus();
		figList.remove(f); //volta um false quando f é uma nova figura
		figList.add(f);
		repaint();
	}

	//Responsavel por ajusta o botao em Foco
		public void trataBotaoEmFoco (Button b) {

			if ( BTfocus != null ) {
				BTfocus.perdeFocus();}

			BTfocus = b;
			BTfocus.recebeFocus();
			repaint();
		}
		
	public void trocaColorFill (Integer c) {
		Focus.changeCollor(0, c);
		repaint();
	}
	
	public void trocaColorDraw (Integer c) {
		Focus.perdeFocus();
		Focus.changeCollor(c, 0 );
		Focus = null;
		repaint();
	}
	
	public void salvarSVG() throws Exception 
    {
		Figure figAp; // definindo uma Figura de apoio
		int i, n;
 
        try (PrintWriter out = new PrintWriter("Figuras.svg")) { 
            //FileOutputStream fos = new FileOutputStream("Figuras.svg");
            //OutputStream oos = new OutputStream(fos);
        	out.println("<html>");
        	out.println("<body>");
        	out.println("<svg width=\"800\" height=\"600\" xmlns=\"http://www.w3.org/2000/svg\">\r\n"
     			+ " ");
    		n = figList.size();
    		for (i=0; i<n; i++) {
    			figAp = figList.get(i);
    			figAp.gravasvg(out);
    		};
    		
        	out.println("</svg>");
        	out.println("</body>");
        	out.println("</html>");
            out.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
	
	//Salvar listas de figura em um arquivo <listData.bin>
	public void salvarlista() throws Exception 
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("listData.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(figList);
            oos.close();
            fos.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        }
    }
	
	//Recupera lista de figura de um arquivo <listData.bin>
	public void recuperalista() {
		try
		  {
		  FileInputStream fin=new FileInputStream("listData.bin");
		  ObjectInputStream oin=new ObjectInputStream(fin);
		 
		  figList = (ArrayList) oin.readObject();

		  oin.close(); 
		  fin.close();
		  Figure figAp;
		  
		  //Tratando o problema da falta de serialização do tipo Color
		  int i, n = figList.size();
  		  for (i=0; i<n; i++) {
  			figAp = figList.get(i);
  			figAp.changeCollor(figAp.intDrawColor, figAp.intFillColor);
  		    System.out.print(figAp.retornaCoremString (figAp.drawColor)); 
		    }
		  
		  }
  		  catch(Exception e){}
	}
	
	public static void main(String[] args) {  
		new PackFrame();  
	}  
	
	
	
}  
