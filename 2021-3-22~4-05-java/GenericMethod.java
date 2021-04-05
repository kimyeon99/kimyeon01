package scpark;

public class GenericMethod {
	
	public static <T> T get(T[] arr, int idx) {
		return arr[idx];
	}
	
	public static Object get2(Object[] arr, int idx) {
		return arr[idx];
	}

}
