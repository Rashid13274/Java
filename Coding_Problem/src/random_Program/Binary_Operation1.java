package random_Program;

// SET ith binary  position.
// input n=10; k=2;
// output: n=14
// 10=1010
// after  2nd bit set 1110 i.e 14

public class Binary_Operation1 {
	

	public static void main(String[] args) {
		
		long n=6;
//		long k=1 ;
//		 System.out.println(setithBinary(n, k)); 

	}
	public static long   setithBinary(long n, long k) {
		 return (n | 1<<(k-1));
	}


}
