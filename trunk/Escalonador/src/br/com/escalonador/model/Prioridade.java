/**
 * 
 */
package br.com.escalonador.model;

/**
 * @author nayalison
 *
 */
public enum Prioridade {
	
	BAIXA(1),
	NORMAL(2),
	ALTA(3);
	
	private int prioridade;

	private Prioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	/**
	 * @return the prioridade
	 */
	public int getPrioridade() {
		return prioridade;
	}

	/**
	 * @param prioridade the prioridade to set
	 */
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	
}
