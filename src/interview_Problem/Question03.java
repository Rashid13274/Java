package interview_Problem;

public class Question03 {
	public static String reverseWords(String s) {
		int i=s.length()-1;
		String ans="";
		while(i>=0) {
			while(i>=0 && s.charAt(i)==' ') i--;
			int j=i;
			if(i<0) break;
			while(i>=0 && s.charAt(i)!=' ') i--;
			if(ans.isEmpty()) {
				ans=ans.concat(s.substring(i+1, j+1));
			}else
				ans=ans.concat(" "+s.substring(i+1, j+1));
		}
		
		return ans;
	}
	
	// rev 
	public static  String reverse(String s) {
		String rev="";
		int i=s.length()-1;
		while(i>=0) {
			while(i>=0 && s.charAt(i)==' ') i--; // for trail empty string;
			int j=i;
			if(i<0)  break;
			while(i>=0 && s.charAt(i) !=' ') i--;
				if(rev.isEmpty()) {
					
					rev=rev.concat(s.substring(i+1,j+1));
				}
			else {
				
				rev=rev.concat(" " +s.substring(i+1,j+1));
			}
			
		}	
		return rev;
		
	}

	public static void main(String[] args) {
		// REVERSE A STRING WORD BY WORD
		// INPUT: "blue is sky"
		//output: "sky is blue"
		
//		String  newString=reverseWords("blue is sky");
//		System.out.println(newString);
		
		String  newString=reverse("blue is sky");
		System.out.println(newString);
	}

}
