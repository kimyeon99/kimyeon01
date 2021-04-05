package scpark;

import java.util.ArrayList;

public class StaticTest {

	private int n1, n2;

	public static void main(String[] args) {
         StaticTest st = new  StaticTest(10, 20);
         System.out.println(st.sum());
         System.out.println(StaticTest.sum(10, 30));
		
	}
	
	public StaticTest(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public int sum() {
		return n1+n2;
	}
	
	// 아래의 sum1, sum2, sum3는 제네릭의 표현만 다르다.
	
	public static <T extends Number> Double sum(T a, T b) {
	 //	return a + b; - 어떤 데이터타입은 더하기를 할 수 없기 때문에 제네릭이라도 오류가 뜬다.
		return (a.doubleValue() + b.doubleValue());
	}
	
	public static <T extends Number> Double sum2(ArrayList<T> arr) {
		Double total = 0.0;
		for(int i = 0; i < arr.size(); i++) {
			total += arr.get(i).doubleValue();
		}
		return total;
	}
	
	public static Double sum3(ArrayList<? extends Number> arr) {
		Double total = 0.0;
		for(int i = 0; i < arr.size(); i++) {
			total += arr.get(i).doubleValue();
			}
		return total;
	}

}
