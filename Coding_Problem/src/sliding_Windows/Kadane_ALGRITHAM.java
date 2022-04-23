package sliding_Windows;
// KADANE ALGORITHAM IS USED TO  FIND MAXM SUM OF CONTIGEOUS SUBARRAY.
public class Kadane_ALGRITHAM {

	public static void main(String[] args) {
		int arr[]= {-2,-3,4,-1,-2,1,5,-3};
		System.out.println(maxSumOfSubarry(arr));
		
	}
	public static int  maxSumOfSubarry(int arr[]) {
  		int max=0;
		int currSum=0;
		for(int i=0;i<arr.length;i++) {
			currSum=currSum+max;
			if(arr[i]>max) {
				max=arr[i];
			}
			if(currSum<0) {
				currSum=0;
			}
		}
		return max;
	}

}
