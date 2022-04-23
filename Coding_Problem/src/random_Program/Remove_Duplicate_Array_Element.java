package random_Program;
import java.util.Arrays;

public class Remove_Duplicate_Array_Element {

	public static void main(String[] args) {
		int arr[]= {1,1,2,2,3,34,4,5,5,66,7,7,8};
		int n=arr.length;
		 n=rm(arr,n);
		 //print updated array
		 for(int i=0;i<n;i++) {
			 System.out.print(arr[i]+" ");
		 }
	
	}

	public static int  rm(int arr[], int n) {
		Arrays.sort(arr);
		int temp[]=new int [n];
		int j=0;
		for(int i=0;i<n-1;i++) {
			if(arr[i]!=arr[i+1]) {
				temp[j++]=arr[i];
			}
		}
		temp[j++]=arr[n-1];
		for(int j1=0;j1<j;j1++) {
			arr[j1]=temp[j1];
		}
		return j;
		
	}
	
	}

