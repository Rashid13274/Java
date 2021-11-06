package binary_Search_Problem;

public class Reverse_sorted_array {

	public static void main(String[] args) {
		int arr[]= {9,8,7,6,5,4,3,2,1};
		int target =8;
		System.out.println(binarySearch(arr, target));
		
	}
	public static int  binarySearch(int[]arr,int target) {
		int high =0; 
		int low= arr.length-1;
		while(high<=low) {
			int mid=high+(low-high)/2;  // to overcome the overflow.
			if(target==arr[mid]) {
				return  mid;
			}else if(target>arr[mid]) {
				low=mid-1;
			}else if(target<arr[mid]) {
				high=mid+1;
			}
		}
		return -1;
		
	}

}
