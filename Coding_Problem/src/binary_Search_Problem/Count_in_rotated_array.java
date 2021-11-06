package binary_Search_Problem;

/*
 * YOU HAVE GIVEN AN ARRAY THAT IS SORTED  AND THEN ROTATED.
 * FOR EX: arr=[1,2,3,4,5] 	WHICH IS SORTED.
 * ROTATE IT TWICE  TO THE  RIGHT TO GIVE [4,5,1,2,3].
 * WE HAVE TO  FIND HOW MANY TIMES THIS SORTED ARRAY  ROTATED.
 * --> ANS: INDEX OF MINM ELEMENT IN ROTATED ARRAY WILL BE  COUNT OF NUMBER OF TIMES ARRAY WAS  ROTATED .
 * --> HERE MINM ELEMENT 1 AND INDEX IS 2 SO  ROTATION IS 2.
 */
 

public class Count_in_rotated_array {
	public static int binarySearch(int[]arr ,int N) {
		int low=0;
		int high= N-1;
		while(low<=high) {
			// avoid overflow ,
			int mid= low+(high-low)/2;
			int nextElement=(mid+1)% N;
			int prevElement=(mid+N-1)%N;
			// if our mid is  the minm element of sorted array.
			if(arr[prevElement]>arr[mid] && arr[mid]<arr[nextElement]) {
				return mid;
				// if mid is greater than low  i.e left section is sorted .
			}else if(arr[low]<=arr[mid]) {
				low =mid+1;
				// here mid is grater than high  i.e right section is unsorted 
				// so minM element willbe there.
			}else if(arr[high]<=arr[mid]){
				high=mid-1;
			}
			}
		return -1;
		}
	

	public static void main(String[] args) {
		int arr[]= {4,5,6,7,8,9,1,2,3};
//		int arr[]= {4,5,1,2,3};
		int  N=arr.length;
		System.out.println(binarySearch(arr, N));
		

	}

}
