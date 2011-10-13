/**
 * 
 */
package br.com.escalonador.model;

/**
 * @author nayalison
 *
 */
public enum Estado {
	PRONTO(1),
	EXECUTANDO(2),
	BLOQUEADO(3),
	FINALIZADO(4);
	
	private int estado;
	

	private Estado(int estado) {
		this.estado = estado;
	}

	/**
	 * @return the estado
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

}
