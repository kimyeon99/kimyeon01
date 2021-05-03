package Ysj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CollectionTest {

	public static void main(String[] args) {

		Map<String, String> aPhone = new TreeMap<>(); // a반 폰번호
		aPhone.put("예승재", "010-1111-2222");
		aPhone.put("장재현", "010-2222-3333");
		aPhone.put("김태헌", "010-4444-5555");
		
		Map<String, String> bPhone = new TreeMap<>(); // b반 학생 폰번호
		bPhone.put("홍길동", "010-5555-6666");
		bPhone.put("홍길동2", "010-6666-7777");
		bPhone.put("홍길동3", "010-7777-8888");
		
		
		Map<String, Map<String, String>> phoneBook = new HashMap<>();
		
		phoneBook.put("WDJ-A", aPhone);
		phoneBook.put("WDJ-B", bPhone);
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("a반 or b반?, quit is Q.");
			String ban = sc.nextLine();
			if(ban.equalsIgnoreCase("Q")) {
				break;
			}
			Map<String, String> banPB = phoneBook.get(ban);
			if(banPB == null) {
				System.out.println("그런 반은 없습니다.");
				continue;
			}
			
			System.out.println("ㄴㄱ?");
			String who = sc.nextLine();
			String phone = banPB.get(who);
			Map<String, String> whoPB = phoneBook.get(who);
			if(phone == null) {
				System.out.println("그런 사람은 없습니다.");
				continue;
			}
			System.out.println(ban + "의 " + who + ": " + phone);
		}
		sc.close();
		System.out.println("프로그램 종료");

		

		

		
		
		
		
		

	}

}
