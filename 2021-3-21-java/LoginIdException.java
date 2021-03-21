package scpark;

public class LoginIdException extends LoginException{

	public LoginIdException(String errMsg) {
		super(errMsg);
	}
	
	public LoginIdException(String errMsg, int errCode) {
		super(errMsg, errCode);
	}
}
