package scpark;

import java.util.Scanner;

public class Assertion {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("��¥ �Է�");
			int date = sc.nextInt();
			
			
			 // assert(date >= 1 && date <= 31); // date�� 1�̻� 31�������� Ȯ������. �ƴϸ� �������� �� ������ aasert �ɼ��� ���� �ʾ� ���� �ڵ� ���.
			assert date >=1 && date <=31 : "�߸��� ��¥ : " + date; // << �̷��Ե� ����
			
			
			System.out.printf("�Էµ� ��¥�� %d �Դϴ�.", date); // f = formatted text
			
		}
	}
}
