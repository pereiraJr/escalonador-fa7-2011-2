/**
 * 
 */
package br.com.escalonador.model;

/**
 * 
 * @author Nayalison
 */
public class Processo {

	private int pid;
	private String descricao;
	private long tempoProcessamento;
	private int tamanhoMemoria;
	
	private Prioridade prioridade;
	private Estado estado;
	
	
	
	/**
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
	}
	
	
	
	/**
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
	
}
