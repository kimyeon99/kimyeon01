package scpark3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTest {

	public static void main(String[] args) {
		String[] sample = {"I" , "walk", "the" , "line"};
		List<String> list = Arrays.asList(sample); // 배열 -> 리스트
		Collections.sort(list);
		System.out.println(list);
	}

}
