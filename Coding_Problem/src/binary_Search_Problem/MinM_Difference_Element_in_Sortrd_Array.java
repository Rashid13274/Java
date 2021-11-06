package binary_Search_Problem;

public class MinM_Difference_Element_in_Sortrd_Array {
	public static int binary(int arr[], int target) {
		int low=0;
		int high=arr.length-1;
		while(low<=high) {
			int mid=low+(high-low)/2;
			if(target==mid) {
				return arr[mid];
			}
			else if(target>arr[mid]) {
				low=mid+1;
			}else {
				high=mid-1;
			}
		}
		int a=Math.abs(12-arr[low]);
		
		int b=Math.abs(12-arr[high]);
		if(a<=b) {
			return  arr[low];
		}else {
			return arr[high];
		}
		
	}

	public static void main(String[] args) {
		
		int arr[]= {1,2,4,5,6,8,9,14,18};
		int key=7;
		System.out.println(binary(arr, key));
	}

}
