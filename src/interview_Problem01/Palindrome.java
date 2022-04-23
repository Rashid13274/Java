package interview_Problem01;

import java.util.Scanner;

//import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
		String str="mango";
		String reverstr="";
		int strLength=str.length();
		for(int i=strLength-1; i>=0;i--) {
			reverstr=reverstr+str.charAt(i);
		}
	if(str.toLowerCase().equals(reverstr.toLowerCase())) {
		System.out.print(str+" "+"str is a palindrome");
	}else {
		System.out.println(str +" " +" str is not a palindrome");
	}
		
		/* EXAMPLE:02
		 Scanner sc = new Scanner(System.in);
		String s= sc.next();
		int start=0;
		int last=s.length()-1;
		Boolean  isPalindrome=true;
		while(start<last) {
			if(s.charAt(start)!=s.charAt(last)) {
				isPalindrome=false;
				break;
			}
			start++;
			last--;
		}
		System.out.println(isPalindrome);
		
		 */

	}

}
