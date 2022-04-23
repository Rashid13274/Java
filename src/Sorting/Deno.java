package Sorting;

public class Deno {

	public static void main(String[] args) {
	int number =104;
	System.out.println(sum(number));
//	print all no between 100 to 1000 where 3 digit no contains atleast one 5.

	}
	public static int   sum(int number) {
		int first=0;
		int second;
		int third;
		int sum1=0;
		int n;
		int m;
		for(int i=100;i<number;i++) {
			first=i%10;
			n=i/10;
			second =n%10;
			m=n/10;
			third=m%10;
			if(first==5 ||second == 5||third ==5) {
				sum1=sum1+i;
			}
			
		}
		return sum1;

	}
}
	


