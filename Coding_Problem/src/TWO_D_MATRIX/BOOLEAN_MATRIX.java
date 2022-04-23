package TWO_D_MATRIX;

import java.util.Scanner;

/*
 * Boolean Matrix Problem
You are given a matrix Mat of m rows and n columns. The matrix is boolean so 
the elements of the matrix can only be either 0 or 1. Now, if any row of the matrix contains a 1, 
then you need to fill that whole row with 1. After doing the mentioned operation, you need to print the modified matrix.

Input
The first line of contains m and n denoting number of rows and number of columns.
 Then next m lines contain n elements denoting the elements of the matrix.

Constraints:
1 <= m, n <= 700
Mat[I][j] ∈ {0,1}
Output
In a new line, print the modified matrix.

Example
Input:
5 4
1 0 0 0
0 0 0 0
0 1 0 0
0 0 0 0
0 0 0 1
Output:
1 1 1 1
0 0 0 0
1 1 1 1
0 0 0 0
1 1 1 1
 */

public class BOOLEAN_MATRIX {

	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		 Scanner sc =new Scanner(System.in);
	        int m=sc.nextInt();
	        int n=sc.nextInt();
	        int arr[][]=new int[m][n];
	        for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	                arr[i][j]=sc.nextInt();
	            }
	        }
	         for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	              if(arr[i][j]==1){
	                  for(int k=0;k<n;k++){
	                      arr[i][k]=1;
	                  }
	              }
	            }
	        }
	         for(int i=0;i<m;i++){
	            for(int j=0;j<n;j++){
	                System.out.print(arr[i][j]+" ");
	            }
	            System.out.println();
	        }

	}

}
