package br.com.escalonador.model;

public enum TipoEscalonamento {

	FIFO(1),
	LOTERIA(2);
	
	private int codEscalonamneto;

	/**
	 * @param codEscalonamneto
	 */
	private TipoEscalonamento(int codEscalonamneto) {
		this.codEscalonamneto = codEscalonamneto;
	}

	/**
	 * @return the codEscalonamneto
	 */
	public final int getCodEscalonamneto() {
		return codEscalonamneto;
	}

	/**
	 * @param codEscalonamneto the codEscalonamneto to set
	 */
	public final void setCodEscalonamneto(int codEscalonamneto) {
		this.codEscalonamneto = codEscalonamneto;
	}
	
}
