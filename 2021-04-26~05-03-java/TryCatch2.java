package Ysj;

import java.util.Scanner;

public class TryCatch2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("��¥�Է� ��");
		int date = input.nextInt();

	    assert date >=1 && date <= 31 : "�߸��� ��¥: " + date;
	    
	    System.out.printf("�Էµ� ��¥�� %d �Դϴ�. \n", date);
	}
}
