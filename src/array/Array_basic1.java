package array;

//import java.util.Arrays;
//import java.util.Arrays;
//import java.util.Arrays;
//import java.util.Scanner;

public class Array_basic1 {

	public static void main(String[] args) {
		// DECLARATION OF ARRAY:-
//		 int arr[]=new int[5];
		//or
		//
//		int arr[]=new int[5];
//		System.out.println(Arrays.toString(arr));
//		[0, 0, 0, 0, 0]
	 
		
//		Scanner sc = new Scanner(System.in);
//		for(int i=0;i<arr.length;i++) {
//		arr[i]= sc.nextInt();
//		}
//		System.out.println(Arrays.toString(arr));
		
		// binary search:
		
//		int arr[]={7,8,9,1,2,3,4,5};
//		int start=0;
//		int end=arr.length-1;
//		int target =4;
//		binarySearch(arr,start,end ,target);
//		int n=11;
//		prime(n);
//	}
//	public static int  binarySearch(int[]arr, int start, int end, int target) {
//		int mid=Math.round((start+end)/2);
//		while(start<end) {
//			if(target>arr[mid]) {
//				start=mid+1;
//			} else if(target<arr[mid]) {
//					end=mid-1;;
//			}
//			else if(target==arr[mid]) {
//				return mid;
//		}
//		
//	} return -1;
//
		int n=12;
		primeN(12);
}
	 public static void primeN(int  n) {
		 Boolean arr[]=new Boolean[n+1];
		 for(int i=0;i<n;i++) {
			 arr[i]=true;
		 }
		 // remove all the multiple of even and odd except 2, 3;
		 for(int p=2;p*p<=n;p++) {
			 if(arr[p]==true) {
				 for(int i=p*p; i<=n;i+=p) {
					 arr[i]=false;
				 }
			 }
			 }
			 
		 for(int i=0;i<=n;i++) {
			 if(arr[i]==true) {
				 System.out.print(i+" ");
			 }
		 }
	 }

}
