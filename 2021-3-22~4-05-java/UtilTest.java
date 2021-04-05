package scpark;

import java.util.Arrays;

public class UtilTest {

	public static void main(String[] args) {

		Integer[] arr = {3, 4, 2, 10, 38, 76, 92, 124, 13, 24};
		Double[] arr2 = {3.0, 4.1, 58.8, 76.3};
		String[] arr3 = {"동해물과","백두산이","마르고"};
		Student[] arr4 = {new Student("가", 78), new Student("나",34),
				         new Student("다", 66), new Student("라",67)};
		
		Util.println(arr4);
		System.out.println("최댓값 : " + Util.getMax(arr4));
		
		Util.printValueOf5Times(12);
		Util.printValueOf5Times(12.3);
	//	Util.printValueOf5Times("suzuki"); 안 됨.


		Util.printSum(Arrays.asList(arr)); // arr를 list타입으로 바꿔줌!! 
		                                   // 그 list를 util에 있는 메소드로 받아침.
		
	}

}
