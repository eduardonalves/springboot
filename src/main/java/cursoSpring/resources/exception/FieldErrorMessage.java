package cursoSpring.resources.exception;

import java.io.Serializable;

public class FieldErrorMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fildName;
	private String message;
	public FieldErrorMessage() {
		
	}
	public FieldErrorMessage(String fildName, String message) {
		super();
		this.fildName = fildName;
		this.message = message;
	}
	public String getFildName() {
		return fildName;
	}
	public void setFildName(String fildName) {
		this.fildName = fildName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
