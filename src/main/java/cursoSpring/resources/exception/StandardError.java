package cursoSpring.resources.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class StandardError implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String msg;
	private Long timestStamp;
	
	public StandardError(HttpStatus notFound, String msg, Long timestStamp) {
		super();
		this.status = notFound;
		this.msg = msg;
		this.timestStamp = timestStamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimestStamp() {
		return timestStamp;
	}

	public void setTimestStamp(Long timestStamp) {
		this.timestStamp = timestStamp;
	}
	
	
}
