/**
 * 
 */
package br.com.escalonador.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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
	private Controller controller;
	private Observer observer;
	private static ProcessoWindow instance;
	
	private ProcessoWindow(){
		setResizable(false);
		controller = Controller.getInstance();
		observer = Observer.getInstance();
		observer.setProcessoWindow(this);
		setSize(400, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(MessagesResource.getString("janela.processo.titulo"));
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		add(new Menu(), BorderLayout.NORTH);
		add(getMainPanel(), BorderLayout.CENTER);
		add(getPainelBotoes(), BorderLayout.SOUTH);
		setVisible(true);
	}

	private Component getPainelBotoes() {
		JPanel panelBotoes = new JPanel();
		panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER));
		btSalvar = new JButton(MessagesResource.getString("janela.processo.salvar"));
		btSalvar.addActionListener(this);
		panelBotoes.add(btSalvar);
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
	
	public Processo getProcesso(){
		return new Processo(controller.getPid(), Long.valueOf(qtdTempo.getText()),  Integer.valueOf(qtdMenmoria.getText()), Prioridade.BAIXA);
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
		if(e.getSource() == btCancelar) {
			observer.closeProcessoWindow();
		}
	}

	private boolean isCamposValidos() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
