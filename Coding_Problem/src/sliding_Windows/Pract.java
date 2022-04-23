package sliding_Windows;


public class Pract {
	// for reset table;
	static int max_no=100005;
	static int reset[]=new int [max_no];
	public static void kmppreprocess(String p) {
		
		int i=0;
		int j=1;
		reset[0]=-1;
 		while(i<p.length()) {
			// check for resetting;
			while(j>=0 && p.charAt(i)!=p.charAt(j)) {
				j=reset[j];
			}
			i++;
			j++;
			reset[i]=j;
		}
	}
	public static void kmpsearch(String text, String p) {
		int i=0;
		int j=0;
		while(i<text.length()) {
			while(j>=0 && text.charAt(i)!=p.charAt(j)) {
				j=reset[j];
		}
				i++;
				j++;
			// if we hit the pattern 	
			if(j==p.length()) {
				System.out.println("pattern is found at "+" "+ (i-j) );
				// again searching for pattern in string that's why we reset;
				j=reset[j];
			}
		}
		
	}

	public static void main(String[] args) {
		for(int i=0;i<max_no;i++) {
			reset[i]=-1;
		}
		String p="abc";
		String text="habcdabc";
		kmpsearch(text, p);
	}
	

}
