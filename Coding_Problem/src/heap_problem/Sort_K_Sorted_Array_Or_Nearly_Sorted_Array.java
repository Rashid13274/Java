package heap_problem;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;
// this problem is similar to kth largest element.

public class Sort_K_Sorted_Array_Or_Nearly_Sorted_Array {

	public static void main(String[] args) {
		int arr[]= {6,5,3,2,8,10,9};
		int n=arr.length;
		int k=3;
		kSortArray(arr,n,k);
		utlity(arr,n);

	}
	public static void kSortArray(int []arr, int n,int k) {
		PriorityQueue<Integer> minheap=new PriorityQueue<>();
		// add first k+1 items  to the min heap.
		for(int i=0;i<k+1;i++) {
			minheap.add(arr[i]);
		}
		int index=0;
		for(int i=k+1;i<arr.length;i++) {
			arr[index++]=minheap.peek();
			minheap.poll();
			minheap.add(arr[i]);
		}
		
		Iterator<Integer>itr=minheap.iterator();
		while(itr.hasNext()) {
			arr[index++]=minheap.peek();
			minheap.poll();
		}
		

	}
	//  A UTILITY FUNCTION  TO PRINT  THE ARRAY
	public static void utlity(int arr[], int n) {
	for(int i=0;i<n;i++) {
			System.out.print(arr[i]+" ");
		}

	}
}
