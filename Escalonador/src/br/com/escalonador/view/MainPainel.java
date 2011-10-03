package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.escalonador.controller.Controller;
import br.com.escalonador.util.MessagesResource;

public class MainPainel extends JPanel {

	private static final long serialVersionUID = 1941367165748667283L;
	private Controller controller;
	private JTabbedPane abaPane;
	private JButton btSuperNovo;
	private JButton btInferNovo ;
	private JButton btSuperEditar;
	private JButton btInferEditar;
	private JButton btSuperExcluir;
	private JButton btInferExcluir;
	private JPanel painelProcessos;

	/**
	 * Construtor.
	 */
	public MainPainel() {
		super();
		controller = Controller.getInstance();
		setLayout(new BorderLayout());
		add(getManiPainel(), BorderLayout.CENTER);
		add(getPanel(), BorderLayout.EAST);
		add(getPanel(), BorderLayout.WEST);
		setVisible(true);
	}

	private JTabbedPane getManiPainel() {
		abaPane = new JTabbedPane();
		abaPane.addTab(MessagesResource.getString("janela.aba.processos"), getPainelProcessos());
		abaPane.addTab(MessagesResource.getString("janela.aba.execucao"), getPainelExecucao());
		return abaPane;
	}
	
	private Component getPainelProcessos() {
	    painelProcessos = new JPanel();
		painelProcessos.setLayout(new BorderLayout());
		painelProcessos.add(getPainelBotoesSuperiores(), BorderLayout.NORTH);
		painelProcessos.add(getPainelBotoesInferiores(), BorderLayout.SOUTH);
		painelProcessos.add(getTabelaProcessos(), BorderLayout.CENTER);
		return painelProcessos;
	}

	/**
	 * 
	 */
	private JPanel getPainelBotoesSuperiores() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT));
		btSuperNovo = new JButton(MessagesResource.getString("janela.aba.processos.novo"));
		btSuperNovo.addActionListener(new OuvinteBotoes());
		painelBotoes.add(btSuperNovo);
		btSuperEditar = new JButton(MessagesResource.getString("janela.aba.processos.editar"));
		btSuperEditar.addActionListener(new OuvinteBotoes());
		painelBotoes.add(btSuperEditar);
		btSuperExcluir = new JButton(MessagesResource.getString("janela.aba.processos.excliuir"));
		btSuperExcluir.addActionListener(new OuvinteBotoes());
		painelBotoes.add(btSuperExcluir);
		return painelBotoes;
	}
	
	/**
	 * 
	 */
	private JPanel getPainelBotoesInferiores() {
		JPanel painelBotoes = new JPanel();
		painelBotoes.setLayout(new FlowLayout(FlowLayout.LEFT));
		btInferNovo = new JButton(MessagesResource.getString("janela.aba.processos.novo"));
		btInferNovo.addActionListener(new OuvinteBotoes());
		painelBotoes.add(btInferNovo);
		btInferEditar = new JButton(MessagesResource.getString("janela.aba.processos.editar"));
		btInferEditar.addActionListener(new OuvinteBotoes());
		painelBotoes.add(btInferEditar);
		btInferExcluir = new JButton(MessagesResource.getString("janela.aba.processos.excliuir"));
		btInferExcluir.addActionListener(new OuvinteBotoes());
		painelBotoes.add(btInferExcluir);
		
		return painelBotoes;
	}

	private JTable getTabelaProcessos() {
		String[] nomeColunas = {
				MessagesResource.getString("janela.aba.processos.tabela.pid"),
				MessagesResource.getString("janela.aba.processos.tabela.memoria"),
				MessagesResource.getString("janela.aba.processos.tabela.tempo"),
				MessagesResource.getString("janela.aba.processos.tabela.prioridade")
				};
		DefaultTableModel tableModel = new DefaultTableModel(controller.getLinhasTabela(), nomeColunas);
		JTable jtable = new JTable(tableModel);
		return jtable;
	}

	private Component getPainelExecucao() {
		return null;
	}
	
	public JPanel getPanel(){
		JPanel tmp = new JPanel();
		tmp.setSize(2, 7);
		return tmp;
	}
  
	

	/**
	 * Listener do menu parar.
	 * 
	 * @author nayalison
	 */
	class OuvinteBotoes implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btSuperNovo || e.getSource() == btInferNovo) {
				controller.addProcesso(ProcessoWindow.getProcesso());
			}
			if (e.getSource() == btSuperEditar || e.getSource() == btInferEditar) {

			}
			if (e.getSource() == btSuperExcluir || e.getSource() == btInferExcluir) {

			}
		}

	}
	

	
}
