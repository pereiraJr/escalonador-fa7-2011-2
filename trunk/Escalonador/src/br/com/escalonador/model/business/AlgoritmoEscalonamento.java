package br.com.escalonador.model.business;

import java.util.List;

import br.com.escalonador.model.Processo;
import br.com.escalonador.model.exception.BusinessException;

/**
 * Interface de uma algoritmo de escalonaneto.
 * 
 * @author nayalison
 */
public interface AlgoritmoEscalonamento extends Runnable {
	
	/**
	 * Escalona os processo contidos na lista passada por parâmetro.
	 * 
	 * @param listaProcessos lista de processos
	 * @throws BusinessException {@link BusinessException}
	 */
	void escalonar(List<Processo> listaProcessos) throws BusinessException;
	
	void parar();

}
