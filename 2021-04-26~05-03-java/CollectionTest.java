package Ysj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CollectionTest {

	public static void main(String[] args) {

		Map<String, String> aPhone = new TreeMap<>(); // a�� ����ȣ
		aPhone.put("������", "010-1111-2222");
		aPhone.put("������", "010-2222-3333");
		aPhone.put("������", "010-4444-5555");
		
		Map<String, String> bPhone = new TreeMap<>(); // b�� �л� ����ȣ
		bPhone.put("ȫ�浿", "010-5555-6666");
		bPhone.put("ȫ�浿2", "010-6666-7777");
		bPhone.put("ȫ�浿3", "010-7777-8888");
		
		
		Map<String, Map<String, String>> phoneBook = new HashMap<>();
		
		phoneBook.put("WDJ-A", aPhone);
		phoneBook.put("WDJ-B", bPhone);
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("a�� or b��?, quit is Q.");
			String ban = sc.nextLine();
			if(ban.equalsIgnoreCase("Q")) {
				break;
			}
			Map<String, String> banPB = phoneBook.get(ban);
			if(banPB == null) {
				System.out.println("�׷� ���� �����ϴ�.");
				continue;
			}
			
			System.out.println("����?");
			String who = sc.nextLine();
			String phone = banPB.get(who);
			Map<String, String> whoPB = phoneBook.get(who);
			if(phone == null) {
				System.out.println("�׷� ����� �����ϴ�.");
				continue;
			}
			System.out.println(ban + "�� " + who + ": " + phone);
		}
		sc.close();
		System.out.println("���α׷� ����");

		

		

		
		
		
		
		

	}

}
