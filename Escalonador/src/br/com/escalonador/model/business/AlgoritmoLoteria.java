package br.com.escalonador.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.escalonador.controller.Observer;
import br.com.escalonador.model.Estado;
import br.com.escalonador.model.Processo;
import br.com.escalonador.model.SimuladorConstants;
import br.com.escalonador.model.exception.BusinessException;

/**
 * Esse classe simula a execução do algoritmo de escalonamneto
 * loteria.
 * 
 * @author nayalison
 *
 */
public class AlgoritmoLoteria extends Thread implements AlgoritmoEscalonamento {

	private List<Processo> processos;
	private List<Processo> listaProcessos;
	private boolean isToStop = false;

	@Override
	public void escalonar(List<Processo> listaProcessos)
			throws BusinessException {
		this.listaProcessos = listaProcessos;
		this.start();
	}

	private Processo obterProcessoSorteado(Integer bilhete) {
		Processo processo = null;
		for (Processo p : processos) {
			if (p.hasBilhete(bilhete)) {
				processo = p;
				break;
			}
		}
		return processo;
	}
	
	

	/* (non-Javadoc)
	 * @see br.com.escalonador.model.business.AlgoritmoEscalonamento#parar()
	 */
	@Override
	public void parar() {
		isToStop = true;
		
	}

	@Override
	public void run() {
		try {
			int qtdBilhetes = 0;
			processos = new ArrayList<Processo>();
			processos.addAll(listaProcessos);
			for (Processo p : processos) {
				qtdBilhetes += p.getPrioridade().getPrioridade();
			}
			GeradorBilhete geradorBilhete = new GeradorBilhete();
			geradorBilhete.gerarBilhetes(qtdBilhetes);
			for (Processo p : processos) {
				p.setListaBilhetes(geradorBilhete.obterBilhete(p
						.getPrioridade().getPrioridade()));
			}

			while (!processos.isEmpty()) {
				Integer bilhete = geradorBilhete.sortearBilhete();
				Processo processo = obterProcessoSorteado(bilhete);
				if(processo.getEstado() != Estado.FINALIZADO) {
					if (!processo.isAlive()) {
						processo.start();
					} else {
						processo.reiniciar();
					}
				}
				long tempoRestante = System.currentTimeMillis() + SimuladorConstants.QUANTUM_PREMIO;
				while (tempoRestante - System.currentTimeMillis() > 0 && processo.getEstado() != Estado.FINALIZADO);
				processo.getLockObject().lock();
				if (processo.getEstado() == Estado.FINALIZADO) {
					processos.remove(processo);
					geradorBilhete.devolverBilhetes(processo.getListaBilhetes());
				} else {
					processo.parar();
				}
				processo.getLockObject().unlock();
				Observer.getInstance().atualizarPainelProcessos();
			}
		} catch (BusinessException e) {
			JOptionPane
					.showMessageDialog(null,
							"Erro na execução do algoritmo de escalonamneto loteria"
									+ e.getMessage(), "Erro",
							JOptionPane.ERROR_MESSAGE);
		}
	}

}
