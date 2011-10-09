package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import br.com.escalonador.controller.Controller;
import br.com.escalonador.controller.Observer;
import br.com.escalonador.util.MessagesResource;

public class MainPainel extends JPanel {

	private static final long serialVersionUID = 1941367165748667283L;
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
		setLayout(new BorderLayout());
		add(getManiPainel(), BorderLayout.CENTER);
//		add(getPanel(), BorderLayout.EAST);
//		add(getPanel(), BorderLayout.WEST);
		setVisible(true);
	}
	
	public void repaint(){
		this.removeAll();
		setLayout(new BorderLayout());
		add(getManiPainel(), BorderLayout.CENTER);
//		add(getPanel(), BorderLayout.EAST);
//		add(getPanel(), BorderLayout.WEST);
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
		painelProcessos.add(getPanel(), BorderLayout.EAST);
		painelProcessos.add(getPanel(), BorderLayout.WEST);
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
		@SuppressWarnings("serial")
		JTable jtable = new JTable(tableModel) {
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
				Observer.getInstance().bloquearMainWindow();
				ProcessoWindow.getInstance();
			}
			if (e.getSource() == btSuperEditar || e.getSource() == btInferEditar) {

			}
			if (e.getSource() == btSuperExcluir || e.getSource() == btInferExcluir) {

			}
		}

	}
	

	
}
