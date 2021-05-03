package Ysj;

public class TryCatch3{

	public void myAge(int age) throws AgeException{
		try {
			if(age < 0) {
				throw new AgeException();
			}
			System.out.println(age);
		}catch(AgeException e) {
			System.err.println("오류 발생 ㅠ");
		}
		 
	}
	
	public static void main(String[] args) throws AgeException{
		TryCatch3 tc = new TryCatch3();
		tc.myAge(5);
		tc.myAge(-5);
		
	}
}
