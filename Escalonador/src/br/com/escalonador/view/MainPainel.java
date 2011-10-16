package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import br.com.escalonador.controller.Controller;
import br.com.escalonador.controller.Observer;
import br.com.escalonador.model.Processo;
import br.com.escalonador.model.TipoEscalonamento;
import br.com.escalonador.util.MessagesResource;

public class MainPainel extends JPanel {

	private static final long serialVersionUID = 1941367165748667283L;
	private static final int OPCAO_SIM = 0;
	private JTabbedPane abaPane;
	private JButton btSuperNovo;
	private JButton btInferNovo ;
	private JButton btSuperEditar;
	private JButton btInferEditar;
	private JButton btSuperExcluir;
	private JButton btInferExcluir;
	private JButton btIniciar;
	private JRadioButton jrbFIFO;
	private JRadioButton jrbLoteria;
	private JPanel painelTabelaProcessos;
	private JPanel painelEstatus;
	private JPanel painelMemoria;
	private JPanel painelInfo;
	private JTable jtable;
	private Controller controller;

	/**
	 * Construtor.
	 */
	public MainPainel() {
		super();
		controller = Controller.getInstance();
		setLayout(new BorderLayout());
		add(getManiPainel(), BorderLayout.CENTER);
		setVisible(true);
	}

	public void atualizarPainel(){
		setVisible(false);
		this.removeAll();
		setLayout(new BorderLayout());
		add(getManiPainel(), BorderLayout.CENTER);
		setVisible(true);
	}
	public void atualizarPainelProcessos(){
		setVisible(false);
		this.removeAll();
		setLayout(new BorderLayout());
		add(getManiPainel(), BorderLayout.CENTER);
		abaPane.setSelectedIndex(1);
		setVisible(true);
	}

	private JTabbedPane getManiPainel() {
		abaPane = new JTabbedPane();
		abaPane.addTab(MessagesResource.getString("janela.aba.processos"), getPainelProcessos());
		abaPane.addTab(MessagesResource.getString("janela.aba.execucao"), getPainelExecucao());
		return abaPane;
	}

