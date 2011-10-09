/**
 * 
 */
package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.UIManager;

import br.com.escalonador.controller.Observer;
import br.com.escalonador.util.MessagesResource;

/**
 * Janela pricipal do programa.
 * 
 * @author nayalison
 */
public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = -1470223680745055236L;
	private Observer observer;

	public MainWindow() {
		observer = Observer.getInstance();
		observer.setMainWindow(this);
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(MessagesResource.getString("janela.titulo"));
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		add(new Menu(), BorderLayout.NORTH);
		add(new MainPainel(), BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void atualizarPainel(){
		MainPainel mainPainel = (MainPainel)this.getContentPane().getComponent(1);
		mainPainel.repaint();
	}
	
	public static void main(String[] args) {
		 try {
			 UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		MainWindow window = new MainWindow();
	}

}
