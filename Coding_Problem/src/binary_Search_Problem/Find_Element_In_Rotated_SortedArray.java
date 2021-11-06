package binary_Search_Problem;

public class Find_Element_In_Rotated_SortedArray {
	

	public static void main(String[] args) {
		int arr []= {2,3,4,5,6,7,8,9,1};
		int start=0;
		int end =arr.length-1;
//		int N=arr.length;
		int key=7;
		System.out.println(binarySearch(arr, start, end, key ));
	}
	// binarySearch  functionj
	public static int  binarySearch(int []arr, int start, int end, int key) {
		while(start<=end) {
			// to avoid overflow  
			int mid=start+(end-start)/2;
			// key found
			if(key==arr[mid]) return mid;
			
			// if left half is sorted
			if(arr[start]<=arr[mid]) {
				// if the key is present in the left half .
				if(arr[start]<=key && arr[mid]>=key) {
					return binarySearch(arr, start, mid-1, key);
					
					// if the key is not present in left half... search right half
				}else {
					return binarySearch(arr,mid+1, end , key);
				}
			}// if right half is sorted  
			else {
				// key is present in right half.
				if(arr[mid]<=key && arr[end]>=key) {
					return binarySearch(arr,mid+1, end ,key);
					
					// if key is not present in right half ... then search left half
				}else {
					return binarySearch(arr, start, mid-1, key);
				}
				
			}
		}
		return -1;
	}
	
	}


