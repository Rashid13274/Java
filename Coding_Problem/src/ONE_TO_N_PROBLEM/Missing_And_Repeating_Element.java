package ONE_TO_N_PROBLEM;
import java.util.Map;
import java.util.HashMap;
public class Missing_And_Repeating_Element {

	public static void main(String[] args) {
		int arr[]= {4,3,6,2,1,1};
		missingAndRepeating(arr);

	}
	public static void missingAndRepeating(int arr[]) {
		Map<Integer,Boolean> map=new HashMap<>();
		int max=arr.length;
		for(Integer i:arr) {
			if(map.get(i)==null) {
				map.put(i, true);
			}else {
				System.out.println("repeating"+" "+i);
			}
		}
		for(int i=1;i<=max;i++) {
			if(map.get(i)==null) {
				System.out.println("missing number is "+" "+i);
				
				//O(N)
			}
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void missingRepeating(int arr[]) {
		
	}
}
