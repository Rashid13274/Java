package random_Program;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.Scanner;
import java.util.Set;
//import java.util.TreeSet;

/*FIND THE ONLY NON REPEATING  ELEMENT IN AN ARRAY WHERE EVERY ELEMENT REPEAT THRICE.
 *WE CAN DO IT BY CHANGING THE MODULAS  BY KTH TIMES. IF  IT KTH TIMES AND SO ON.
 *
 * 1. WE CAN ACHIEVE BY SET OPERATION 
 * 2. WE CAN ACHIEVE BY HASHMAP.
 */

public class XOR_PROBLEM_02 {


		static final int INT_SIZE = 32;

		// Method to find the element that occur only once
		static int getSingle(int arr[], int n)
		{
			int result = 0;
			int x, sum;

			// Iterate through every bit
			for (int i = 0; i < INT_SIZE; i++) {
				// Find sum of set bits at ith position in all
				// array elements
				sum = 0;
				x = (1 << i);
				for (int j = 0; j < n; j++) {
					if ((arr[j] & x) != 0)
						sum++;
				}
				// The bits with sum not multiple of 3, are the
				// bits of element with single occurrence.
				if ((sum % 3) != 0)
					result |= x;
			}
			return result;
		}
		////////////////////////////////////////////////////////////////////////////////////
		public static  void  noDuplication(String arr[], int n) {
			Map<String, Integer> map=new HashMap<>();
			for(int i=0;i<arr.length;i++) {
				if(map.containsKey(arr[i])) {
					map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
				}else {
					map.put(arr[i], 1);
				}
			}
			for(Entry<String, Integer> val: map.entrySet()) {
				if(val.getValue()==1) {
					System.out.println(val.getKey());
				}
				
			}
		}
		////////////////////////////////////////////////////////////////////////////////////
		public  static void singleCopy(char arr[], int n) {
			Set<Object> set=new HashSet<>();
			for(int i=0;i<arr.length;i++) {
				set.add(arr[i]);
			}
			System.out.println(set);
		}

		///////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
  
		
//		int arr[] = {2,2,1,5,1,1,2};
//		String arr[]= {"ram","shyam","ram","shyam","madan"};
		char arr[]= {'a','b','c','b', 'a'};
		int n = arr.length;
//		System.out.println("The element with single occurrence is " + getSingle(arr, n));
//		System.out.println("The element with single occurrence is " + once(arr, n));
//		noDuplication(arr, n);
		singleCopy(arr,n);
//		Set<Object> set=new TreeSet<>();
//		Scanner sc=new Scanner(System.in);
//		for(int i=0;i<6;i++) {
//			int k=sc.nextInt();
//			set.add(k);
//			
//		}
//		System.out.println(set);
		
		

	}


}
