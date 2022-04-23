package interview_Problem01;

public class FloodFill {

	public static void main(String[] args) {
		// Dimension of paint screen 
		
		
		int[][] a = {{ 1, 2, 3 ,4, 5, 6},
				     { 4, 5, 6 ,4, 5, 6}, 
					 { 1, 2, 3,4, 5, 6 },
				     { 4, 5, 6,4, 5, 6 }, 
					 { 1, 2, 3 ,4, 5, 6},
				     { 4, 5, 6 ,4, 5, 6}};
//		floodfill(a,3,3,5,3);
//		printMatrix(a);
		int r=4;
		int c=4, toFill=3;
		
		callFunction(a, r, c,toFill);
		System.out.println("updated  screen  after call  to flood  callFunction");
		for (int i=0;i<M; i++) {
			for(int j=0;j<N;j++) {
				
				System.out.print( a[i][j]+"  ");
//				System.out.print(" ");
			}
		}
	

	}
	static int  M =6;
	static int N=6;
	static void floodfill(int a[][],int r, int c,int toFill,int prevFill) {
		// a matrix array
		// r =rows
		// c=column
		// toFill= color we want to fill
		// prevFill= previous color of that field
		int rows=a.length;
		int cols=a[0].length;
		if(r<0 || r>=rows ||  c<0 || c>=cols) { //  we don't have such length of matrix;
			return ;
		}
		if(a[r][c]!=prevFill) { // base condition  prevFill is outOfBound
			return;
			
		}
		a[r][c]=toFill;
		floodfill(a, r-1, c, toFill, prevFill);
		floodfill(a, r+1, c, toFill, prevFill);
		floodfill(a, r, c-1, toFill, prevFill);
		floodfill(a, r-1, c+1, toFill, prevFill);
	}
	// It mainly  finds  the previous  color  on (x, y) and  calls   floodfill();
	static void callFunction(int a[][],int r, int c ,int  toFill) {
		int  prevFill=a[r][c];
		if(prevFill==toFill) return;
		floodfill(a,r,c,toFill, prevFill);
	}
	  


}
