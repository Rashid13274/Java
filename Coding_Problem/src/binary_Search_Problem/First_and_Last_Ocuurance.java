package binary_Search_Problem;

// WE ARE GOING TO FIND FIRST AND LAST OCCURANCE IN BINARY SEARCH
public class First_and_Last_Ocuurance {
	public static int first(int arr[], int target) {
		int result=-1;
		int start =0;
		int end =arr.length-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(target==arr[mid]) {
				result=mid;
				end=mid-1;  // to get first occurance
			}else if(target>arr[mid]) {
				start=mid+1;
			}else {
				end=mid-1;
			}
		}
		return result;
	}
	
	
	public static int last(int arr[], int target) {
		int result=-1;
		int start =0;
		int end =arr.length-1;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(target==arr[mid]) {
				result=mid;
				start=mid+1;  // to get last  occurance
			}else if(target>arr[mid]) {
				start=mid+1;
			}else {
				end=mid-1;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int arr[]= {1,3,4,10,10,10,10,9};
		int target=10;
		System.out.println(first(arr, target));
		System.out.println(last(arr, target));
		
	}

}
