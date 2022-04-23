package random_Program;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
public class INTERSECTION_OF_TWO_ARRAYS {

	public static void main(String[] args) {
		int arr[]= {1,2,3,4,5,6};
		int arr1[]= {3,4,5,6,7,8,9};
		intersection(arr, arr1);

	}
	public static void intersection(int arr[],int arr1[]) {
		Map<Integer,Integer>map=new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			map.put(arr[i], 1);
		}
		for(int j=0;j<arr1.length;j++) {
			if(map.containsKey(arr1[j])) {
				map.put(arr1[j], map.getOrDefault(arr[j],0)+1);
			}
		}
			for(Entry<Integer,Integer> val:map.entrySet()) {
				if(val.getValue()>=2) {
					System.out.print(val.getKey()+" ");
					
				}
				
			}
	
		
	
	}

}
