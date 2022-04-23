package sliding_Windows;

public class Maximum_Sum_of_Subarray_Of_Size_K {

	public static void main(String[] args) {
		int arr[]= {2,5,1,8,2,9,1};
		int n=arr.length;
		int k=4;
		System.out.println(maxSum(arr,n,k));
	}
	public static  int maxSum(int arr[], int n, int k) {
		int max=0,sum = 0, i = 0, j=0;
		
		
		while(j<arr.length) {
			sum+=arr[j];  
			if(j-i+1<k) {
				j++;
			}
			else if(j-i+1==k) {
				max=Math.max(sum, max);
				sum=max-arr[i];
				i++;
				j++;
			
			}
			
		}
		return max;
	}

}
