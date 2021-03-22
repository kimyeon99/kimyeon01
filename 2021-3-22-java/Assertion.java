package scpark;

import java.util.Scanner;

public class Assertion {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("날짜 입력");
			int date = sc.nextInt();
			
			
			 // assert(date >= 1 && date <= 31); // date가 1이상 31이하인지 확인해줌. 아니면 오류나게 함 지금은 aasert 옵션을 주지 않아 없는 코드 취급.
			assert date >=1 && date <=31 : "잘못된 날짜 : " + date; // << 이렇게도 가능
			
			
			System.out.printf("입력된 날짜는 %d 입니다.", date); // f = formatted text
			
		}
	}
}
