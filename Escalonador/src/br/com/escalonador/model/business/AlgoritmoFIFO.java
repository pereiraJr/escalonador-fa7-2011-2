/**
 * 
 */
package br.com.escalonador.model.business;

import java.util.List;

import br.com.escalonador.controller.Observer;
import br.com.escalonador.model.Estado;
import br.com.escalonador.model.Processo;

/**
 * @author nayalison
 *
 */
public class AlgoritmoFIFO implements AlgoritmoEscalonamento, Runnable {
	
	private List<Processo> listaProcessos;
	private boolean isToStop = false;

	/* (non-Javadoc)
	 * @see br.com.escalonador.model.business.AlgoritmoEscalonamento#escalonar(java.util.List)
	 */
	@Override
	public void escalonar(List<Processo> listaProcessos) {
		this.listaProcessos = listaProcessos;
		this.run();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		for(Processo p: listaProcessos) {
			p.start();
			Observer.getInstance().atualizarPainelProcessos();
			while(p.getEstado() != Estado.FINALIZADO){
				if(isToStop){
					p.interrupt();
					break;
				}
				System.out.println("running");
			}
			Observer.getInstance().atualizarPainelProcessos();
		}
	}

	/* (non-Javadoc)
	 * @see br.com.escalonador.model.business.AlgoritmoEscalonamento#stop()
	 */
	@Override
	public void parar() {
		isToStop = true;
	}

}
