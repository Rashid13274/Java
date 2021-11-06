package stack_Problem;


/*Nearest smaller to left  or Nearest smaller elmeent
 * INPUT :[3, 5, 6, 2, 1]
 * OUTPUT:[-1, 3, 5, -1, -1]
 * */

import java.util.Stack;
import java.util.Vector;

public class Nearest_Element_To_Left {

	public static void main(String[] args) {
			int arr[]={3,5,6,2,1};
			int n=arr.length;
			System.out.println(nse(arr,n));
	}
	public static Vector<Integer> nse(int[]arr, int n){
		Vector<Integer> v=new Vector<>();
		Stack<Integer> s=new Stack<>();
		for(int i=0;i<arr.length;i++) {
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
		return v;

	}
}
