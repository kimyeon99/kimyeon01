package scpark;

import java.util.ArrayList;

public class UtilTest2 {

	public static void main(String[] args) {
        Number n = Integer.valueOf(100); // 인티저 객체로 만들어주는 것임!!
       
        // ArrayList<Number> list = new ArrayList<Integer>; - error
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Double> list2 = new ArrayList<Double>();

        process(list);
        process(list2);
        // process(list2) - error
        
        // 제네릭을 이용하여 데이터타입이 달라도 사용가능하도록 하자.
        // 이 때 쓰는 것이 와일드카드임
        // 제네릭 클래스, 제네릭 메소드, 와일드카드가 있음.
        // 제네릭이 나오기 전에는 오브젝트 타입으로 저장했으나 뺏을 때 데이터타입이 달라 계산에 문제
        // 가 생겨서 힘들었음.
        // 제네릭은 그러한 단점을 보안하기 위해 나옴 !!
       // 실제로 시용할 데이터 타입ㅇ느 객체 생성 시에 type parameter로 받아서 처리
        // 그리고 명시적인 type casting도 안 해도 되도록 하기 위해..
        
        
	}

	public static void process(ArrayList<? extends Number> list) {
		
	}
}
