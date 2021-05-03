package Ysj;

import java.io.IOException;

public class TryCatch {

	public static void main(String[] args) {

		try {
			System.out.println(readString());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static String readString() throws IOException {
		byte[] buf = new byte[100];
		System.out.println("문자열 입력");
		System.in.read(buf); // 문자열을 buf 배열에 저장
		return new String( buf);
	}
	


}
