/**
 * 
 */
package br.com.escalonador.model.business;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.com.escalonador.model.exception.BusinessException;

/**
 * @author nayalison
 *
 */
public class GeradorBilhete {
	
	private List<Integer> listaBilhetes;
	private List<Integer> listaBilhetesDisponiveis;
	Random rand = new SecureRandom(); 
	
	public int obterBilhete() throws BusinessException{
		if(hasBilhete()) {
			Integer bilhete = listaBilhetesDisponiveis.get(0);
			listaBilhetesDisponiveis.remove(bilhete);
			listaBilhetes.add(bilhete);
			return bilhete;
		} else {
			throw new BusinessException("A lista de bilhetes esta vazia");
		}
	}
	
	public int sortearBilhete() throws BusinessException{
		if(hasBilheteSorteio()) {
			Collections.shuffle(listaBilhetes, rand);
			return listaBilhetes.get(0);
		} else {
			throw new BusinessException("A lista de bilhetes para sorteio esta vazia");
		}
		
	}
	
	public List<Integer> obterBilhete(final int qtdBilhetes) throws BusinessException{
		if(qtdBilhetes != 0) {
			List<Integer> listaBilhetes = new ArrayList<Integer>();
			for(int i=1; i<= qtdBilhetes; i++){
				listaBilhetes.add(obterBilhete());
			}
			return listaBilhetes;
		} else {
			throw new BusinessException("Quantidade de bilhetes ibnválida");
		}
	}
	
	public void devolverBilhete(Integer bilhete) {
		listaBilhetesDisponiveis.add(bilhete);
		listaBilhetes.remove(bilhete);
	}
	
	public void devolverBilhetes(List<Integer> listaBilhetes) throws BusinessException {
		if(listaBilhetes != null && !listaBilhetes.isEmpty()) {
			for(Integer i: listaBilhetes) {
				devolverBilhete(i);
			}
		} else {
			throw new BusinessException("Lista de bilhetes nula ou vazia.");
		}
	}
	
	public List<Integer> gerarBilhetes(final int qtdBilhetes){
		listaBilhetes = new ArrayList<Integer>();
		listaBilhetesDisponiveis = new ArrayList<Integer>();
		for(int i =1; i <= qtdBilhetes; i++) {
			listaBilhetesDisponiveis.add(i);
		}
		Collections.shuffle(listaBilhetesDisponiveis, rand);
		return listaBilhetesDisponiveis;
	}
	
	public boolean hasBilhete(){
		boolean hasBilhete = false;
		if(listaBilhetesDisponiveis != null && !listaBilhetesDisponiveis.isEmpty()) {
			hasBilhete = true;
		}
		return hasBilhete;
	}
	
	public boolean hasBilheteSorteio(){
		boolean hasBilhete = false;
		if(listaBilhetes != null && !listaBilhetes.isEmpty()) {
			hasBilhete = true;
		}
		return hasBilhete;
	}

}
