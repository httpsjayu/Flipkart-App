
package in.jay.exception;


public class ReportServiceException extends RuntimeException{
	
	private String errorCode;

	public ReportServiceException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
