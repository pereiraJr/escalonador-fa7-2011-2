package br.com.escalonador.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import br.com.escalonador.model.Estado;
import br.com.escalonador.model.Processo;
import br.com.escalonador.model.SimuladorConstants;

public class PainelStatusMemoria extends JPanel{

	private static final long serialVersionUID = 8598604308681869329L;
	private List<Processo> processos;
	private int x = getX();
	private int y = getY();
	private int height = 40;
	private int width = 950;
	
	public PainelStatusMemoria(List<Processo> processos){
		super();
		setPreferredSize(new Dimension(width, 70));
		this.processos = processos;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
		int index = x;
		for(Processo p : processos) {
			if(p.getEstado() != Estado.FINALIZADO) {
				int percMemoProcesso = calcularPercentualMemoria(p);
				g.setColor(p.getColor());
				g.fillRect(index, y, percMemoProcesso, height);
				index += percMemoProcesso;
			}
		}
		
	}

	private int calcularPercentualMemoria(Processo p) {
		return (width * p.getTamanhoMemoria())/ SimuladorConstants.TAMANHO_MEMORIA;
	}

	
}
