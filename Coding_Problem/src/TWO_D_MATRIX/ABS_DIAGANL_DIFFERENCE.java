package TWO_D_MATRIX;
import java.util.Scanner;
/*
Diagonal Difference
Given a square matrix, calculate the absolute difference between the sums of its diagonals.

For example, the square matrix is shown below:

1 2 3
4 5 6
9 8 9  
The left-to-right diagonal =1+5+9=15 . The right to left diagonal = 3+5+9 = 17. Their absolute difference is |15-17| = 2.
*/

public class ABS_DIAGANL_DIFFERENCE {

	public static void main(String[] args) {
        // your code here
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int matrix [][]=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=sc.nextInt();
            }
        }
            int sumFirstDiagnal=0, sumSecondDiagnal=0;
            for(int i=0;i<n;i++){
                sumFirstDiagnal+=matrix[i][i];
            }
             for(int i=0;i<n;i++){
                 // second dioganl formula
                sumSecondDiagnal+=matrix[i][n-i-1];
            }
        
        System.out.println(Math.abs(sumFirstDiagnal-sumSecondDiagnal));

	}

}
