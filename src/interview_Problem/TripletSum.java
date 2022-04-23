//package interview_Problem;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class TripletSum {
//	
//	
//	//Function to find if there exists a triplet in the 
//    //array A[] which sums up to X.
//	Scanner sc=new Scanner(System.in);
//	
//			int n=sc.nextInt();
//		  int A=sc.nextInt();
//		  
//    public static boolean find3Numbers(int A[], int n) { 
//        Arrays.sort(A);
//        int k=0;
//        for(int i=0;i<n-2;i++){
//            
//       if(twoSum(A,-A[i], i+1)){
//           return true;
//       }
//        }
//    return false;
//    
//    }
//    public static boolean twoSum(int a[], int x, int i+1 ){
//        int j=a.length-1;
//        while(i<j){
//            if(a[i]+a[j]<x){
//                i++;
//            }else if(a[i]+a[j]>x){
//                j--;
//            }
//            return true;
//        }
//        return false;
//    }
//
//
//
//	public static void main(String[] args) {
//		TripletSum d=new TripletSum();
//		d.find3Numbers(A,n );
//		
//
//	}
//
//
