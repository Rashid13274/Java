package string_usual_problem;

public class Reverse_The_Words_In_String {

	public static void main(String[] args) {
		String str="racecar racE";
		// stroing into string array
		String[]  words=str.split(" ");
		// finally  revString at the end;
		String revString ="";

		for(int i=0;i<words.length;i++) {
			// storing the word one by one;
		String word=words[i];
			String revWord="";
			for(int j=word.length()-1;j>=0;j--) {
				revWord=revWord+word.charAt(j);
			}
			// finally adding the  reverse word to reverstring;
			revString=revString+revWord+" ";
		}
		// printing the string at the end;
		System.out.println(revString);

	}

}
