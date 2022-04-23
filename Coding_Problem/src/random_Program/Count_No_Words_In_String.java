package random_Program;

public class Count_No_Words_In_String {

	public static void main(String[] args) {
		String s="hi my namem is md rashid.";
		System.out.println(count(s));
		
	}
//	count number of words in the string.
	
	public static int   count(String str) {
		int count =0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!=' ') {
				count++;
				while(str.charAt(i)!=' ' && i<str.length()-1) {
					i++;
				}
			}
		}
		return count;
	}
	
}
