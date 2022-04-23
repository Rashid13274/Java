package practice_java_usual_question;

public class Basic_Math_Problem {

	public static void main(String[] args) {
		int n=8;
		isPowerOfTwo(n);
	}
	public static void isPowerOfTwo(int n) {
		int count =0;
		if(n%2==0) {
			while(n>0) {
				n/=2;
				count++;
				
			}
			System.out.println(n);
			int checkValue=(int)Math.pow(2, count);
			System.out.println(checkValue);
			if(checkValue==n) {
				System.out.println(checkValue+" "+n);
			}
		}
	}

}
