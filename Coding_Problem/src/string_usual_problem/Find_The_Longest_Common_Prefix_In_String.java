package string_usual_problem;

/*FIND THE LONGEST COMMON PREFIX AMONG  ARRAY OF STRINGS
 * GIVEN TWO ARRAY OF STRINGS , WRITE A METHOD  TO FIND  THE LONGEST  COMMON PREFIX.
 * IF NO COMMON PREFIX IS FOUND THEN PRINT EMPTY STRING.
 * 
 * FOR EX:  INPUT: ["cat", "cable", "camera"]
 * 			OUTPUT:ca
 * 
 * EX: 		INPUT: ["rat", "dog", "elephent"]
 * 			OUTPUT:""  
 * 
 * */

public class Find_The_Longest_Common_Prefix_In_String {

	public static void main(String[] args) {
		String []arr= {"katrina","kater","katering" };
		String  lcpString=commonPrefix(arr);
		System.out.println(lcpString);
		
	}
	public static String commonPrefix(String[]arr) {
		// IF ARRAY IS NULL OR ARRAY IS EMPTY.
		if(arr==null || arr.length==0)return "";
		// let  this is out first longest common prefix at index 0;
		String lcp=arr[0];
		for(int i=1;i<arr.length;i++) {
			// this is our second string
			String currentWord=arr[i];
			int j=0;
			// HERE WE COMPARING FIRST STRING TO SECOND AND FURTHRON STRING
			while(j<lcp.length() && j<currentWord.length() && 
			lcp.charAt(j)==currentWord.charAt(j)) {
				j++;
			}
			// if above while does'nt execute  means there is no prefix.
			if(j==0) return "";
			// here we are putting j since j will only increment if both have common prefix or character.
			// Otherwise  loop will get breaked.
			lcp=currentWord.substring(0,j);
		}
		return lcp;
	}
	

}