	private Component getPainelProcessos() {
	    painelTabelaProcessos = new JPanel();
		painelTabelaProcessos.setLayout(new BorderLayout());
		painelTabelaProcessos.add(getPainelBotoesSuperiores(), BorderLayout.NORTH);
		painelTabelaProcessos.add(getPainelBotoesInferiores(), BorderLayout.SOUTH);
		painelTabelaProcessos.add(getTabelaProcessos(), BorderLayout.CENTER);
		painelTabelaProcessos.add(getPanel(), BorderLayout.EAST);
		painelTabelaProcessos.add(getPanel(), BorderLayout.WEST);
		return painelTabelaProcessos;
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

	@SuppressWarnings("serial")
	private JScrollPane getTabelaProcessos() {
		JScrollPane jScrollPane;
		String[] nomeColunas = {
				"",
				MessagesResource.getString("janela.aba.processos.tabela.pid"),
				MessagesResource.getString("janela.aba.processos.tabela.memoria"),
				MessagesResource.getString("janela.aba.processos.tabela.tempo"),
				MessagesResource.getString("janela.aba.processos.tabela.prioridade")
				};
		DefaultTableModel tableModel = new DefaultTableModel(Controller.getInstance().getLinhasTabela(), nomeColunas);
		jtable = new JTable(tableModel) {
		      public void tableChanged(TableModelEvent e) {
		          super.tableChanged(e);
		          repaint();
		        }
		      };
		ButtonGroup group1 = new ButtonGroup();
		for(int i=0;i < jtable.getRowCount(); i++){
			group1.add((JRadioButton)tableModel.getValueAt(i, 0));
		}
		jScrollPane = new JScrollPane(jtable);
		jtable.getColumn("").setCellRenderer(new RadioButtonRenderer());
		jtable.getColumn("").setCellEditor(new RadioButtonEditor(new JCheckBox()));
		return jScrollPane;
	}

	private Component getPainelExecucao() {
		JPanel painelExecucao = new JPanel();
//		painelExecucao.setLayout(new BorderLayout());
//		painelExecucao.add(getPainelMemoria(), BorderLayout.NORTH);
//		painelExecucao.add(getPainelStatus(), BorderLayout.CENTER);
//		painelExecucao.add(getPainelInfo(), BorderLayout.EAST);
		painelExecucao.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.weighty = 0.1;
		c.gridwidth = 3;
		
		c.gridx = 0;
		c.gridy = 0;
		painelExecucao.add(getPainelBotoesExecucao(), c);
		c.gridwidth = 3;
		c.ipady = 30;
		c.gridx = 0;
		c.gridy = 1;
		painelExecucao.add(getPainelMemoria(), c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 350;
		c.ipadx = 650;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		painelExecucao.add(getPainelStatus(), c);
		c.gridwidth = 1;
		c.ipady = 50;
		c.ipadx = 40;
		c.gridx = 2;
		c.gridy = 2;
		painelExecucao.add(getPainelInfo(), c);
		return painelExecucao;
	}

	private Component getPainelBotoesExecucao() {
		JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btIniciar = new JButton(MessagesResource.getString("janela.aba.execucao.botoes.start"));
//		btIniciar.seti
		btIniciar.addActionListener(new OuvinteBotoes());
		painel.add(btIniciar);
		painel.add(new JLabel(MessagesResource.getString("janela.aba.execucao.botoes.algoritmo")));
		ButtonGroup buttonGroup = new ButtonGroup();
		jrbFIFO = new JRadioButton(MessagesResource.getString("janela.aba.execucao.botoes.algoritmo.fifo"), true);
		jrbFIFO.addActionListener(new OuvinteTipoAlgoritmo());
		buttonGroup.add(jrbFIFO);
		jrbLoteria = new JRadioButton(MessagesResource.getString("janela.aba.execucao.botoes.algoritmo.loteria"));
		jrbLoteria.addActionListener(new OuvinteTipoAlgoritmo());
		buttonGroup.add(jrbLoteria);
		painel.add(jrbFIFO);
		painel.add(jrbLoteria);
		
		return painel;
	}

	private Component getPainelInfo() {
		Controller controller = Controller.getInstance();
		painelInfo = new JPanel();
		painelInfo.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		painelInfo.setBorder(BorderFactory.createTitledBorder(MessagesResource.getString("janela.aba.execucao.titulo.painel.informacao")));
		JLabel labelTipoAlgoritmo = new JLabel(controller.getLabelAlgoritmo());
		JLabel labelMemoria = new JLabel(controller.getLabelMemoria());
		JLabel labelProcesso = new JLabel(controller.getLabelQtdProcesso());
		JLabel labelTempo = new JLabel(controller.getLabelTempoTotal());
		c.gridx = 0;
		c.gridy = 0;
		painelInfo.add(labelTipoAlgoritmo,c);
		c.gridx = 0;
		c.gridy = 1;
		painelInfo.add(labelMemoria, c);
		c.gridx = 0;
		c.gridy = 2;
		painelInfo.add(labelProcesso, c);
		c.gridx = 0;
		c.gridy = 3;
		painelInfo.add(labelTempo, c);
		
		return painelInfo;
	}
	

	private Component getPainelMemoria() {
		painelMemoria = new JPanel();
		painelMemoria.setBorder(BorderFactory.createTitledBorder(MessagesResource.getString("janela.aba.execucao.titulo.painel.memoria")));
		return painelMemoria;
	}

	private Component getPainelStatus() {
		Controller controller = Controller.getInstance();
		painelEstatus = new JPanel();
		painelEstatus.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		painelEstatus.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		int row = 0;
		for (Processo p : controller.getListProcessos()) {
			JPanel painel = new JPanel();
			JLabel label = new JLabel(p.toString());
			painel.add(label);
			switch (p.getEstado()) {
			case BLOQUEADO:
				painel.setBackground(Color.RED);
				break;
			case EXECUTANDO:
				painel.setBackground(Color.GREEN);
				break;
			case PRONTO:
				painel.setBackground(Color.YELLOW);
				break;
			case FINALIZADO:
				painel.setBackground(Color.GRAY);
				break;
			}
			c.gridx = 0;
			c.gridy = row;
			painelEstatus.add(painel, c);
			row++;
		}
		JScrollPane jScrollPane = new JScrollPane(painelEstatus);
		jScrollPane.setBorder(BorderFactory.createTitledBorder(MessagesResource.getString("janela.aba.execucao.titulo.painel.monitor")));
		return jScrollPane;
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
				ProcessoWindow.getInstance();
			}
			if (e.getSource() == btSuperEditar || e.getSource() == btInferEditar) {

			}
			if (e.getSource() == btSuperExcluir || e.getSource() == btInferExcluir) {
				int index = jtable.getSelectedRow();
				int confirmacao = JOptionPane.showConfirmDialog(null, MessagesResource.getString("janela.confima.exclusao.texto"), MessagesResource.getString("janela.confima.exclusao.titulo"), JOptionPane.YES_NO_OPTION);
				if(confirmacao == OPCAO_SIM) {
					Observer.getInstance().removerProcesso(index);
				}
			}
			if(e.getSource() == btIniciar) {
				controller.iniciarEscalonamento();
			}
		}

	}
	
	/**
	 * Listener do tipo de  algoritmo.
	 *
	 * @author nayalison
	 */
	class OuvinteTipoAlgoritmo implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jrbFIFO) {
				controller.setTipoEscalonamento(TipoEscalonamento.FIFO);
			}
			if(e.getSource() == jrbLoteria) {
				controller.setTipoEscalonamento(TipoEscalonamento.LOTERIA);
			}
		}

	}



}
