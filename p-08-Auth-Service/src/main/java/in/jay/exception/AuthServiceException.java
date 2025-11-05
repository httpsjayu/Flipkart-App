package in.jay.exception;

public class AuthServiceException extends RuntimeException {
	
	public String errCode;
	
	public AuthServiceException() {
		
	}

	public AuthServiceException(String msg,String errCode) {
		super(msg);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	
	
	

}
