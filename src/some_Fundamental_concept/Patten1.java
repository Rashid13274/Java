package some_Fundamental_concept;

//import java.util.Scanner;

public class Patten1 {  
	// print 1 to 10 tables
	

	public static void main(String[] args) {
//		for(int i=2;i<=10;i++) {
//			for(int j=1;j<=10;j++) {
//				
//				System.out.print(i*j+" ");
//			}
//			System.out.println();
//		}
		
//		
// 		PATTERN:-
		/*for(int i=0;i<5;i++) {
			for(int j=1;j<10;j++) {
				System.out.print("*");
			}
			System.out.println();
			
			*********
			*********
			*********
			*********
			*********
		}*/
		
		
		
		/*for(int i=1;i<=5;i++) {
			for(int j=1;j<=i;j++) {
				System.out.print("*"+" ");
			}
			System.out.println();
			
			* 
			* * 
			* * * 
			* * * * 
			* * * * * 
		}*/
		
		/*
		for(int i=5;i>=1;i--) {
			for(int j=i;j>=1;j--) {
				System.out.print("*"+" ");
			}
			System.out.println();
			
			* * * * * 
			* * * * 
			* * * 
			* * 
			* 
		}*/
		
	
		// FOR SPACE : THERE IS ith row  for each row space will be 2(i-1);
		// For STAR: THERE IS ith row and N star so (n-1+1) star in each row;
		 
		/*for(int i=1; i<=5;i++) {
			for(int j=i; j<=(i*2-2);j++) {
				System.out.print(" ");
			}
			for(int k=i;k<=5;k++) {
				System.out.print("*");
			
			}System.out.println();
			
			*****
			 ****
			  ***
			   **
			    *
		}*/
				
				
		/*
				for(int i=1;i<=6;i++) {
					for(int j=i;j<8;j++) {
						System.out.print(" ");
						
					}
					for(int k=1;k<=i;k++) {
						System.out.print("*");
					}System.out.println();
				}
				
				   	   *
				      **
				     ***
				    ****
				   *****
				  ******
		*/  
		
		/*
		
		for(int i=1;i<=6;i++) {
			for(int j=i;j<6;j++) {
				System.out.print("  ");
				
			}
			for(int k=1;k<=i;k++) {
				System.out.print("*"+"  ");
			}System.out.println();
			
			
		       	  *  
		        *  *  
		      *  *  *  
		    *  *  *  *  
		  *  *  *  *  *  
		*  *  *  *  *  *  
			
		}*/
		
		
		/*
		Scanner sc =new Scanner(System.in);
		int n=sc.nextInt();
		int rows=2*n-1;
		for(int i=1;i<=rows;i++) {
			if(i<=n) {
				for(int j=1;j<=i;j++) {
				System.out.print("*"+" ");
			}
				System.out.println();
			}else {
				for(int j=1;j<=rows-i+1;j++) {
					System.out.print("*"+" ");
				}
				System.out.println();
		
		
			}*/
			
			
		}
		
	}


