package scpark;

import java.util.ArrayList;

public class UtilTest2 {

	public static void main(String[] args) {
        Number n = Integer.valueOf(100); // ��Ƽ�� ��ü�� ������ִ� ����!!
       
        // ArrayList<Number> list = new ArrayList<Integer>; - error
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Double> list2 = new ArrayList<Double>();

        process(list);
        process(list2);
        // process(list2) - error
        
        // ���׸��� �̿��Ͽ� ������Ÿ���� �޶� ��밡���ϵ��� ����.
        // �� �� ���� ���� ���ϵ�ī����
        // ���׸� Ŭ����, ���׸� �޼ҵ�, ���ϵ�ī�尡 ����.
        // ���׸��� ������ ������ ������Ʈ Ÿ������ ���������� ���� �� ������Ÿ���� �޶� ��꿡 ����
        // �� ���ܼ� �������.
        // ���׸��� �׷��� ������ �����ϱ� ���� ���� !!
       // ������ �ÿ��� ������ Ÿ�Ԥ��� ��ü ���� �ÿ� type parameter�� �޾Ƽ� ó��
        // �׸��� ������� type casting�� �� �ص� �ǵ��� �ϱ� ����..
        
        
	}

	public static void process(ArrayList<? extends Number> list) {
		
	}
}
