package scpark2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class ListTest {
	
	public static void main(String[] args) {

	    
		countDisticWord();
	}
	
	private static void test2() {
		 // List<Integer> list = new ArrayList<>();
		List<Integer> list = new LinkedList<>();

		IntStream.rangeClosed(1, 10000).forEach(i ->
          list.add(i)	
     	);
		
		long startTime = System.currentTimeMillis();
		long endTime = System.currentTimeMillis();
		
		for(int i = 0; i < 10000; i++) {
			list.add(100, (i+1)*1000);
		}
		
		System.out.println("elapsed time:" + (endTime - startTime));
	}
	
	private static void test() {
	    // List<String> list = new ArrayList<>();
	    List<String> list = new LinkedList<>();
	    
	    list.add("Milk");
	    list.add("Bread");
	    list.add("Apple");
	    
	    list.add(1, "Apple"); // �߰�(�з���)
	    System.out.println(list);
	    
	    list.set(2, "Grape"); // ����(�� �з���)
	    System.out.println(list);
	    
	    list.remove(3); // ����
	    System.out.println(list);
	    
	    for(int i = 0; i < list.size(); i++) {
	    	System.out.print(list.get(i) + " ");
	    }
	    
	    System.out.println();
	    
	    for(String s : list) {
	    	System.out.print(s + " ");
	    }
	    
	    System.out.println();
	    
	    Iterator<String> iter = list.iterator();
	    while(iter.hasNext()) { // hasNext�� ������ ������ true, ������ false�̴�.
	    	iter.next();
	    }
	}
	
	private static void test3() {
		
		// Set<String> set = new HashSet<>(); // ��������, �ߺ�������� ����!!
		// Set<String> set = new LinkedHashSet<>(); // ���� �ִ� hashset!!(�ߺ�x)
		// ������ �Էµ� ������ ����ȴ�.
		// TreeSet�� �ߺ� ���x, ���� ���� �����Ѵ�. ���� ������� ��µȴ�.
		Set<String> set = new TreeSet<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2); // �������(������������) ����
				// return o2.compareTo(o1); // �������� ����
			}
		}); 
		
		String[] strArr = {"�ܾ�", "�ߺ�", "����", "�ߺ�"};
		for(String s : strArr) {
			if(set.add(s) == false) {
				System.out.println(s + "�� �̹� �����Ѥ� ��!!!!!!!");
			}
		}
		
		System.out.println(set);
		
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next());
		}
		System.out.println();
		// iter.next(); - error
		iter = set.iterator();
		System.out.println(iter.next());
		System.out.println("��...");
	}
	
	private static void genLotto() {
		Set<Integer> set = new TreeSet<>();
		Iterator<Integer> iter = set.iterator();
		
		Random ran = new Random();
	/*	for(int i = 0; set.size() < 6; i++) {
			set.add(ran.nextInt(45) + 1);
		} */
		
		while(iter.hasNext()) {
			set.add(ran.nextInt(45) + 1);
		}
		System.out.println(set);
	}
	
	private static void countDisticWord() {
		// Set<String> set = new HashSet<>();
		Map<String, Integer> map = new HashMap<>();
		int count = 0; // �ߺ� ����
		File file = new File("C:/Users/������/eclipse-workspace/Scpark/bin/scpark2/wordbook.txt");
		try(BufferedReader br = new BufferedReader(new FileReader(file))){ // try �ȿ� ���� �ڵ����� �ݾ��༭ ���ϴ�.
			String s = null;
			while((s = br.readLine()) != null) {
				System.out.println(s.trim() + " ");
			    Integer n = map.get(s);
			    if(n == null) {
			    	map.put(s, 1); 
			    } else {
			    	map.put(s, n+1);
			    }
			}
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(count);
	    // �Ʒ��� �ߺ� ���� �ι�° ���.. iter�� set�� ����.
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Integer value = map.get(key);
			System.out.println(key + " : " + value + " , ");
		}
	}
	
	
}
