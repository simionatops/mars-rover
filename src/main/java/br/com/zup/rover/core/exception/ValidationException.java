package br.com.zup.rover.core.exception;

/**
 * Exceção de validação
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 8312893080570900874L;

	public ValidationException(String message) {
		super(message);
	}
}