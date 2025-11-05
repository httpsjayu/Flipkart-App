package in.jay.exception;

public class ProductServiceException extends RuntimeException {
	
	private String errorCode;
	
	public ProductServiceException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	

}
