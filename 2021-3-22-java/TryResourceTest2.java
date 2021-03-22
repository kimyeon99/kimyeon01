package scpark;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TryResourceTest2 {
	
	public static void writeList() {
		try(PrintWriter out = new PrintWriter("outfile2.txt")) { // 이렇게 하면 printwriter가 끝나면 close 안 해도 자동으로 닫힘
	    	for(int i = 0; i < 10; i++) {
			  out.print("배열원소" + i + " = " + i);
		    }
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
        
	}

}
