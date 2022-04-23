package String;

public class Anagram02 {

	public static void main(String[] args) {
		String a="silent";
		String b="listen";
		boolean isAnagram=true;
		int all[]=new int[256];
		int bll[]=new int [256];
		for(char c :a.toCharArray())  // a.toCharArray() basically convert into charactor and store it into arrays;
		{ 
			int index=(int) c;		// HERE WE ARE CONVERTING THE CHAR INTO INT (AS ASCII VALUE)
			all[index]++;			// AND STOTING IT INTO ARRAYS;  
		}
		for(char c :b.toCharArray())  
		{ 
			int index=(int) c;
			bll[index]++;
			
		}
		for(int i=0;i<256;i++) {
			if(all[i]!=bll[i])			//CONDITION CHECKING IN TWO ARRAYS
			{
				isAnagram=false;
				break;
			}
				
		}
		if(isAnagram) {
			
			System.out.println("Anagram");
		}
		else {
			System.out.println("not Anagram");
		}
		
	}

}
