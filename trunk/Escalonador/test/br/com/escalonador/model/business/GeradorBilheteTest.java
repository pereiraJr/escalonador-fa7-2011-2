package br.com.escalonador.model.business;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.escalonador.model.exception.BusinessException;

public class GeradorBilheteTest extends TestCase{

	@Test
	public void testGerarBilhetes() {
		GeradorBilhete geradorBilhete = new GeradorBilhete();
		List<Integer> lista = geradorBilhete.gerarBilhetes(10);
		for (Integer i: lista) {
			System.out.println(i);
		}
	}
	
	@Test
	public void testHasBilheteSorteio(){
		try {
			GeradorBilhete geradorBilhete = new GeradorBilhete();
			assertFalse(geradorBilhete.hasBilheteSorteio());
			geradorBilhete.gerarBilhetes(10);
			geradorBilhete.obterBilhete();
			assertTrue(geradorBilhete.hasBilheteSorteio());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testHasBilhete(){
		GeradorBilhete geradorBilhete = new GeradorBilhete();
		assertFalse(geradorBilhete.hasBilhete());
		geradorBilhete.gerarBilhetes(10);
		assertTrue(geradorBilhete.hasBilhete());
	}
	
	@Test
	public void testObterBilhete() {
		try {
			GeradorBilhete geradorBilhete = new GeradorBilhete();
			geradorBilhete.gerarBilhetes(1);
			geradorBilhete.obterBilhete();
			assertFalse(geradorBilhete.hasBilhete());
			assertTrue(geradorBilhete.hasBilheteSorteio());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testDevolverBilhete(){
		try {
			GeradorBilhete geradorBilhete = new GeradorBilhete();
			geradorBilhete.gerarBilhetes(1);
			geradorBilhete.obterBilhete();
			assertFalse(geradorBilhete.hasBilhete());
			assertTrue(geradorBilhete.hasBilheteSorteio());
			geradorBilhete.devolverBilhete(1);
			assertTrue(geradorBilhete.hasBilhete());
			assertFalse(geradorBilhete.hasBilheteSorteio());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

}
