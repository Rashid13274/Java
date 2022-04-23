package searching;

import java.util.Arrays;

public class Matrix {

	public static void main(String[] args) {
		int [][]arr= {
				{10,21,12,3,34},
				{23,45,66,43,31},
				{24,44,46,67,76}	
		};
		int target=34;
		System.out.println(Arrays.toString(search(arr,target)));
		

	}
	static int [] search(int [][]matrix, int target) {
		
		for(int row=0;row<matrix.length;row++) {
			for(int  column =0;column<matrix.length;column++) {
				if(matrix[row][column]==target) 
					return new int[] {row, column};
			}
		}
		 return new int[] {-1,-1};
	}

}
