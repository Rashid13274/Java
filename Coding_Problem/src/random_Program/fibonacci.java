package random_Program;

public class fibonacci {

	public static void main(String[] args) {
		
		System.out.println("fibonacci series");
		int n=10;
		
		fib(n);

	}
	public static void fib(int n) {
		int a=0;
		int b=1;
		while(n>=0) {
			System.out.print(a+" ");
		int  c=a+b;
			a=b;
			b=c;
			
			n--;
		}
		
	}

}
