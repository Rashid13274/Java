package map;

import java.util.Arrays;
import java.util.HashMap;
			/*
			 * Longest consective sequece number
			 * ie.[200,2,100,4,3,1,]
			 * so in given arr 1,2,3,4  ie length 4   is greater length consequence number.
			 * */
public class Longest_Consective_Sequence {
	public static int LongestConsective(int[] num) {
		// putting the value in the hashMap
		HashMap<Integer,Boolean> hm= new HashMap<>();
		for(int i=0;i<num.length;i++) {
			hm.put(num[i], true);
		}
		// checking the second last consective number
		for(int i=0;i<num.length;i++) {
			if(hm.containsKey(num[i] -1)) {
				hm.put(num[i], false);
			}
		}
		// traversing the hashmap;
		int max=0;
		for(Integer key:hm.keySet()) {
			if(hm.get(key)==true) {
				max=Math.max(max, findLength(hm,key));
			}
		}
		return max;
		
	}
	private static int findLength(HashMap<Integer,Boolean> hm,int k) {
		int ans=0;
		while(hm.containsKey(k)) {
			ans++;
			k++;
		}
		return ans;
	}

	public static void main(String[] args) {
		int num[]= {200,1,3,100,2,4};
		Arrays.sort(num);
		System.out.println(LongestConsective(num));  
		

	}

}
