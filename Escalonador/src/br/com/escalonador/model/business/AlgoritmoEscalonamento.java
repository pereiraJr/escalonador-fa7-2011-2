package br.com.escalonador.model.business;

import java.util.List;

import br.com.escalonador.model.Processo;
import br.com.escalonador.model.exception.BusinessException;

public interface AlgoritmoEscalonamento {
	
	void escalonar(List<Processo> listaProcessos) throws BusinessException;

}
