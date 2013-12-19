package br.com.zup.rover.core.validation;

import org.apache.commons.lang.StringUtils;

import br.com.zup.rover.core.exception.ValidationException;

/**
 * Mecanismo de validações
 */
public final class ValidationEngine {

	public static void checkNotEmpty(Object object, String message) {

		boolean empty = false;

		if (object instanceof String) {
			empty = StringUtils.isEmpty((String) object);
		}

		if (empty) {
			throw new ValidationException(message);
		}
	}

	public static void checkNotNull(Object object, String message) {

		if (object == null) {
			throw new ValidationException(message);
		}
	}

	public static void checkPositive(Number number, String message) {

		checkNotNull(number, message);
		if (Math.signum(number.doubleValue()) > 0) {
			throw new ValidationException(message);
		}
	}

	public static void checkNegative(Number number, String message) {

		checkNotNull(number, message);
		if (Math.signum(number.doubleValue()) < 0) {
			throw new ValidationException(message);
		}
	}

	public static void checkTrue(Object object, String message) {

		boolean value = false;

		if (object instanceof Boolean && (Boolean) object) {
			value = true;
		}

		if (value) {
			throw new ValidationException(message);
		}
	}
}