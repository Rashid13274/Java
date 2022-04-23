package map;

import java.util.HashMap;
import java.util.Map;

public class Map_Program01 {

	public static void main(String[] args) {
		
		
		
		String str="tree";
		freqeuncyCount(str);
		
		
	} // PROBLEM:01
	// SORT THE STRING BASED ON  FREQUENCY COUNT:-
	// EX: tree  --> eetr or eert 
	 
	public static String countString( String s) {
		StringBuilder sb=new StringBuilder();
		int n=s.length();
		Map<Character, Integer> val=new HashMap<>();
		// Storing the character into the hashmap
		for(int i=0;i<n;i++) {
			char c=s.charAt(i);
			val.put(c, val.getOrDefault(c, 0)+1);
		}
		// traversing the hashmap
		val.entrySet().stream().sorted(Map.Entry.<Character,Integer>comparingByKey().reversed()).forEach(record->{
			Character  key=record.getKey();
			int value=record.getValue();
			// APPEND THE CHARCHATER NUMBER OF TIMES IT OCCURS
			for(int j=0;j<value;j++) {
				sb.append(key);
			}
		});
		return sb.toString();
			
	}
	//PROBLEM:02
	//Java program to count the occurrence of each character in a string using Hashmap.
	public static void freqeuncyCount(String str) {
		char[] arr=str.toCharArray();
		Map<Character,Integer> letter=new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			if(letter.containsKey(arr[i])) {
				letter.put(arr[i],letter.getOrDefault(letter, 0) +1);
			}
			letter.put(arr[i], i);
		}
		for(Map.Entry<Character,Integer> ch: letter.entrySet()) {
			Character  e=ch.getKey();
			int val=ch.getValue();
			System.out.println(e+" "+val);
			
		}
		
		
	}

}
