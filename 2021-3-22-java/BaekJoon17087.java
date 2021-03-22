import java.util.Scanner;

public class BaekJoon17087 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
        	a[i] = sc.nextInt() - s;
        }
        
        for(int i = 0; i < n-1; i++) {
             System.out.println( gcd(a[i],a[i+1]));
        }
        
	}
	static int gcd(int a, int b) {
		if(b == 0) return a;
		else return gcd(b, a%b);
	}

}
