/**
 * 
 */
package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.escalonador.controller.Controller;
import br.com.escalonador.controller.Observer;
import br.com.escalonador.model.Prioridade;
import br.com.escalonador.model.Processo;
import br.com.escalonador.util.MessagesResource;

/**
 * @author nayalison
 *
 */
public class ProcessoWindow extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 9222119543327192455L;
	private JComboBox cbPrioridade;
	private JTextField qtdMenmoria;
	private JTextField qtdTempo;
	private JButton btSalvar;
	private JButton btCancelar;
	private JButton btAddMais;
	private Controller controller;
	private Observer observer;
	private static ProcessoWindow instance;
	
	private ProcessoWindow(){
		setResizable(false);
		controller = Controller.getInstance();
		observer = Observer.getInstance();
		observer.setProcessoWindow(this);
		setSize(410, 190);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(MessagesResource.getString("janela.processo.titulo"));
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		add(getMainPanel(), BorderLayout.CENTER);
		add(getPainelBotoes(), BorderLayout.SOUTH);
		add(getPanel(), BorderLayout.WEST);
		add(getPanel(), BorderLayout.EAST);
		add(getPanel(), BorderLayout.NORTH);
		centralizar();
		setVisible(true);
	}

	private Component getPainelBotoes() {
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
		btSalvar = new JButton(MessagesResource.getString("janela.processo.salvar"));
		btSalvar.addActionListener(this);
		panelBotoes.add(btSalvar);
		btAddMais= new JButton(MessagesResource.getString("anela.processo.add.mais"));
		btAddMais.addActionListener(this);
		panelBotoes.add(btAddMais);
		btCancelar = new JButton(MessagesResource.getString("janela.processo.cancelar"));
		btCancelar.addActionListener(this);
		panelBotoes.add(btCancelar);
		return panelBotoes;
	}

	private Component getMainPanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 2, 1, 1));
		mainPanel.add(new JLabel(MessagesResource.getString("janela.processo.Prioridade")));
		cbPrioridade = new JComboBox(Prioridade.values());
		mainPanel.add(cbPrioridade);
		mainPanel.add(new JLabel(MessagesResource.getString("janela.processo.tempo")));
		qtdTempo = new JTextField("");
		mainPanel.add(qtdTempo);
		mainPanel.add(new JLabel(MessagesResource.getString("janela.processo.qtd.menmoria")));
		qtdMenmoria = new JTextField("");
		mainPanel.add(qtdMenmoria);
		return mainPanel;
	}

	public static ProcessoWindow getInstance() {
	    instance = new ProcessoWindow();
		return instance;
	}
	
	public void centralizar(){
		Dimension dimension = this.getToolkit().getScreenSize(); 
		int x = (int) (dimension.getWidth() - this.getSize().getWidth() ) / 2; 
		int y = (int) (dimension.getHeight() - this.getSize().getHeight()) / 2; 
		this.setLocation(x,y); 
	}
	
	public JPanel getPanel(){
		JPanel tmp = new JPanel();
		tmp.setSize(2, 7);
		return tmp;
	}
	
	public void limparValores(){
		cbPrioridade.setSelectedIndex(0);
		cbPrioridade.requestFocusInWindow();
		qtdTempo.setText("");
		qtdMenmoria.setText("");
	}

	
	public Processo getProcesso(){
		return new Processo(controller.getPid(), Long.valueOf(qtdTempo.getText()),  Integer.valueOf(qtdMenmoria.getText()), (Prioridade)cbPrioridade.getSelectedItem());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btSalvar) {
			if(isCamposValidos()){
				observer.addProcesso();
			}
		}
		if(e.getSource() == btAddMais) {
			if(isCamposValidos()){
				observer.addMaisUmProcesso();
				limparValores();
			}
		}
		if(e.getSource() == btCancelar) {
			observer.closeProcessoWindow();
		}
	}

	private boolean isCamposValidos() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
