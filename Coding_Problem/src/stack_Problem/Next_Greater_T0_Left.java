package stack_Problem;
/*NEXT GREATER TO LEFT 
 * INPUT:[10,5,4,6]
 * OUTPUT:[-1, 10, 5, 10]
 * 
 * */

import java.util.Stack;
import java.util.Vector;

public class Next_Greater_T0_Left {

	public static void main(String[] args) {
		int arr[]= {10,5,7,6};
		int n=arr.length;
		
		System.out.println(nge(arr, n));
		

	}
	public static   Vector<Integer> nge(int arr[], int n) {
		Vector<Integer> v=new Vector<>();
		Stack<Integer> s=new Stack<>();
		for(int i=0;i<arr.length;i++) {
			if(s.size()==0) {
				v.add(-1);
			}else if(s.size()>0 && s.peek()>arr[i]) {
				v.add(s.peek());
			}else if(s.size()>0 && s.peek()<=arr[i]) {
				while(s.size()>0 && s.peek()<=arr[i]) {
					s.pop();
				}
				if(s.size()==0) {
					v.add(-1);
				}else {
					v.add(s.peek());
				}
			}
			
			s.add(arr[i]);
			
		}
		return v;
		
	
		}
		
	}


