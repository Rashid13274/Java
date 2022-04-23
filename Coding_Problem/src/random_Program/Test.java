package random_Program;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
					
				}
		int j=arr.length;
		
		bubble(arr,j);

	}
	public static void  bubble(int arr[], int j) {
		for(int i=0;i<j-i-1;i++) {
			for(int k=0;k<j-1;k++) {
				if(arr[k]>arr[k+1]) {
					int temp= arr[k+1];
					arr[k+1]=arr[k];
					arr[k]=temp;
				}
			}
		}
		for(int arrElement:arr) {
			System.out.println(arrElement+" ");
		}
	
	}

}
