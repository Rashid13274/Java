package interview_Problem01;

public class Prime_number {

	public static void main(String[] args) {
		 int n=7;
		 System.out.println(prime(n));

	}
	// to check that number is prime or not
	public static boolean  prime(int  n) {
		if(n==0||n==1)  return false;
		else if(n%2==0) return false;
			
			for(int i=3; i<Math.sqrt(n);i+=2) {
				if(n%i==0)  return false;
		
			}return true;
	
			
}	
}
