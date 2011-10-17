/**
 *
 */
package br.com.escalonador.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import br.com.escalonador.controller.Controller;
import br.com.escalonador.controller.Observer;
import br.com.escalonador.model.TipoEscalonamento;
import br.com.escalonador.util.MessagesResource;

/**
 * Essa classe é representa o menu da aplicação.
 *
 * @author nayalison
 */
public class Menu extends JMenuBar {

	private static final long serialVersionUID = 9182557741955211629L;
	private JMenu arquivo;
	private JMenu configuracoes;
	private JMenuItem iniciar;
	private JMenuItem parar;
	private JMenuItem limparValores;
	private JMenuItem sair;
	private Controller controller;
	private JMenu subMenuAlgoritmo;
	private JMenuItem subMenuFifo;
	private JMenuItem subMenuLoteria;

	/**
	 * Construtor.
	 */
	public Menu() {
		super();
		controller = Controller.getInstance();
		add(getMenuArquivo());
		add(getMenuConfiguracoes());
	}

	/**
	 * Esse método obtém o menu de arquivo.
	 *
	 * @return {@link JMenu}
	 */
	private JMenu getMenuArquivo(){
		arquivo = new JMenu(MessagesResource.getString("janela.menu.arquivo"));
		iniciar = new JMenuItem(MessagesResource.getString("janela.menu.iniciar"));
		iniciar.addActionListener(new OuvinteMenuIniciar());
		parar = new JMenuItem(MessagesResource.getString("janela.menu.parar"));
		parar.addActionListener(new OuvinteMenuParar());
		limparValores = new JMenuItem(MessagesResource.getString("janela.menu.limpar.valores"));
		limparValores.addActionListener(new OuvinteMenuLimpar());
		sair = new JMenuItem(MessagesResource.getString("janela.menu.sair"));
		sair.addActionListener(new OuvinteMenuSair());
		arquivo.add(iniciar);
		arquivo.add(parar);
		arquivo.add(limparValores);
		arquivo.add(sair);
		return arquivo;
	}

	/**
	 * Esse método obtém o menu de configuração.
	 *
	 * @return {@link JMenu}
	 */
	private JMenu getMenuConfiguracoes() {
		configuracoes = new JMenu(MessagesResource.getString("janela.menu.configuracoes"));
		subMenuAlgoritmo = new JMenu(MessagesResource.getString("janela.menu.configuracoes.algoritmo"));
		configuracoes.add(subMenuAlgoritmo);
		ButtonGroup buttonGroup = new ButtonGroup();
		subMenuFifo = new JRadioButtonMenuItem(MessagesResource.getString("janela.menu.configuracoes.algoritmo.fifo"), controller.isAlgoritmoFifo());
		subMenuFifo.addActionListener(new OuvinteSubMenuAlgoritmo());
		buttonGroup.add(subMenuFifo);
		subMenuLoteria = new JRadioButtonMenuItem(MessagesResource.getString("janela.menu.configuracoes.algoritmo.loteria"), controller.isAlgoritmoLoteria());
		subMenuLoteria.addActionListener(new OuvinteSubMenuAlgoritmo());
		buttonGroup.add(subMenuLoteria);
		subMenuAlgoritmo.add(subMenuFifo);
		subMenuAlgoritmo.add(subMenuLoteria);
		return configuracoes;
	}

	/**
	 * Listener do menu iniciar.
	 *
	 * @author nayalison
	 */
	class OuvinteMenuIniciar implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.iniciarEscalonamento();
		}
	}

	/**
	 * Listener do menu parar.
	 *
	 * @author nayalison
	 */
	class OuvinteMenuParar implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.pararEscalonamneto();
		}

	}

	/**
	 * Listener do menu limpar valores.
	 *
	 * @author nayalison
	 */
	class OuvinteMenuLimpar implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			controller.limparValores();
			Observer.getInstance().atualizarTabelaProcessos();
		}

	}

	/**
	 * Listener do menu sair.
	 *
	 * @author nayalison
	 */
	class OuvinteMenuSair implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Observer.getInstance().fecharJanela();
		}

	}

	/**
	 * Listener do menu algoritmo.
	 *
	 * @author nayalison
	 */
	class OuvinteSubMenuAlgoritmo implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == subMenuFifo) {
				controller.setTipoEscalonamento(TipoEscalonamento.FIFO);
				Observer.getInstance().atualizarPainelProcessos();
			}
			if(e.getSource() == subMenuLoteria) {
				controller.setTipoEscalonamento(TipoEscalonamento.LOTERIA);
				Observer.getInstance().atualizarPainelProcessos();
			}
		}

	}

}
