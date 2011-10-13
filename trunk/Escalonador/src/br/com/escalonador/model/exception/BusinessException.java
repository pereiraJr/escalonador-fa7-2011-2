/**
 * 
 */
package br.com.escalonador.model.exception;

/**
 * @author nayalison
 *
 */
public class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7097702336228713467L;

	/**
	 * Construtor.
	 */
	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construtor.
	 * 
	 * @param mensagem descrição da exceção
	 * @param throwable {@link Throwable}
	 */
	public BusinessException(String mensagem, Throwable throwable) {
		super(mensagem, throwable);
	}

	/**
	 * Construtor.
	 * 
	 * @param mensagem descrição da exceção
	 */
	public BusinessException(String mensagem) {
		super(mensagem);
	}

	/**
	 * Construtor.
	 * 
	 * @param throwable {@link Throwable}
	 */
	public BusinessException(Throwable throwable) {
		super(throwable);
	}
	

}
