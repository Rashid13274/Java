package interview_Problem01;

public class Recursion {
	

	public static void main(String[] args) {
		System.out.println(sumNatural(5));
		System.out.println(factorial(4));
		System.out.println(betterPow(2, 5));
		System.out.println("steps"+count);

	}
	// add first 5 natural number using recursion
	static int sumNatural(int n) {
		if(n==1) return 1;
		return n+sumNatural(n-1);
	}
	// to calculate the factorial of natural number;
	static int factorial(int n) {
		if(n==1) return 1;
		return n*factorial(n-1);
	}
	 static int count =0;
	// power of natural of  number
	static int  power(int a, int b) {
		count++;
		if(b==0) return 1;
		return a *power(a, b-1);
	}
	static int betterPow(int a, int b) {
		if(b==0) {
			return 1;
		}
		if(b%2==0)  {
			return  betterPow(a*a, b/2);
		}
			
				return a*betterPow(a,b-1);
	}

}
