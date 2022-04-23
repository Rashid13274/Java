package practice_java_usual_question;
// string  "aba"
// true;
// String abca you can delete at most one character   to  be an palindrom.
// true.
public class Valid_Palinderome {

	public static void main(String[] args) {
//		String s="aba";
		String s="abca";
		System.out.println(validPalindrome(s));
		

	} 
	public static boolean validPalindrome(String s) {
		int  left=0;
		int right =s.length()-1;
		while(left<=right) {
			if(s.charAt(left)!=s.charAt(right)) {
				return  palindrome(s, left+1, right) || palindrome(s,left,right-1);
			}
			left++;
			right--;
		}
		return true;
	}
	
	
	
	
	
	// palindrome function.
	public static boolean  palindrome (String s , int start, int end) {
		
		while(start<=end) {
			if(s.charAt(start)!=s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
		
	}

}
