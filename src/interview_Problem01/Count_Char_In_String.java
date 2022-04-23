package interview_Problem01;
/*
 *  To count the occurrence of a particular character in a string, the below steps are followed:

 * */


public class Count_Char_In_String {
	
	public static void main(String[] args) {
		// Driver method
		String str= "geeksforgeeks";
		char c = 'e';
		System.out.println(count(str, c));
	}

//JAVA program to count occurrences
//of a character



	// Method that return count of the given
	// character in the string
	public static int count(String s, char c)
	{
		int res = 0;

		for (int i=0; i<s.length(); i++)
		{
			// checking character in string
			if (s.charAt(i) == c)
			res++;
		}
		return res;
	}
	
	
}
