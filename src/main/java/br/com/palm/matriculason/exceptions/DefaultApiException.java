package br.com.palm.matriculason.exceptions;

/**
 * Exception generica para ser usada na API
 * @author Pedro Alex
 *
 */
public class DefaultApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DefaultApiException(String msg) {
		super(msg);
	}

	public DefaultApiException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
