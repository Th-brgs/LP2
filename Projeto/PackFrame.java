import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
//import javax.swing.*;

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
					public void windowClosing (WindowEvent e) {
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
		setVisible(true);  
	}  
	
	private void criabotoes() {
		//int n=0;
		Figure fig_n;
		/*Rect rect_n;
		Ellipse elipse_n;
		Triangle tri_n;
		Line line_n;*/
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

		// Caso seja numa figura, retirar o focus da anterior - se houver - e passar para a nova

		if (newFigFocus != null) {
			trataFiguraEmFoco (newFigFocus);
		}
		else {
			//Verificando se o click foi sobre um botao na tela
			n = buttonList.size();
			for (i=0; i<n; i++) {
				butAp = buttonList.get(i);
				if (butAp.clicked(mousex, mousey)) {
					newBTFocus = butAp;     	   
				};
			};

			// Caso seja num botao, retirar o focus do anterior - se houver - e passar para o novo

			if (newBTFocus != null) {
				trataBotaoEmFoco (newBTFocus);
			}
		}
		
		if ((newFigFocus == null) && (newBTFocus == null)) {
			if (BTfocus != null) {
				if (BTfocus.idx == 1) {
					criaRetangulo();
				}
				if (BTfocus.idx == 2) {
					criaRetangulo();
				}
				if (BTfocus.idx == 3) {
					criaRetangulo();
				}
				if (BTfocus.idx == 4) {
					criaRetangulo();
				}
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
		Rect rect_n;
		Ellipse elipse_n;
		Triangle tri_n;
		Line line_n;
		letra = e.getKeyChar();
		specialKey = e.getKeyCode();

		if (Character.compare(letra, 'r') == 0) {
			criaRetangulo();
		}

		if (Character.compare(letra, 'e') == 0) {
			elipse_n = new Ellipse(mousex, mousey); //, 200,130, Color.red, Color.yellow);
			figList.add(elipse_n);
			trataFiguraEmFoco (elipse_n);
		}

		if (Character.compare(letra, 't') == 0) {
			tri_n = new Triangle(mousex, mousey); //, 200,130, Color.red, Color.yellow);
			figList.add(tri_n);
			trataFiguraEmFoco (tri_n);
		}

		if (Character.compare(letra, 'l') == 0) {
			line_n = new Line(mousex, mousey); //, 200,130, Color.red, Color.yellow);
			figList.add(line_n);
			trataFiguraEmFoco (line_n);
		}

		if (Focus != null) {
			if (Character.compare(letra, '1') == 0|| Character.compare(letra, '2') == 0|| Character.compare(letra, '3') == 0){
				Focus.changeCollor(0, Character.getNumericValue(letra));
				repaint();

			}

			if (Character.compare(letra, '4') == 0|| Character.compare(letra, '5') == 0|| Character.compare(letra, '6') == 0){
				Focus.changeCollor(Character.getNumericValue(letra), 0 );
				Focus.perdeFocus();
				Focus = null;
				repaint();

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
		rect_n = new Rect(mousex, mousey); //, 200,130, Color.red, Color.yellow);
		figList.add(rect_n);
		trataFiguraEmFoco (rect_n);
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

		Figure figAp; // definindo um retângulo de apoio
		int i, n;
		n = figList.size();
		for (i=0; i<n; i++) {
			figAp = figList.get(i); // ou  Rect(figList.get(I)).paint(g);
			figAp.paint(g);
		};
	}

	public void remove (Figure f) {
		Figure figAp; // definindo um retângulo de apoio
		int i = 0, n;
		n = figList.size();
		while (i<n) {
			figAp = figList.get(i); // ou  Rect(figList.get(I)).paint(g);
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
	
	public static void main(String[] args) {  
		new PackFrame();  
	}  
}  
