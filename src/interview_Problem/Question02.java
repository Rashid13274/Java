package interview_Problem;

import java.util.Scanner;

public class Question02 {
	// REVERSE AN ARRAY BY ITERATIVE RECURSIVELY;

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		System.out.println("enter the size of an array");
		int n=input.nextInt();
		
		
		//Declare an array
		 int arr[]=new int [n];
		 System.out.println("enter an arrays value");
		 // input arrays value
		 for(int i=0;i<n;i++) {
			 arr[i]=input.nextInt();
		 }
		 int temp;
		 
		 int start=0;
		 int end =n-1;
		 while(start<end) {
			 temp=arr[start];
			 arr[start]=arr[end];
			 arr[end]=temp;
			 start++;
			 end--;
		 }
		 System.out.println("printing an array after reverse");
		 for(int j=0; j<n;j++) {
			 System.out.println(arr[j]);
		 }

	}

}
