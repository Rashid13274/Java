package string_usual_problem;


import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class First_Non_Repeating_Character_In_String {

	public static void main(String[] args) {
		String str ="hello";
		// HERE WE USE LINKEDHASHMAP , SINCE  IT PRESERVE INSERTION ORDER.
		LinkedHashMap<Character,Integer> map= new LinkedHashMap<>();
		for(int i=0;i<str.length();i++) {
			if(map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), map.get(str.charAt(i))+1);
			}else {
				map.put(str.charAt(i), 1);
			}
			
		}
		System.out.println("print the first-non repeating character");
		
		// traversing the map
		for(Entry<Character, Integer> ch: map.entrySet()) {
			if(ch.getValue()==1) {
				System.out.println(ch.getKey());
			}
			break;
		}
		
			 
			 
	}

}
