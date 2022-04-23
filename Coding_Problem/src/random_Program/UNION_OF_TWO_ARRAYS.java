package random_Program;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

public class UNION_OF_TWO_ARRAYS {

	public static void main(String[] args) {
		int arr1[]= {1,2,3,4,5,6,2,0};
		int arr2[]= {2,3,4,7,8,9,10};
//		union(arr1,arr2);
		printUnion(arr1,arr2);

	}
	public static void union(int arr1[], int arr2[]) {
		HashSet<Integer> set=new HashSet<>();
		for(int i=0;i<arr1.length;i++) {
			set.add(arr1[i]);
		}
		for(int i=0;i<arr2.length;i++) {
			set.add(arr2[i]);
		}
		System.out.println(set);
	}
	//time complexcity O(m*log(m)+n*log(n))
//////////////////////////////////////////////////////////////////////////////////////////////////////
	//SECOND APPROACH
	public static void printUnion(int arr1[], int arr2[]) {
		Map<Integer,Integer>map=new HashMap<>();
		for(int i=0;i<arr1.length;i++) {
			map.put(arr1[i], i);
		}
		for(int i=0;i<arr2.length;i++) {
			map.put(arr2[i], i);
		}
		System.out.println("union of both the arrays are");
		for(Entry<Integer,Integer>val:map.entrySet()) {
			System.out.print(val.getKey()+" ");
			
			//map will contains only distinct elements from the array.
			// time complexcity O(n+m);  
		}
	}
	

}
