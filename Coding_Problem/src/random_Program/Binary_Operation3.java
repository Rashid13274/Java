package random_Program;

public class Binary_Operation3 {

	public static void main(String[] args) {
//		int n=133;
//		numberOFbits2(n);
		long n=10;
		System.out.println(numberOFbits2(n));
		 
	}
	
	
	// this function is for decimal value digit.
//	public static  void  numberOFbits(int n) {
//	int res= (int) (Math.floor(Math.log10(n)) +1);
//	System.out.println(res);
//	} 
	
	// THIS FUNCTION COUNT bits IN BIARY NUMBER.
	public static long    numberOFbits2(long n) {
		 long count =0;
		while(n!=0) {
			count++;
			n>>=1;
		}
		return count;
	}

}
