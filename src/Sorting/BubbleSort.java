package Sorting;

public class BubbleSort {

	public static void main(String[] args) {
		// bubble sort  time complexcity O(n2);
		int arr[]= {5,3,-1,0,7,8,6};
		int n=arr.length;
		boolean sorted =true;
		// initially no sorted so sorted is true;
		for(int i=0;i<n-1-i;i++) {
			for(int j=0;j<n-1;j++) { //  Here WE USE J<n-1-i FOR BETTER OPTIMIZATION OF CODE:
				if(arr[j+1]<arr[j]) {
					int temp= arr[j+1];
					arr[j+1]=arr[j];
					arr[j]=temp;
					
					sorted=false;
					//sorted means if we use any times swap means it has remaining sorted
				}
			}
			if(sorted) break; // condition if there is no swap needed then break the loop. that means it has already sorted;
		}
		for(int item:arr) {
			System.out.print(item+" ");
		}
//System.out.println(n);
	}

}
