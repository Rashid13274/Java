package sliding_Windows;

public class Kmp_Matching_alogoritham {

	

	public static void main(String[] args) {
	String str1="SILENT";
	String str2="SILETN";
	isAnagram(str1,str2);
	
	}
//	public static boolean palin(String str) {
//		// return in true or false;
//		boolean isPalindrome=false;
//		int i=0;
//		int j=str.length()-1;
//		while( i<str.length() &&  j>=0) {
//			if(str.charAt(i)!=str.charAt(j)) {
//				return false;
//			}
//			i++;
//			j--;
//		}
//		return true;
//	}
	
public static void isAnagram(String str1, String str2) {
 int all []=new int [256];
 boolean isAnagramm=true;
 for(char c:str1.toCharArray()) {
	 int index=(int) c;
	 all[index]++;
 }
 for(char c:str2.toCharArray()) {
	 int index=(int) c;
	 all[index]--;
 }
 for(int i=0;i<256;i++) {
	 if(all[i]!=0) {
		 isAnagramm=false;
		 break;
	 }
 }
 if(isAnagramm) {
	 System.out.println("ANGRAM");
 }else {
	 System.out.println("NOT ANAGRAM");
 }
}
}
