package TWO_D_MATRIX;

import java.util.Scanner;

/*
 * Alt matrix sum
You are given a chessboard of size N x N, where the top left square is black. Each square contains a value. Find the sum of values of all black square and all white squares.

Remember that in a chessboard black and white squares alternate. Input User task: Input consists on N and the the numbers in the NxN matrix. You need to print out the sum of black squares and then white squares in the next line.

Constraints:-
1 <= N <= 1000
1 <= Matrix[i][j] <= 100000
Output
Print two lines, first line containing the sum of black squares and second line containing the sum of white squares.

Example
Input:
3
1 2 3
4 5 6
7 8 9
Output:
25
20
Explanation:-
black square contains 1, 3, 5, 7, 9; sum = 25

white square contains 2, 4, 6, 8; sum = 20
 */

public class ALT_MATRIX_SUM {

	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		Scanner sc=new Scanner(System.in);
        int m=sc.nextInt();
        int arr[][]=new int [m][m];
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        long sumOfWhiteSquires=0;
        long sumOfBlackSquires=0;
         for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                // black_sum: the cell which is divisible by 2 ;
                if((i+j)%2==0){
                    sumOfBlackSquires+=arr[i][j];
                }else{
                    sumOfWhiteSquires+=arr[i][j];
                }
            }
        }
        System.out.println(sumOfBlackSquires);
        System.out.println(sumOfWhiteSquires);

	}

}
