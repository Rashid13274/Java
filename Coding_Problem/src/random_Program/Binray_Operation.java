package random_Program;

public class Binray_Operation {
	/* GIVEN A NUMBER  N AND K(1<=K<=32), FIND THE  VALUE OF K-TH BIT  IN  THE  BINARY REPERESENTION ON N. BITES ARE NUMBERERED
	 * FROM RIGHT(LEAST SIGNIFICANT  BIT) TO LEFT (MOST SIGNIFICANT BIT).
	 * INPUT: N=13, K=2
	 * OUTPUT: 0
	 * N=13 ==1101;
	 * 
	 * 1<<(K-1): EX 1<<(3-1) ==1<<2  i.e 0000....100( 1 and followed by two zero)
	 * */

	public static void main(String[] args) {
	 long  n=13  ; // 1101
	 long k=3; // second bit from right  is 0;
		
		findIthBit(n,k);
	}
	public static void  findIthBit(long n, long k) {
	System.out.println((n&(1<<(k-1)))>>(k-1)) ;
		 
	
	}
}
