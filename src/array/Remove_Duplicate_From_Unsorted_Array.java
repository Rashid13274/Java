package array;

//import java.util.HashMap;
//import java.util.Arrays;
//import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Remove_Duplicate_From_Unsorted_Array {

	public static void main(String[] args) {
		// THERE IS TWO APPROACH TO SOLVE THIS PROBLEM :
		// 1. USING SORTING TECHNIQUE: O(nlogn)
		// 2. USING HASHMAP: O(n)
		
		
	
		//1. USING FIRST APPROCH. 
		
//		int j=0;
//		int arr[]= {2,3,4,2,1,23,21,12,1};
//		Arrays.sort(arr);
//		// traverse the array
//		for(int i=0;i<arr.length-1;i++) {
//			if(arr[i]!=arr[i+1]) {
//				arr[j++]=arr[i];
//			}
//		}
//		arr[j++]=arr[arr.length-1];
//		for(j=0;j<arr.length;j++) {
//			System.out.print(arr[j]+" ");
//			
//		}
	
		
		// 2. USING HASHMAP:-
			
//		int arr[]= {2,3,4,2,1,23,21,12,1};
//		removeDuplicate(arr);
//		}
//	public static void removeDuplicate(int []arr) {
//		Arrays.sort(arr);
//		int n=arr.length;
//		
//		Map<Integer,Integer> map=new HashMap<>();
//		for(int i=0;i<n;i++) {
//			if(map.containsKey(arr[i])){
//				map.put(arr[i], map.get(arr[i]+1));
//			}else {
//				map.put(arr[i], 1);
//			}
//	}
//		// print each keys
//		map.forEach(( k,v)-> System.out.print(k+" "));
		
//		Scanner sc =new Scanner(System.in);
//		
//		int n=sc.nextInt();
//		int arr[]= new int [n];
//		int start =0;
//		int end = arr.length-1;
//		int middle=Math.round((start+end)/2);
//		System.out.println("ENTER THE ARRAY VALUE");
//		for(int i=0;i<arr.length;i++) {
//			arr[i]=sc.nextInt();
//		}
//		while(start<end) {
//		int temp=arr[start];
//		 arr[start]=arr[end];
//		 arr[end]=arr[start];
//		 start++;
//		 end--;
//		}
//		
//		System.out.println();
//	
//		for(int j=0;j<arr.length;j++) {
//			if(j==middle) {
//				System.out.print(arr[middle]);
//			}else {
//				
//				System.out.print(arr[j]+ " ");
//			}
//		}
	
		
	}

}
