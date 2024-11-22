package br.com.palm.matriculason.exceptions;

/**
 * Exceção que representa a ausência de recurso solicitado.
 * @author Pedro Alex
 *
 */
public class ResourceNotFoundException extends DefaultApiException {

	private static final long serialVersionUID = 395635821960317595L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
