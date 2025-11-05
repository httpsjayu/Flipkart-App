package in.jay.exception;

public class CartExceptionService extends RuntimeException{
	
	private String errCode;
	
	public CartExceptionService(String message,String errCode) {
		super(message);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	

}
