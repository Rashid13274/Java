package some_Fundamental_concept;

import java.util.Scanner;

public class Power_Series {

//	evaluate the power of any number  by any times;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
	int c=1;
	
	while(a>0) {
		c *=b;
		a--;
		
	}
	System.out.println(c);
}

}
