import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

import figures.*;

public class MenuFrame extends JFrame implements MouseListener{  
	ArrayList<Button> buttonList = new ArrayList<Button>();
	//para tratar BTfocus
	Button BTfocus = null; 
	int mousex = 0, mousey = 0; //para identificar a posição do click do mouse

	MenuFrame(){  
		addMouseListener(this);
		addWindowListener (
				new WindowAdapter() {
					public void windowClosing (WindowEvent e) {
						System.exit(0);
					}
				}
				); 

		

		setSize(20,160);  
		setLayout(null);  
		this.setTitle("Menu");
		this.setFocusTraversalKeysEnabled(false);
		criabotoes();
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
		Button butAp; // definindo um botao de apoio
		int i, n;
		Button newFocus = null;

		//Capturando a posição do click do mouse para uso futuro
		mousex = e.getX();
		mousey = e.getY();

		//Verificando se o click foi sobre um botao na tela
		n = buttonList.size();
		for (i=0; i<n; i++) {
			butAp = buttonList.get(i);
			if (butAp.clicked(mousex, mousey)) {
				newFocus = butAp;     	   
			};
		};

		// Caso seja num botao, retirar o focus do anterior - se houver - e passar para o novo

		if (newFocus != null) {
			trataBotaoEmFoco (newFocus);
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


	public void paint (Graphics g) {
		super.paint(g);

		Button butAp; // definindo um retângulo de apoio
		int i, n;
		n = buttonList.size();
		for (i=0; i<n; i++) {
			butAp = buttonList.get(i);
			butAp.paint(g, false);
		};
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
		new MenuFrame();  
	} 
}  
