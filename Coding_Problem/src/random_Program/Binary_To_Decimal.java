package random_Program;

public class Binary_To_Decimal {

	public static void main(String[] args) {
		int n=101;
//		binary(n);
//		btod(n);

	}
	public static void binary(int n) {
		if(n==0) {
			System.out.println(0);
		}
		int count =-1;
		int decimalValue=0;
		while(n!=0) {
			int temp=n%10;
			count++;
			int sum=(int) Math.pow(2, count)*temp;
			decimalValue = decimalValue+sum;
			n/=10;
		}
		System.out.println(decimalValue);
	}

}
