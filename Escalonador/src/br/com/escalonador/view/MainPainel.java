package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JPanel painelTabelaProcessos;
	private JPanel painelEstatus;
	private JPanel painelMemoria;
	private JPanel painelInfo;
	private JTable jtable;

	/**
	 * Construtor.
	 */
	public MainPainel() {
		super();
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
		painelExecucao.setLayout(new BorderLayout());
		painelExecucao.add(getPainelMemoria(), BorderLayout.NORTH);
		painelExecucao.add(getPainelStatus(), BorderLayout.WEST);
		painelExecucao.add(getPainelInfo(), BorderLayout.EAST);
		return painelExecucao;
	}

	private Component getPainelInfo() {
		painelInfo = new JPanel();
		painelInfo.setBackground(Color.DARK_GRAY);
		return painelInfo;
	}

	private Component getPainelMemoria() {
		painelMemoria = new JPanel();
		painelMemoria.setBackground(Color.blue);
		return painelMemoria;
	}

	private Component getPainelStatus() {
		Controller controller = Controller.getInstance();
		painelEstatus = new JPanel();
		painelEstatus.setLayout(new GridLayout(controller.getQtdProcessos(), 1));
		for(Processo p: controller.getListProcessos()) {
			JLabel label = new JLabel(p.toString());
			switch(p.getEstado()){
			case BLOQUEADO:
				label.setBackground(Color.RED);
				break;
			case EXECUTANDO: 
				label.setBackground(Color.green);
				break;
			case PRONTO:
				label.setBackground(Color.YELLOW);
				break;
			case FINALIZADO:
				label.setBackground(Color.GRAY);
				break;
			}
			painelEstatus.add(label);
		}
		JScrollPane jScrollPane = new JScrollPane(painelEstatus);
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
				Observer.getInstance().bloquearMainWindow();
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
		}

	}



}
