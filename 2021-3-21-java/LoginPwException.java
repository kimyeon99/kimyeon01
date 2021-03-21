package scpark;

public class LoginPwException extends LoginException{
	public LoginPwException(String errMsg) {
		super(errMsg);
	}
	
	public LoginPwException(String errMsg, int errCode) {
		super(errMsg, errCode);
	}
}
