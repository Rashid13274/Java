package interview_Problem;

import java.util.Scanner;

public class Question04 {

	public static void main(String[] args) {
	// PATTERN PRACTICE:
		/*
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				System.out.print("*"+" ");
			}
			System.out.println();
			
			* * * * * 
			* * * * * 
			* * * * * 
			* * * * * 
			* * * * * 
			
			*/
		
		/*
		for(int i=0;i<5;i++) {
			for(int j=0;j<=i;j++) {
				System.out.print("* ");
			}
			System.out.println();
			
			* 
			* * 
			* * * 
			* * * * 
			* * * * * 
			
		}
			*/
			
		/*
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				System.out.print("  ");
			}
			for(int j=1;j<=(n-i+1);j++) {
				System.out.print("* ");
			}
			System.out.println();
				
		}
		input.close();
		
	    * * * * * 
        * * * * 
        * * * 
        * * 
        * 
        
        */
	}

}
