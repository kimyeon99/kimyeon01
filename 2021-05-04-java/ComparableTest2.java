package Ysj;

import java.util.Arrays;
import java.util.Comparator;

public class ComparableTest2 {

	public static void main(String[] args) {

		Student2 std[] = new Student2[5];
		std[0] = new Student2("가" , 2 , 3.0);
		std[1] = new Student2("나" , 2 , 4.5);
		std[2] = new Student2("다" , 4 , 2.1);
		std[3] = new Student2("라" , 4 , 1.7);
		std[4] = new Student2("마" , 4 , 3.6);
		
//		Arrays.sort(std, new Comparator<Student2>() {
//			public 
//		});
		
		Arrays.sort(std, new Comparator<Student2>() {
			public int compare(Student2 s1, Student2 s2) {
				return Double.compare(s1.grade, s2.grade);
			}
		});
	
		for(int i = 0; i < 5; i++) {
			System.out.println(std[i]);
		}
	}
}

class Student2 implements Comparable<Student2>{
	String name;
	int number;
	double grade;
	public Student2(String name, int number, double grade) {
		this.name = name;
		this.number = number;
		this.grade = grade;
	}
	public String toString() {
		return "이름: " + name + " 학번: " + number + " 성적: " + grade;
	}
	@Override
	public int compareTo(Student2 std) {
		return (number > std.number) ? 1 : ((number == std.number) ? 0 : -1);
		//return Integer.compare(number, std.number);
	}
	
}
