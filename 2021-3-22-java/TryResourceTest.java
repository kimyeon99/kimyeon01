package scpark;
import java.io.*;

public class TryResourceTest {
	
	public static void writeList() {
		PrintWriter out = null; // try �ȿ� �����ϸ� �������� �ǹǷ� �ۿ� �����ؾ���
		// FileOutputStream fout = null;
		try {
	     	out = new PrintWriter("outfile.txt"); // ������ ��������� ������ ����
	    	for(int i = 0; i < 10; i++) {
			  out.print("�迭����" + i + " = " + i);
		    }
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			 if(out != null) out.close(); // close ������� nullŸ�� �μ����� ��ǥ���� ��Ÿ�� �μ����̴�?
			 System.out.println("������ �ݾҽ��ϴ�.");
		}
		
		
	}

	public static void main(String[] args) {
        writeList();
	}

}
