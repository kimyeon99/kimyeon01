package scpark;

import java.util.List;

public class Util {

	public static <T extends Comparable<T>> T getMax(T[] arr) {
		boolean r = 1 > 2;
		T max = arr[0];
		for(int i = 1; i < arr.length; i++) {
			if(max.compareTo(arr[i]) < 0) max = arr[i];
		}
		
		return max;
	}
	
	
	
	public static <T extends Number> void printValueOf5Times(T value) {
		Double result = value.doubleValue() * 5;
		System.out.println(result);
	}
	
	
	
	public static <T extends Number> void printSum(List<T> list) {
		Double sum = 0.0;
		for (T val : list) sum += val.doubleValue();
		
		System.out.println("sum : "  + sum);
	}
	
	// 위와 아래는 표현만 다르고 기능은 같음.
	public static void printSum2(List<? extends
			                                         Number> list) {
		Double sum = 0.0;
		for (Number val : list) sum += val.doubleValue();
		
		System.out.println("sum : "  + sum);
	}
	
	
	
	public static <T extends Number> void printSum3(List<T> list) {
		Double sum = 0.0;
		for (T val : list) sum += val.doubleValue();
		
		System.out.println("sum : "  + sum);
	}
	
	
	
	
	
	
	public static <T> void println(T[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + " ");
		}
		System.out.println("\n");
	}
}
