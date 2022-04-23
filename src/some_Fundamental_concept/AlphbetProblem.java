package some_Fundamental_concept;

//import java.util.Arrays;
import java.util.Scanner;

public class AlphbetProblem {

	public static void main(String[] args) {
//		StringBuilder builder= new StringBuilder();
		Scanner sc= new Scanner(System.in);
		System.out.println("please enter the input");
		// taking the input from  user
		int input =sc.nextInt();
		// initializing the string to store  character sequence
		String series="";
		// using the loop storing alphbet
		for(int i=0;i<26;i++) {
			char  ch=(char)('A'+i);
			series+=ch;
//			builder.append(ch);
		}
		int sum=0;
		int sum1=0;
		// adding the sum of input like 111--> 1+1+1=3
		while(input>0) {
			int temp=input%10;
			sum=sum+temp;
			input/=10;
		}
		
		// IF SUM IS OUT OF 25 INDEXES OF ALPHABET 
		
			if(sum>=26) {	
			 while(sum!=0) {
				int temp2=sum%10;
				 sum1+=temp2;
				sum/=10;
			}
			}	
			
			// SHOWING THE OUTPUT  OF SUM FOR VALIDATION
			System.out.println(sum);
			System.out.println(sum1);
//			int temp3=sum;
//			sum=sum1;
//			sum1=temp3;
//			
//			System.out.println(sum);
			
			// DISPLAYING THE REQUIRED RESULT.
			if(sum1<26 && sum1>0) {
			System.out.println(series.charAt(sum1));
				
			}else {
				System.out.println(series.charAt(sum));
			}
			
		}
		
	}


