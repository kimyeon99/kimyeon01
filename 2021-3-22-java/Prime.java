import java.util.Scanner;

public class Prime {
	
	public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int n = sc.nextInt();
      for(int i = 0; i < n; i++) {
    	  long sum = 0;
    	  int a = sc.nextInt();
    	  int[] input = new int[a];
    	  for(int j = 0; j < a; j++) {
    		  input[j] = sc.nextInt();
    	  }
    	  for(int j = 0; j < a-1; j++) {
    		  for(int k = j+1; k < a; k++) {
    			  sum += gcd(input[j],input[k]);
    		  }
    	  }
          System.out.println(sum);

    	}

  }
	static int gcd(int a, int b) {
		while(b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
