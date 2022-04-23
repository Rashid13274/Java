package interview_Problem01;

public class Number01 {

	public static void main(String[] args) {
		
		int n=4;
		// Iteratively Factorial
		int sum =1;
		for(int i=1;i<=n;i++){
			sum*=i;
			
		}
		System.out.println(sum);
	}
	// RECURSIEVLY FACTORIAL
	
//	public static int  fact(int n) {
//		if(n==0|| n==1) {
//			return 1;
//		}return n*fact(n-1);
//	}
	

}
