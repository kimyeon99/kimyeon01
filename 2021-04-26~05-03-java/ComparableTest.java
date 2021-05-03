package Ysj;

import java.util.Arrays;

public class ComparableTest {
			public static void main(String[] args) {
				Student student[] = new Student[5];
				//순서대로 "이름", 학번, 학점
				student[0] = new Student("Dave", 20120001, 4.2);
				student[1] = new Student("Amie", 20150001, 4.5);
				student[2] = new Student("Emma", 20110001, 3.5);
				student[3] = new Student("Brad", 20130001, 2.8);
				student[4] = new Student("Cara", 20140001, 4.2);
				Arrays.sort(student);
				for(int i = 0; i<5; i++) {
					System.out.println(student[i]);
				}
			}
		
	}
	class Student implements Comparable<Student>{ // student끼리 비교하므로
		String name; //이름
		int id; //학번
		double score; //학점
		public Student(String name, int id, double score){
			this.name = name;
			this.id = id;
			this.score = score;
		}
		public String toString(){ //출력용 toString오버라이드
			return "이름: "+name+", 학번: "+id+", 학점: "+score;
		}
		@Override
		public int compareTo(Student anotherStudent) {
			//return Integer.compare(id, anotherStudent.id);
			 return (id < anotherStudent.id) ? -1 : ((id==anotherStudent.id) ? 0 : 1);
		} // x - y <= 0 일 경우 자리바꿈x , x - y > 0일 경우 오름차순.
	}
