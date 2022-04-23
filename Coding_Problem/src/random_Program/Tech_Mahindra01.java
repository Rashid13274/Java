package random_Program;

import java.util.Arrays;
import java.util.List;

public class Tech_Mahindra01 {

	public static void main(String[] args) {
		// FIXME Auto-generated method stub
//		int arr[]= {1,2,3,4,5};
//		int n=arr.length;
////		findOddEvenDifference(arr, n);
//		print(arr, n);
//		int []arr= {4,5,7,1,2};
//		 System.out.println( minimumAbsoluteDifference(arr));
//		int arr[]={18,11,27,12,14};
//		int n=arr.length;
//		findTotalFeet(arr, n);
//		 Scannner sc =new Scanner(System.in);
		int n=5;
		long arr[]= new long[n];
	}
	public static void findOddEvenDifference(int arr[], int n) {
		
		int oddSum=0;
		int evenSum=0;
		for(int i=0;i<n;i++) {
			if(arr[i]%2==0) {
				evenSum+=arr[i];
			}else {
				oddSum+=arr[i];
			}
		}
		int  total_difference=Math.abs(oddSum-evenSum);
		System.out.println(total_difference);
	}
	
	
	// PROBLEM:2
	
	public static void findTotalDistance(int arr[],int n) {
		int totalDistance=0;
		int i=0;
		int j=1;
		while(j<=arr.length-1 && i<arr.length) {
			totalDistance+=Math.abs(arr[i]-arr[j]);
			
			i++;
			j++;
		
		}
		System.out.println(arr.length);
		System.out.println(totalDistance);
		
	}
	
	
//	public static void print(int arr[],int n) {
//		for(int i=0;i<=arr.length-1; i++) {
//			System.out.print(arr[i]);
//		}
//		System.out.println();
//		for(int i=1;i<=arr.length-1; i++) {
//			System.out.print(arr[i]);
//		}
//	}
	
	
	
	;
// Minumum differernce  in the array;
	 public static int minimumAbsoluteDifference(int arr []) {
		    // Write your code here
		   Arrays.sort(arr);
		    int minDifference=1000000;
		    int n=arr.length-1;
		    for(int i=0;i<n;i++){
		        int difference=Math.abs(arr[i]-arr[i+1]);
		        minDifference=Math.min(difference,minDifference);
		        
		    }
		   
		    return  minDifference;
		}
	 
	 // MAXMUM DIFFERENCE BETWEEN ARRAY
	
	// find total feet
	 public static void findTotalFeet(int arr[],int n) {
		 int sum=0;
		 for(int i=0;i<n;i++) {
			if(arr[i]>=12) {
				sum+=arr[i]/12;
			}
		 }
		 
		 System.out.println(sum);
	 }
	 
	 // calculate the total tax 
	 public static void totalTax(long arr[],int n) {
		
		 for(int j=0;j<n;j++) {
			 
		 }
		 
		 int tax =0;
		 for(int i=1;i<=n;i++) {
			 tax+=(int)((arr[i]-1000)*0.1);
		 }
		 System.out.println(tax);
	 }

}

