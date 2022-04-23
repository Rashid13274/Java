package array;


public class Matrix_problem1 {
//	 better runtime than linear search in 2D Matrix

	public static void main(String[] args) {
		int [] [] arr = {{1,2,7,30},
        {2,4,18,50},
        {3,6,19,90},
        {4,7,20,91}};
		int x=20;
		System.out.println(findN(arr,x));
		
		

	

		
	}
	public static boolean findN(int[][] a, int x) {
	    if (a.length == 0 || a[0].length == 0 || x > a[a.length - 1][a[0].length - 1] || x < a[0][0]) {
	        return false;
	    }
	    int LastRow = a.length - 1, Lastcol = a[0].length - 1, row = 0, col = 0;

	    while (row <= LastRow) {
	        if (a[row][col] == x) {
	            return true;
	        } else if (col < Lastcol) {
	            col++;
	        } else {
	            col = 0;
	            row++;
	        }
	    }
	    return false;
	}

}
