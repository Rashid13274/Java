package leetCode_problem;

//import java.util.Arrays;

public class TwoSum {

	public static void main(String[] args) {
		/**
		 * INPUT [2,7,11,15]
		 * OUTPUT: [1,2]
		 * EXPLANATION : THE SUM OF  2 AND  7 IS 9. THEREFORE INDEX1=1 AND INDEX2=2;
		 * 
		 */
//		Arrays.toString(arr);
		System.out.println(sum(arr,target));
	}
	// USING TWO POINTER ALGORITHAM
	static   int [] arr= {1,2,4,5,6,8};
	static   int target =9;
	  public static int[]  sum( int []arr ,int target) {
		  int a_pointer=0;
		  int b_pointer=arr.length-1;
		  while(a_pointer<=b_pointer) {
			 int sum=arr[a_pointer]+arr[b_pointer];
			 if(sum>target) {
				 b_pointer-=1;
				 
			 }else if(sum<target) {
				 a_pointer+=1;
				 
			 }else {
				 return new int [] {a_pointer+1,b_pointer+1};
			 }
		  }
		  return new int [] {a_pointer+1,b_pointer+1};
		 }
		  
	  }


