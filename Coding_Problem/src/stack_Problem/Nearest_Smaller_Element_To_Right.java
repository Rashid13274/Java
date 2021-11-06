package stack_Problem;


import java.util.Stack;
import java.util.Vector;

/*NEXT SMALLET ELEMENT TO LEFT :-
 * ALL PROBLEM BASED SIMILAR CONCEPT.
 *  INPUT:[3,5,6,2,1]
 *  OUTPUT:[5 3 1 1 -1 -1 ]
 * */

public class Nearest_Smaller_Element_To_Right {

	public static void main(String[] args) {
//		int arr[]={3,5,6,2,1};
		int arr[]= {6,5,3,7,1,9};
		int n=arr.length;
		nse(arr,n);
		
	}
	public static void nse(int[]arr, int n){
		//WHEN WE NEED TO TRAVERSE RIGHT WE USE LIFO CONCEPT.
		// i.e FOR CHEKING 5 FIRST IT MUST BE  ADDED IN THE LAST TO STACK.
		Vector<Integer> v=new Vector<>();
		Stack<Integer> s=new Stack<>();
		for(int i=arr.length-1;i>=0;i--) {
			if(s.size()==0) {
				v.add(-1);
			}else if(s.size()>0 && s.peek()<arr[i]) {
				v.add(s.peek());
			}else if(s.size()>0 && s.peek()>=arr[i]) {
				while(s.size()>0 && s.peek()>=arr[i]) {
					s.pop();
				} if(s.size()==0) {
					v.add(-1);
				}else {
					v.add(s.peek());
				}
			}
			s.add(arr[i]);
		}
		// traversing the vector 
		for(int j=v.size()-1;j>=0;j--) {
			System.out.print(v.get(j)+" ");
		}

	}

}
