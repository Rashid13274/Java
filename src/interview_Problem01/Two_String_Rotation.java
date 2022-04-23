package interview_Problem01;

public class Two_String_Rotation {
	
	/*
	 * 1. Create a temp string and store concatenation of str1 to
       str1 in temp.
                          temp = str1.str1
       2. If str2 is a substring of temp then str1 and str2 are 
       rotations of each other.

    Example:                 
                     str1 = "ABACD"
                     str2 = "CDABA"

     temp = str1.str1 = "ABACDABACD"
     Since str2 is a substring of temp, str1 and str2 are 
     rotations of each other.
	 * */

	public static void main(String[] args) {
		String s1="abca";
		String s2="bcaas";
		String temp=s1+s2;
		System.out.println((s1 + s1).indexOf(s2));
		
	}
	
}
