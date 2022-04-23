package Sorting;

public class SelectonSort {

	public static void main(String[] args) {
		//selection sort:-
		int arr[]= {5,3,0,-3,8,4};
		int n=arr.length;
		for(int i=0;i<n-1;i++) {
			int minIndex=i;
			for(int j=i;j<n;j++) {
				if(arr[j]<arr[minIndex]) {
					minIndex=j;
				}
			}
			int temp=arr[i];
			arr[i]=arr[minIndex];
			arr[minIndex]=temp;
		}
		for(int e:arr) {
			System.out.print(e+" ");
		}

	}

}
