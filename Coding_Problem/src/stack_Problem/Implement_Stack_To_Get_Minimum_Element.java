package stack_Problem;
import java.util.*;
import java.util.Stack;

public class Implement_Stack_To_Get_Minimum_Element {
	Stack<Integer>s;
	 static Stack<Integer>ss;
	// constractor
	Implement_Stack_To_Get_Minimum_Element(){
		s=new Stack<Integer>();
		ss=new Stack<Integer>();
		
	}
	//push operation 
	void push(int a) {
		s.push(a);
		if(ss.size()==0 || a<=ss.peek()) {
			ss.push(a);
		}
		return;
	}
	// pop operation 
	int pop() {
		if(s.size()==0) {
			return -1;
		}int ans=s.peek();
		s.pop();
		if(ss.peek()==ans) {
			ss.pop();
		}
		return ans;
	}
	
	// print Minm ELEMENT OF STACK
	int  getMin() {
		if(s.size()== 0) {
			return -1 ;
		}
		return ss.peek();
	}
	// is Empty
	Boolean stackEmpty() {
		if(s.size()==0) {
			return true;
		}return false;
	}



	public static void main(String[] args) {
		Implement_Stack_To_Get_Minimum_Element s1=new Implement_Stack_To_Get_Minimum_Element();
		s1.push(10);
		s1.push(2);
		s1.push(4);
		s1.push(49);
		s1.push(1);
		s1.pop();
		
		System.out.println(s1.getMin());
		
		

	}
	

}
