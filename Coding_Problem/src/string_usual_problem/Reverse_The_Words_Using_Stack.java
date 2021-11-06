package string_usual_problem;

import java.util.Scanner;
import java.util.Stack;

public class Reverse_The_Words_Using_Stack {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner st=new Scanner(System.in);
		System.out.println("please enter the string");
		String c=st.nextLine();
		Stack<Character> stak=new Stack<>();
		// PUSHING THE ELEMENT IN THE STACK
		for(int i=0;i<c.length();i++) {
			stak.push(c.charAt(i));
		}
		// REVERSING  THE STRING  
		while(!stak.empty()) {
			System.out.print(stak.pop());
		}
		
				//OR
		
//		for(int j=0;j<c.length();j++) {
//			System.out.print(stak.pop());
//		}

	}

}
