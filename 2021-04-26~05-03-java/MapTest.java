package scpark3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MapTest {

	public static void main(String[] args) {
		test2();
	}
	
	private static void test1() {
	//	ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Student> list = new ArrayList<>();
		Random random = new Random();
		for(int i = 0; i < 10; i++) {
		//	list.add(random.nextInt(100)+1);
			list.add(new Student("이름"+i, random.nextInt(100)+1));
		}
		System.out.println(list);
		
		sort(list);
		
		System.out.println("정렬 후 : ");
		System.out.println(list);
		
	//	Collections.sort(list);

	}
	
	private static void test2() {
		ArrayList<Integer> list = new ArrayList<>();
		Random random = new Random();
		for(int i = 0; i < 10; i++) {
		//	list.add(random.nextInt(100)+1);
			list.add(i, random.nextInt(100)+1);
		}
		System.out.println(list);
		
		sort2(list);
		
		System.out.println("정렬 후 : ");
		System.out.println(list);
	}
	
	private static void sort(List<Student> list) {
		/*
		 * selection sort
		 */
		for(int i = 0; i < list.size(); i++) {
			int min = i; // j는 i+1이고 i는 정의된 가장 작은 수이므로 min은 항상 작은 수로 정의된다.
			for(int j = i+1; j < list.size(); j++) {
				if(list.get(min).compareTo(list.get(j))>0) { // 양수는 앞에 것이 더 크다는 것
					min = j;
				}
			}
	//		swap(list.get(min), list.get(i));
			swap(list, min, i);
		}
	}
	
	private static void swap(List<Student> list, int i, int j) {
		Student temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	private static <T extends Comparable<T>> void sort2(List<T> list) {

		for(int i = 0; i < list.size(); i++) {
			int min = i; // j는 i+1이고 i는 정의된 가장 작은 수이므로 min은 항상 작은 수로 정의된다.
			for(int j = i+1; j < list.size(); j++) {
				if(list.get(min).compareTo(list.get(j))>0) { // 양수는 앞에 것이 더 크다는 것
					min = j;
				}
			}
			swap2(list, min, i);
		}
	}
	
	private static <T extends Comparable<T>>void swap2(List<T> list, int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
}

class Student implements Comparable<Student>{
	private String name;
	private int grade;
	
	public Student(String name, int grade) {
		super();
		this.name = name;
		this.grade = grade;
	}
	
	@Override
	public int compareTo(Student s) {
		return this.grade - s.grade;
	}
	
	@Override	
	public String toString() {
		return "name: " + name + " grade: " + grade;
	}
}
