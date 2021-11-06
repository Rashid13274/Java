package stack_Problem;


import java.util.Stack;
import java.util.Vector;

/*NEXT LARGEST ELEMENT OR NEXT GREATER ELEMENT:-
 * PROBLEM STATEMENT: GIVEN AN ARRAY PRINT  THE NEXT GREATER ELEMENT(NGE) FOR EVERY ELEMENT . THE NEXT GREATER ELEMENT FOR AN ELMENT ON RIGHT
 * SIDE OF X IN THE ARRAY  ELEMENTS FOR WHICH  NO GREATER  EXIST, CONSIDER  THE NEXT GREATER ELEMENT  AS -1;
 * 
 * INPUT: [4,5,2,25]
 * OUTPUT:[5,25,25,-1]
 * 
 * */

public class Next_Largest_Element_OR_Next_Greater_TO_Right {

	public static void main(String[] args) {
		int arr[]= {4,5,2,25};
		int n=arr.length;
		
		nge(arr, n);

	}
	// AN ARRAY AND LENGTH OF ARRAY GIVEN
	public static  void  nge(int arr[], int n) {
		// VECTOR TO STORE OUTPUT
		Vector<Integer> v=new Vector<>();
		// STACK TO  TO VALIDATE CONDITION
		Stack<Integer> s=new Stack<>();
		// HERE WE LIFO  TO FULL FILL THE DESIRE CONDITION.
		for(int i=n-1;i>=0;i--) {
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
		
		// displaying the values
		for(int j=v.size()-1;j>=0;j--) {
			 System.out.println(v.get(j));
		}
		
	}

}
