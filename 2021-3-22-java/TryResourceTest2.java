package scpark;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TryResourceTest2 {
	
	public static void writeList() {
		try(PrintWriter out = new PrintWriter("outfile2.txt")) { // �̷��� �ϸ� printwriter�� ������ close �� �ص� �ڵ����� ����
	    	for(int i = 0; i < 10; i++) {
			  out.print("�迭����" + i + " = " + i);
		    }
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
        
	}

}
