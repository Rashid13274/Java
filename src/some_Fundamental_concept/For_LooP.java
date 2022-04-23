package some_Fundamental_concept;

import java.util.Scanner;

public class For_LooP {
	// FIBONACCI NUMBER: THE SUM OF TWO PREVIOUS NUMBER  OF THAT PATTERN IS CALLED  FIBONACCI SERIES.
	// EX:0,1,1,2,3,5,8,13,24....

	public static void main(String[] args) {

		Scanner sc =new Scanner(System.in);
		int n=sc.nextInt();
		fib(n);
		
		
	}
	public static void fib(int n) {
		int a=0;
		System.out.print(a+" ");
		int b=1;
		System.out.print(b+" ");
		for(int i=0;i<n-2;i++) {
			int c=a+b;
			a=b;
			b=c;
			System.out.print(c+" ");
			
		}
		
	}

}
