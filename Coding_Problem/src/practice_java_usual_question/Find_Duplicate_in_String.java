package practice_java_usual_question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;


public class Find_Duplicate_in_String {

	public static void main(String[] args) {
		String s ="programming";
//		dupll(s);
		dupll1(s);
	

	}
	// HASHMAP APPROACH:-
	public static void  dupll(String s) {
		HashMap <Character ,Integer> map=new HashMap<>();
		for(int i=0;i<s.length();i++) {
			if(map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), map.getOrDefault(s.charAt(i),0 )+1);
			}else {
				map.put(s.charAt(i), 1);
			}
		}
		// traverse
		for(Entry<Character, Integer> c:map.entrySet()) {
			if(c.getValue()>1) {
				System.out.print(c.getKey()+" ");
			}
			
		}
	}
	
	// SET APPROACH:-
	public static void dupll1(String s) {
		Set <Character> set=new HashSet<> ();
		for(int i=0;i<s.length();i++) {
			if(set.contains(s.charAt(i))) {
				
				Character ch= s.charAt(i);
				System.out.print(ch+" ");
			}else {
				set.add(s.charAt(i));
			}
			
		}
	}

}
