package scpark2;

import java.util.stream.IntStream;

public class LinkedListTest {
	   Node head = null;
	   
	   
		private class Node{
			//	Node prev;
				Node next;
				Integer value;
		}
		
	   public void add(Integer value) {
		   Node newNode = new Node();
		   newNode.value = value;
		   if(head == null) {
			   head = newNode;
		   } else {
			   Node temp = head;
			   while(temp.next != null) {
				   temp = temp.next;
			   }
			   temp.next = newNode;
		   }
		   size++;
	   }
	   
	   

	
	public String toString() {
		String result = "";
		Node temp = head;
		while(temp != null) {
			result += temp.value + ", ";
			temp = temp.next;
		}
		result += "]";
		return result;
	}
	
	public int size() {
		return size;
	}
	
	public Integer get(int index) {
		if(index >= size) return  null;
		int idx = 0;
		Node temp = head;
		while(idx < index) {
			temp = temp.next;
			idx++;
		}
		return temp.value;
	}
	
	public void remove() {
		Node temp = head;
		if(size <= 1) {
			head = null;
			return;
		}
		while(temp.next.next != null) {
			temp = temp.next;
		}
		temp.next = null;
		
		size--;
	}
	
	public static void main(String[] args) {
       LinkedListTest list = new LinkedListTest();
       IntStream.rangeClosed(1, 10).forEach(i -> list.add(i));
       System.out.println(list);
       for(int i = 0; i < list.size(); i++) {
    	   System.out.print(list.get(i) + " ");
       }
       System.out.println();
       
       list.remove();
       list.remove();
       System.out.println(list);
       int length = list.size(); // 자바에서는 이렇게 해서 for 안에 적어여함!!
       
       for(int i = 0; i < length; i++) {
    	   list.remove();
    	   System.out.println(list);
       }
	}

}
