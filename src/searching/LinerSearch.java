package searching;

import java.util.Scanner;

public class LinerSearch {

	public static void main(String[] args) {
//		 Scanner sc =new Scanner(System.in);
//		int  n =sc.nextInt();
//		int target =sc.nextInt();
//		int [] arr=new int[n];
//		for(int i=0;i<n;i++) {
//			int a=sc.nextInt();
//			
//			arr[i]=a;
//		}
//		for(int i=0;i<n;i++) {
//			if(arr[i]==target) {
//				System.out.println(i);
//			}
//		}
//	
//
		
//		int []arr= {1,2,3,4,56,7,5};
//	linear(arr);
//		

	}
	//FIND MIN AND MAX  ELEMENT OF AN ARRAY
	public static void linear(int []arr) {
		int max;
		for(int i=0;i<arr.length;i++) {
			max=0;
			if(max<arr[i]) {
				max=arr[i];
			}
			System.out.println(max);
			
		}
	}


	
	

}
