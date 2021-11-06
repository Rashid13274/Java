package string_usual_problem;
// java program to count occurance (frequency) of each character in a string 
// ex: str="java:
// j:1, a:2, v:1

import java.util.HashMap;
import java.util.Map;

public class Count_No_oF_Occurance_In_Strint {

	public static void main(String[] args) {
		String str="programming";
		Map<Character, Integer> map=new HashMap<>();
		// put the value in the map
		for(char c: str.toCharArray()) {
			if(map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			}else {
				map.put(c, 1);
			}
		}
		System.out.println(map);
		

	}

}
