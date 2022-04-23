package searching;



public class BinerySearch {
	public static void main(String args[])
	{
	int []arr= {1,2,4,5,6,7,9};
	int key=9;
//	int low =0;
//	int high=arr.length-1;

	
//	System.out.println(binarySearch(arr,key,low,high));
	System.out.println(binarySearch(arr,key));
	

	
	
	

	}
	
//	RECURSIVLY BINARY SEARCH:-
//	public static int binarySearch(int []arr, int key, int low, int high) {
//		if(low>high) return -1; // it is our base condition in this recursive binary search. (i.e element is not present in the array )
//		int mid=(low+high)/2;
//		if(key==arr[mid]) return mid;
//		if(key>arr[mid]) return binarySearch(arr, key, mid+1, high);
//		return binarySearch(arr, key, low, mid-1);
//	}
	
//	ITERATIVELY BINARY SEARCH :-
	
	public static int  binarySearch(int[]arr, int key) {
		int low=0;
		int high=arr.length-1;
		while(low<=high) {
		int mid=(low+high)/2;
			
			if(key==arr[mid]) {
				return mid;
			}else if(key<arr[mid]) {
				high=mid-1;
				
			}else {
				low=mid+1;  
			}
			}
		return -1;
		}
	
}


