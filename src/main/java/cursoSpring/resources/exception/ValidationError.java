package cursoSpring.resources.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ValidationError extends StandardError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<FieldErrorMessage> errors = new ArrayList<>();

	public List<FieldErrorMessage> getError() {
		return errors;
	}

	public ValidationError(HttpStatus notFound, String msg, Long timestStamp) {
		super(notFound, msg, timestStamp);
		// TODO Auto-generated constructor stub
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldErrorMessage(fieldName, message));
	}
}
