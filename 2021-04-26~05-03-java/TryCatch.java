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
		System.out.println("���ڿ� �Է�");
		System.in.read(buf); // ���ڿ��� buf �迭�� ����
		return new String( buf);
	}
	


}
