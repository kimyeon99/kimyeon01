package scpark3;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsAPITest {

	public static void main(String[] args) {
		
		//sortTest();
		//shuffleTest(); // ����
		binarySearch();
	}
	
	public static void sortTest() {
		String[] sample = {"I" , "walk", "the" , "line"}; // -> I line the walk
		List<String> list = Arrays.asList(sample); // �迭 -> ����Ʈ
		Collections.sort(list, (o1, o2) -> o1.compareToIgnoreCase(o2));
		// �ι�° ����
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
		// �� ������ ���� �ӿ��� ���� ã�����ϴ� ���� �ִ���, ����ִ��� ã�ƺ���.
		// ���� ����Ʈ�� ���Ұ� ���ĵǾ� �ִٸ� ���� ���ϴ� ���� ã�� ��
		// �ִ� �� ���� �񱳸� �ؾ��ұ�?
	   // 1~100 �߿� ã�� ���� 35��� ����. �׷��� 1~100 �� �߰� ���� 50�̴�.	
		// 50�� 35�� ������ �� 35�� ���ǿ� �����Ѵ�. ��� 51~100������ ����
		// ���ܸ� �ϰ� �ٽ� 1~50���� ���� ������ ���� ����Ͽ� ����������
		// 35�� ã�� ���̴�. (�������� : ������ ����)
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			list.add(i);
		}
		int idx = Collections.binarySearch(list, 7);
		if(idx >= 0) {
			System.out.println(idx+1);
		} else {
			System.out.println("7�� ����Ʈ�� �����ϴ�.");
		 }
		}
}

class MyString implements Comparator<T>{
	
}
