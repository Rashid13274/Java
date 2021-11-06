package binary_Search_Problem;

public class Ceil_of_Element_in_Sorted_Array {
	public static int binary(int []arr, int key) {
		int low=0;
		int high=arr.length-1;
		int result=-1;
		while(low<=high) {
			int mid=low+(high-low)/2;
			// if key is present in the array.
			if(key==arr[mid]) {
				return mid;
			
			} 
			// if the key is not present in the array and we are guessing the ceil.
			// so here mid will  be greatest candidates in given arr.
			else if(arr[mid]>=key) {
				result=mid;
				high = mid-1;
				
		
			}else {
				low= mid+1;
			}
			
		}
		return result;
	}

	public static void main(String[] args) {
		int arr[]= {1,2,4,6,7,8,10};
		int key=3;
		System.out.println(binary(arr, key));
		
	}

}
