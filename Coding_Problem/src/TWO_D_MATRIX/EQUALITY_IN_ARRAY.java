package TWO_D_MATRIX;

import java.util.Scanner;
/*
 * Equality in Array
Given an array of integers, determine the minimum number of elements to delete to leave only elements of equal value.

Example
arr= [1,2,2,3]

Delete the 2 elements 1 and 3 leaving arr as [2,2]. If both twos plus either the 1 or the 3 are deleted, it takes 3 deletions to leave either [3] or [1]. The minimum number of deletions is . 2

Input Format
The first line contains an integer n, the number of elements in arr. The next line contains n space-separated integers arr[i].

Constraints
0<n<=100
1<=arr[i]<=100
Sample Input
5
3 3 2 1 3
Sample Output
2

Explanation
Delete 2 and 1 to leave [3,3,3]. This is minimal. The only other options are to delete 4 elements to get an array of either [1] or [2].
 */

public class EQUALITY_IN_ARRAY {

	public static void main(String[] args) {
		// your code here
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int [n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int  [] freq= new int[101];
        int maxFreq=0;
        for(int i=0;i<n;i++){
            freq[arr[i]]++;
            if(maxFreq < freq[arr[i]]){
                maxFreq=freq[arr[i]];
            }
        }
        System.out.println(n-maxFreq);
    
	}

}
