package binary_Search_Problem;

public class Binary_Search_Recrusively {

	public static void main(String[] args) {
		int arr []= {1,2,4,5,6,7,8};
		int start=0;
		int end =arr.length-1;
		int target=7;
		System.out.println(binarySearch(arr, start, end, target));

	}
	public static int binarySearch(int[]arr, int start, int end, int target) {
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(target==arr[mid]) return mid;
			if(target<arr[mid]) return binarySearch(arr,start, mid-1, target);
			if(target>arr[mid]) return binarySearch(arr,mid+1, end, target);
		
		
	}
		return -1;

	}
}