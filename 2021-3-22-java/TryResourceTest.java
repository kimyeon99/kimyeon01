package scpark;
import java.io.*;

public class TryResourceTest {
	
	public static void writeList() {
		PrintWriter out = null; // try 안에 선언하면 지역변수 되므로 밖에 선언해야함
		// FileOutputStream fout = null;
		try {
	     	out = new PrintWriter("outfile.txt"); // 폴더를 만들어주진 않음을 유의
	    	for(int i = 0; i < 10; i++) {
			  out.print("배열원소" + i + " = " + i);
		    }
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			 if(out != null) out.close(); // close 해줘야함 null타입 인셉션은 대표적은 런타임 인셉션이다?
			 System.out.println("파일을 닫았습니다.");
		}
		
		
	}

	public static void main(String[] args) {
        writeList();
	}

}
