/**
 * 
 */
package br.com.escalonador.model;

import java.util.ArrayList;
import java.util.List;

import br.com.escalonador.controller.Observer;
import br.com.escalonador.model.exception.BusinessException;

/**
 * 
 * @author Nayalison
 */
public class Processo extends Thread{

	private int pid;
	private String descricao;
	private long tempoProcessamento;
	private int tamanhoMemoria;
	private Prioridade prioridade;
	private Estado estado;
	private long tempoRestante;
	private long horaReferencia;
	private List<Integer> listaBilhetes;
	
	
	
	/**
	 * Construtor.
	 * 
	 * @param pid id do processo
	 * @param tempoProcessamento tempo de processamento
	 * @param tamanhoMemoria tamanho de memória
	 * @param prioridade prioridade do processo
	 */
	public Processo(int pid, long tempoProcessamento, int tamanhoMemoria,
			Prioridade prioridade) {
		super();
		this.pid = pid;
		this.tempoProcessamento = tempoProcessamento;
		this.tamanhoMemoria = tamanhoMemoria;
		this.prioridade = prioridade;
		this.estado = Estado.PRONTO;
		listaBilhetes = new ArrayList<Integer>();
	}
	
	
	
	/**
	 * Construtor.
	 * 
	 * @param pid id do processo
	 * @param descricao descrição do processo
	 * @param tempoProcessamento tempo de processamento
	 * @param tamanhoMemoria tamanho de memória
	 * @param prioridade prioridade do processo
	 * @param estado estado do processo
	 */
	public Processo(int pid, String descricao, long tempoProcessamento,
			int tamanhoMemoria, Prioridade prioridade, Estado estado) {
		super();
		this.pid = pid;
		this.descricao = descricao;
		this.tempoProcessamento = tempoProcessamento;
		this.tamanhoMemoria = tamanhoMemoria;
		this.prioridade = prioridade;
		this.estado = estado;
		listaBilhetes = new ArrayList<Integer>();
	}



	/**
	 * @return the descricao
	 */
	public final String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public final void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the prioridade
	 */
	public final Prioridade getPrioridade() {
		return prioridade;
	}
	/**
	 * @param prioridade the prioridade to set
	 */
	public final void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
	/**
	 * @return the pid
	 */
	public final int getPid() {
		return pid;
	}
	/**
	 * @return the tempoProcessamento
	 */
	public final long getTempoProcessamento() {
		return tempoProcessamento;
	}
	/**
	 * @return the tamanhoMemoria
	 */
	public final int getTamanhoMemoria() {
		return tamanhoMemoria;
	}
	/**
	 * @return the estado
	 */
	public final Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public final void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the listaBilhetes
	 */
	public final List<Integer> getListaBilhetes() {
		return listaBilhetes;
	}

	/**
	 * @param listaBilhetes the listaBilhetes to set
	 */
	public final void setListaBilhetes(List<Integer> listaBilhetes) {
		this.listaBilhetes = listaBilhetes;
	}
	
	public boolean hasBilhete(final Integer bilhete){
		boolean retorno = false;
		if(listaBilhetes!=null && !listaBilhetes.isEmpty()) {
			retorno = listaBilhetes.contains(bilhete);
		}
		return retorno;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		this.estado = Estado.EXECUTANDO;
		Observer.getInstance().atualizarPainelProcessos();
		horaReferencia = System.currentTimeMillis();
		tempoRestante = horaReferencia + tempoProcessamento * SimuladorConstants.QUANTUM;
		while(tempoRestante - horaReferencia > 0) {
			horaReferencia = System.currentTimeMillis();
		}
		this.estado = Estado.FINALIZADO;
	}
	
	public void parar() throws BusinessException {
		try {
			this.estado = Estado.PRONTO;
			this.wait();
		} catch (InterruptedException e) {
			throw new BusinessException("Não foi possivel parar o processo com o PID= " + pid, e);
		}
	}
	
	public void reiniciar(){
		this.estado = Estado.EXECUTANDO;
		long tempoParado = System.currentTimeMillis() - horaReferencia;
		tempoRestante += tempoParado;
		this.notify();
	}

	public String toString() {
		return "PID: " + pid + " Tempo de processamento: " + tempoProcessamento
				+ " Memória requerida: " + tamanhoMemoria + " Prioridade: "
				+ prioridade + " Status: " + estado;
	}
	
}
