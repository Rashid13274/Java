package random_Program;
/// 110 -->111 WE NEED ONLY 1S  TO MAKE A TO B.
// HERE WE'RE CHANGING SMALLER TO BIGGER.

public class Bits_Required_To_Convert_A_To_B {

	public static void main(String[] args) {
		int a=5; // 1010
		int b=10; // 1111 
		
//		bitsRequired(a, b);
	int n=5;
	bits(n);
		
	}
	public static void bitsRequired(int a, int b) {
		int count=0;
		int res=a^b; //0101
		while(res!=0) {
			count++;
			res=res>>1;
			
		}
		System.out.println(count);
	}
	public static void bits(int n) {
		int  count=0;
		while(n>0) {
			count++;
			n>>=1;
			
		}
		System.out.println(count);
	}
	

	

}
