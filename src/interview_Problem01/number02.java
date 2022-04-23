package interview_Problem01;
// COUNT OF TRAILING ZEROS IN FACTORIAL OF A NUMBER;
// GIVEN AN INTEGER N ,WRITE A FUNCTION THAT RETURNS COUNT OF TRAILING ZEROS IN N!
// EX:-
// INPUT: N=5
//OUTPUT:1
// INPUT: N=20;
// OUTPUT: 5;
// FACTORIAL OF 20 IS 24322902008176640000
// FOUR TRAILING ZEROS.


public class number02 {

	public static void main(String[] args) {
		int n=20;
		int result=0;
		for(int i=5;i<=n;i*=5) {
			result=n/5;
		}
		System.out.println(result);
		

	}

}
