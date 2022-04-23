package array;

public class Print_1_To_100 {
//	PRINT 1 TO 100 WITHOUT USING LOOP;

	public static void main(String[] args) {
		int n=1;
		printNum(n);
	
		
	}
	public static void  printNum(int n) {
		if(n<=100) {
			System.out.print(n+" ");
			printNum(n+1);
		}
		
	}

}
