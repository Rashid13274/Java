package string_usual_problem;

import java.util.HashSet;
import java.util.Set;

/*GIVEN A STRING , FIND THE LENGTH OF LONGEST SUBSTRING WITHOUT REPEATING ANY CHARACTER.
 * EX:1.
 * INPUT: "abcabacabc"
 * OUTPUT: 3
 * 
 * EX:2
 * INPUT:"bbbb"
 * OUTPUT:1
 * 
 * EX: 3
 * INPUT:"pwekew"
 * OUTPUT:3
 * 
 * */

public class Longest_Substring_Without_Repeating_Any_Character {

	public static void main(String[] args) {
		String str="abcbe";
		
//		int longest=LongestSubstring(str);
//		System.out.println( st+" "+"longest substring of this string is"+" "+longest);
		System.out.println(LongestSubstring(str));
		
	}
	// SLIDING WINDOWS APPROACH:-
	public static int LongestSubstring(String str) {
		int maxCount=0;
		// first_Pointer
		int i=0;
		// Second_Pointer
		int j=0;
		int strLen=str.length();
		Set<Character> st=new HashSet<>();
		while(i<strLen && j<strLen) {
			// if set does'not contains repeated element add them.
			if(!st.contains(str.charAt(j))) {
				st.add(str.charAt(j));
				j++;
				// maximaize the count; 
				maxCount=Math.max(maxCount, j-i);
			}else {
			// Remove the duplicate character
				st.remove(str.charAt(i));
				i++;
			}
			// at the end we get SUBSTRING  "cbe" of length 3.
		}
		System.out.println(st);
		return maxCount;
	}

}
