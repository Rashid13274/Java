package interview_Problem01;

public class Sum_of_three_digit {

	public static void main(String[] args) {
		// sum of three digit having one digit common between them  either its 5 or any digit;
		
		int number =104;
		System.out.println(sum(number));

	}
	public static int   sum(int number) {
		int second;
		int first=0;
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
