package binary_Search_Problem;
/* WE HAVE TO FIND COUNT OF MOST OCCURANCE ELEMENT IN SORTED ARRAY.
 * 
 SINCE ARRAY  IS SORTED SO ALL THE REPEACTED DIGIT WILL BE ALTOGETHER UNLIKE IN UNSORTED ARRAY 
WHERE ELEMENT ARE SCATTERED.
 EX: 1,3,4,10,10,10,10,9; 
 COUNT IS 4;
TO FINDOUT THE COUNT OF  OCCURANCE  :- (COUNT OF LAST OCCURANCE - COUNT OF FIRST OCCURANCE +1)  WILL BE RESULT.
*/
public class Count_Of_MostOccurance_Element04 {
	
	
		public static int firstOccurance(int arr[], int target) {
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
		
		
		public static int lastOccurance(int arr[], int target) {
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
		int firstCount=firstOccurance(arr, target);
		int lastCount=lastOccurance(arr, target);
		int totalCount=lastCount-firstCount+1;
		System.out.println(totalCount);
	}

}
