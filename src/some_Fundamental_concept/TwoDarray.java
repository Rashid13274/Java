package some_Fundamental_concept;

import java.util.Scanner;

public class TwoDarray {

	public static void main(String[] args) {
//		EXAMPLE:1
		
//		int arr [][] =new int[5][3];
//		int b[][]= {
//				{1,2,3,4,5},
//				{2,3,5,7},
//				{5,6,3}
//				here  array b contains three one dimension array.
//		};
//		System.out.println(b[0][2]);  
//		0th row and 3rd column.
	
		
//		EXAMPLE:2
//		addition  of two matrices;
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter dimension");
		int rows=sc.nextInt();
		int column=sc.nextInt();
		int a[][]=new int [rows][column];
		int b[][]=new int [rows][column];
		
		System.out.println("enter array a");
		for(int i=0;i<rows;i++) {
			for(int j=0;j<column;j++) {
				a[i][j]=sc.nextInt();
			}
		}
		System.out.println("enter array b");
		for(int i=0;i<rows;i++) {
			for(int j=0;j<column;j++) {
				b[i][j]=sc.nextInt();
			}
		}
		int c[][]=new int [rows][column];
		System.out.println("add to array c");
		for(int i=0;i<rows;i++) {
			for(int j=0;j<column;j++) {
				c[i][j]=a[i][j]+b[i][j];
						
			}
		}
		System.out.println("display array c");
		for(int i=0;i<rows;i++) {
			for(int j=0;j<column;j++) {
			System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}
	}

}
