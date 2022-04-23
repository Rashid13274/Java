package binary_Search_Problem;

//import java.util.concurrent.RecursiveAction;

public class binary_pract {

	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		int arr[]= {1,3,4,5,6,7};
		int k=1;
		int start =0;
		int end =arr.length-1;
		System.out.println(recursiveBinary(arr,  start , end , k));

	}
//	public static int binary(int arr[], int n,int k) {
//		// iterative 
//		int start =0;
//		int end =n;
//		while(start <= end) {
//			int mid =start +(end-start)/2;
//			if(arr[mid]==k) {
//				return  mid;
//			}else if(k>arr[mid]) {
//				start =mid+1;
//			}else {
//				end =mid-1;
//			}
//		}
//		return -1;
//	}
	
	
	public static int recursiveBinary(int arr[], int start ,int end, int k ) {
		while(start <=end) {
			int mid =start +(end -start)/2;
			if(k==arr[mid]) {
				return mid;
			}else if(k>arr[mid]) {
				return recursiveBinary(arr,  mid+1, end, k);
			}else{
				 return recursiveBinary(arr,  start, mid-1, k);
			}
		}
		return -1;
		
	}

}
