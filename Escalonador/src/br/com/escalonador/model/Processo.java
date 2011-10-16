/**
 * 
 */
package br.com.escalonador.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

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
	private boolean pausado;
	private Lock lockObject;
	
	
	
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
		setEstado(Estado.PRONTO);
		listaBilhetes = new ArrayList<Integer>();
		pausado = false;
		lockObject =  new ReentrantLock(true);
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
		setEstado(estado);
		listaBilhetes = new ArrayList<Integer>();
		pausado = false;
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
	public synchronized final Estado getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public synchronized final void setEstado(Estado estado) {
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

	public long getTempoRestante() {
		return tempoRestante - horaReferencia;
	}

	public boolean hasBilhete(final Integer bilhete){
		boolean retorno = false;
		if(listaBilhetes!=null && !listaBilhetes.isEmpty()) {
			retorno = listaBilhetes.contains(bilhete);
		}
		return retorno;
	}
	

	/**
	 * @return the lockObject
	 */
	public final Lock getLockObject() {
		return lockObject;
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		setEstado(Estado.EXECUTANDO);
		Observer.getInstance().atualizarPainelProcessos();
		horaReferencia = System.currentTimeMillis();
		tempoRestante = horaReferencia + (tempoProcessamento * SimuladorConstants.QUANTUM);
		while(tempoRestante - horaReferencia > 0) {
			horaReferencia = System.currentTimeMillis();
			verificaPausa();
		}
		
		//==============================================
		//Região cítica
		//==============================================
		lockObject.lock();
		setEstado(Estado.FINALIZADO);
		pausado = false;
		lockObject.unlock();
	}
	
	public void parar()
			throws BusinessException {
		if(getEstado() != Estado.FINALIZADO) {
			setEstado(Estado.PRONTO);
		}
		pausado = true;
	}
	
	public synchronized void reiniciar() {
		setEstado(Estado.EXECUTANDO);
		long tempoParado = System.currentTimeMillis() - horaReferencia;
		tempoRestante += tempoParado;
		pausado = false;
		notifyAll();

	}
	
	private synchronized void verificaPausa(){
		try {
			while (pausado) {
				wait();
			}

		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "No foi possível para o processo pid: "
					+ pid, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


	public String toString() {
		return "PID: " + pid + " Tempo de processamento: " + tempoProcessamento
				+ " Memória requerida: " + tamanhoMemoria + " Prioridade: "
				+ prioridade + " Status: " + getEstado();
	}
	
}
