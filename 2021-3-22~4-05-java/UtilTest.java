package scpark;

import java.util.Arrays;

public class UtilTest {

	public static void main(String[] args) {

		Integer[] arr = {3, 4, 2, 10, 38, 76, 92, 124, 13, 24};
		Double[] arr2 = {3.0, 4.1, 58.8, 76.3};
		String[] arr3 = {"���ع���","��λ���","������"};
		Student[] arr4 = {new Student("��", 78), new Student("��",34),
				         new Student("��", 66), new Student("��",67)};
		
		Util.println(arr4);
		System.out.println("�ִ� : " + Util.getMax(arr4));
		
		Util.printValueOf5Times(12);
		Util.printValueOf5Times(12.3);
	//	Util.printValueOf5Times("suzuki"); �� ��.


		Util.printSum(Arrays.asList(arr)); // arr�� listŸ������ �ٲ���!! 
		                                   // �� list�� util�� �ִ� �޼ҵ�� �޾�ħ.
		
	}

}
