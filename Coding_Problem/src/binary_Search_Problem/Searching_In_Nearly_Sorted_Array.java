package binary_Search_Problem;

/* SEARCHING  IN NEARLY SORTED ARRAY IN LOGERITHMIC TIME.
 * 
 * GIVEN AN  NEARLY SORTED  ARRAY SUCH THAT  EACH OF THE N ELEMENET MAY BE  MISPLACED  BY NO MORE  THAN ONE  POSITION 
 * FROM THE CORRECT ORDER , SEARCH  A GIVEN  ELEMENT  IN IT EFFICENTLY . REPORT  IF THE ELEMENT IS NOT PRESENT INT ARRAY.
 * 
 * INPUT: NUMS=[2,1,3,5,7,6,8,9]
 * TARGET= 9
 * OUTPUT  : ELEMENT IS FOUND  AT INDEXT 3.
 * */

public class Searching_In_Nearly_Sorted_Array {

	public static void main(String[] args) {
		int arr[]= {2,1,3,5,7,6,8,9};
		int target =9;
		System.out.println(binarySearch(arr, target));
		

	}
	public static int binarySearch(int arr[],int target) {
		int start =0;
		int end=arr.length-1;
		int N=arr.length;
		while(start<=end) {
			int mid=start+(end-start)/2;
			// to avoid overflow
			int next=(mid+1)%N;
			// to avoid  
			int prev=(start+N-1)%N;
			if(target==arr[mid]) return mid;
			if(target==arr[next]) return next;
			if(target==arr[prev]) return prev;
			
			
			if(target<=arr[mid]) {
				end=mid-2;
		}else {
			start=mid+2;
		}
			
			
		}
		return -1;
	}

}
