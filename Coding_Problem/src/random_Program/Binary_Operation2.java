package random_Program;
/* N=5, K=1 OUTPUT: 4
 * 5 IS REPERESENTED AS 101 IN BINARY AND HAS FIRST BIT 1 , SO CLEARING IT WILL BE 100 I.E 4.
 *  N=5, K=2
 *  OUTPUT 5 SINCE 5=101 CLEARING 0 WILL BE 0;  SO NO CHANGES.
 * */

public class Binary_Operation2 {

	public static void main(String[] args) {
		long  n=5;
//		long k=1;
//		clearIthBit(n, k);

		
	}
	public static void clearIthBit(long n, long k) {
		  long mask=1<<(k-1);
		  System.out.println(n&(~mask));
		  
	}


}

