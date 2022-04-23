package Sorting;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		
		int arr [] = {2,1,-3,5,3,10};
		int n=arr.length;
		for(int i=1;i<n;i++) {
			int temp=arr[i]; // mark A[i]th element;
			int j=i-1;  	// set j at previous  element of arr[i];
			while(j>=0 && arr[j]>temp) {
				// comparing  all the previous  element of arr[i] with arr[i].
				// if any greater element is found then insert  it at proper position.
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=temp;
			
		}
	
		System.out.println(Arrays.toString(arr));

	}

}
