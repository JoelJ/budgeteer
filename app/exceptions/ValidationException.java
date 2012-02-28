package exceptions;

/**
 * User: joeljohnson
 * Date: 2/11/12
 * Time: 10:12 PM
 */
public class ValidationException extends RuntimeException {
	public ValidationException(String name, Object value) {
		super("Invalid: " + name + ". Was: " + value);
	}

	public ValidationException(String name, Object value, Object expected) {
		super("Invalid: " + name + ". Was: [" + value + "]. Expected: [" + expected + "].");
	}
}
