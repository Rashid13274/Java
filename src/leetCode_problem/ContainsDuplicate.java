package leetCode_problem;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {

	public static void main(String[] args) {
//		System.out.println(duplicate(num));
		System.out.println(checkDuplicate(num2));
		

	}
	
	
		static int[]num= {2,4,5,6,9,7};
	static boolean duplicate(int[]num) {
		HashSet<Integer>number=new HashSet<>();
		for(int i = 0;i<num.length;i++) {
			if(number.contains(num[i])) return true;
			number.add(num[i]);
		}
		return false;
	}
	static int [] num2= {4,3,5,1,1,2,2};
	static boolean  checkDuplicate(int[]num2) {
		Arrays.sort(num2);
		for(int i=0; i<num2.length-1;i++) {
			if(num[i]==num[i+1]) return true;
		}
		return false;
	}

}
