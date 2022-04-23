package interview_Problem01;
import java.lang.*;
import java.util.*;
import java.io.*;
public class Reverse_a_string {
	

	public static void main(String[] args) {
		/*
		 
	    Using built in reverse() method of the StringBuilder class: String class does not have reverse() method, 
		we need to convert the input string to StringBuilder, which is achieved by using the append method of StringBuilder.
		After that, print out the characters of the reversed string by scanning from the first till the last index. 
	   */
		
//		String input="abd";
//		StringBuilder input1= new StringBuilder();
//		input1.append(input);
//		input1.reverse();
//		System.out.println(input1);
		
		
		/*
		Converting String to character array: The user input the string to be reversed. 
		Method: 
		1. First, convert String to character array
		   by using the built in Java String class 
		   method toCharArray().
		2. Then, scan the string from end  to start, 
		   and print the character one by one.
		   
	    */
		
		/*
		String input="geeksforgeeks";
		converting the  string into character array using built in  toCharArray() method;
		 char [] try1=input.toCharArray();
		 for(int i = try1.length-1;i>=0;i--) {
			 System.out.print(try1[i]);
			 
		 }
		 */	 
		
		// PREINCREMENT AND POST INCREMENT:-
		
//		++a increments and then uses the variable.
//		a++ uses and then increments the variable.
//		System.out.println(a++);  		// 1
//		System.out.println(++a);		// 3
		
//		int i=1;
//		int b;
//		b=++i;
//		b=i++;  
//		System.out.println(b + " "+i);
//		int a=1;
		
		

		
//	  int c=++a;
//	      c=a++;
//	      System.out.println(c);
//	      System.out.println(c);
//	      System.out.println(++c);

		//question:-1
		
//		int a=5, i;
//		i=++a + ++a + a++;  20 ,8
//		i=a++ + ++a + ++a;	20,8
//		a=++a + ++a + a++;	20,8
		
//		System.out.println(i);
//		System.out.println(a);
		
	     
	  
	}
			

}
