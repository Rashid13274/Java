package array;

public class Matrix_problem2 {

	public static void main(String[] args) {
//		int arr[][]=new int [][] {
//			{1,2,3,4,5},
//			{3,4,5,6,7},
//			{9,8,6,5,2}
//			
//		};
//		int row=2;
//		int cols=4;
//		int target =1;
//		System.out.println(matrix(arr,9,2,4));
//
//	}
//	public static boolean matrix(int [][]arr, int target, int row, int cols) {
//		for(int i=0;i<row;i++) {
//			for(int j=0;j<cols;j++) {
//				if(arr[i][j]==target) {
//					
//					return true;
//				}
//			}
//		}
//		return false;
		
		
		
		
		
		int mat[][] = {{10, 20, 30, 40},
				{15, 25, 35, 45},
				{27, 29, 37, 48},
				{32, 33, 39, 50},
				{50, 60, 70, 80}};
	if (search(mat, 5, 4, 29))
		System.out.println("Yes");
	else
		System.out.println("No");
		
	}
	// Java program to search an
	// element in row-wise and
	// column-wise sorted matrix

	static final int MAX = 100;

	/* Searches the element x
	in mat[m][n]. If the element
	is found, then prints its
	position and returns true,
	otherwise prints "not found"
	and returns false */
	static boolean search(int mat[][], int m,
						int n, int x)
	{
		
		// set indexes for
		// bottom left element
		int i = m - 1, j = 0;
			while (i >= 0 && j < n)
			{
				if (mat[i][j] == x)
					return true;
				if (mat[i][j] > x)
					i--;
				else // if mat[i][j] < x
					j++;
			}
			
			return false;
	}


	}




