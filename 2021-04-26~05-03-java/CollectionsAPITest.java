package scpark3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsAPITest {

	public static void main(String[] args) {
		
		//sortTest();
		//shuffleTest(); // 섞기
		binarySearch();
	}
	
	public static void sortTest() {
		String[] sample = {"I" , "walk", "the" , "line"}; // -> I line the walk
		List<String> list = Arrays.asList(sample); // 배열 -> 리스트
		Collections.sort(list, (o1, o2) -> o1.compareToIgnoreCase(o2));
		// 두번째 인자
		System.out.println(list);
	}
	
	public static void shuffleTest() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		System.out.println(list);
		System.out.println("After Shuffling");
		
		Collections.shuffle(list); // shuffling!!
		System.out.println(list);

}
	public static void binarySearch() {
		// 수 만개의 원소 속에서 내가 찾고자하는 값이 있는지, 어디있는지 찾아보자.
		// 만약 리스트의 원소가 정렬되어 있다면 내가 원하는 값을 찾을 때
		// 최대 몇 번의 비교를 해야할까?
	   // 1~100 중에 찾는 값이 35라고 하자. 그러면 1~100 의 중간 값이 50이다.	
		// 50과 35를 비교했을 때 35는 조건에 부합한다. 고로 51~100까지의 수는
		// 제외를 하고 다시 1~50에서 반을 나누는 것을 계속하여 최종적으로
		// 35를 찾는 것이다. (전제조건 : 원소의 정렬)
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		int idx = Collections.binarySearch(list, 7);
		if(idx >= 0) {
			System.out.println(idx+1);
		} else {
			System.out.println("7은 리스트에 없습니다.");
		 }
		}
}

class MyString implements Comparator<T>{
	
}
