package scpark;

public class LoginException extends Exception {
	private int errorCode;
	public LoginException(String errMsg) {
		super(errMsg);
	}
	
	public LoginException(String errMsg, int errorCode) {
		super(errMsg);
		this.errorCode = errorCode;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

}
