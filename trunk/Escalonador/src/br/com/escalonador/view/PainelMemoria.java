package br.com.escalonador.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.escalonador.model.Estado;
import br.com.escalonador.model.Processo;

public class PainelMemoria extends JPanel {

	private static final long serialVersionUID = -2030727745241911964L;
	
	public PainelMemoria(List<Processo> processos) {
		super();
		setLayout(new GridLayout(2, 1));
		add(getPainelStatusMemoria(processos));
		add(getPainelLabelMemoria(processos));
	}

	private Component getPainelLabelMemoria(List<Processo> processos) {
		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(getQtdColumns(processos.size()), 6));
		for(Processo p: processos) {
			if(p.getEstado() != Estado.FINALIZADO) {
				JPanel painelCentral = new JPanel();
				JPanel painelCor = new JPanel();
				painelCor.setBackground(p.getColor());
				painelCentral.add(painelCor);
				painelCentral.add(new JLabel(": Processo " +String.valueOf(p.getPid())));
				painel.add(painelCentral);	
			}
		}
		JScrollPane scroll = new JScrollPane(painel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		return scroll;
	}
	private int getQtdColumns(int qtdProcessos) {
		int row = (int)Math.ceil(Float.valueOf(qtdProcessos)/7.0f);
		if(row == 0){
			row = 1;
		}
		return row;
	}
	
	private PainelStatusMemoria getPainelStatusMemoria(List<Processo> processos) {
		return new PainelStatusMemoria(processos);
	}

}
