package Ysj;

import java.util.Arrays;

public class ComparableTest {
			public static void main(String[] args) {
				Student student[] = new Student[5];
				//������� "�̸�", �й�, ����
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
	class Student implements Comparable<Student>{ // student���� ���ϹǷ�
		String name; //�̸�
		int id; //�й�
		double score; //����
		public Student(String name, int id, double score){
			this.name = name;
			this.id = id;
			this.score = score;
		}
		public String toString(){ //��¿� toString�������̵�
			return "�̸�: "+name+", �й�: "+id+", ����: "+score;
		}
		@Override
		public int compareTo(Student anotherStudent) {
			//return Integer.compare(id, anotherStudent.id);
			 return (id < anotherStudent.id) ? -1 : ((id==anotherStudent.id) ? 0 : 1);
		} // x - y <= 0 �� ��� �ڸ��ٲ�x , x - y > 0�� ��� ��������.
	}
