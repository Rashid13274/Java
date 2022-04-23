package onlineStudy4u;

//import java.util.ArrayList ;
import java.util.*;

//import com.sun.tools.javac.util.List;

public class Arraylist {

	public static void main(String[] args) {
		/*
		 * 
		// storing Hetrogeious element
		ArrayList <Object> list=new ArrayList<>();
		list.add("hello");
		list.add("rashid");
		list.add(123);
		list.add(null);
		list.add(true);
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.remove(3));
		System.out.println(list);
		
		 */
		//STORING  HOMOGENIOUS  ELEMENT
		/*
		 * 
		ArrayList <Integer>arr=new ArrayList<Integer>();
		arr.add(12);
		arr.add(-121);
		arr.add(1122);
		System.out.println(arr);
		ArrayList<String> str=new ArrayList<String>();
		str.add("hello");
		str.add("md");
		str.add("rashid");
		System.out.println(str);
		// we can implement Only using  list since it's is parent of child arraylist.
		// there are various function and method  are there that can be implement .
		 */
		
		//LINKEDLIST:-
		// STORING HETROGENIOUS ELEMENT;
		/*
		LinkedList <Object> list=new LinkedList<>();
		list.add(12);
		list.add('h');
		list.add("hello");
		list.add(false);
		list.add(-1.1);
		list.add(1, "hetrogenious");
		list.set(4, "last");
		list.addLast("FINISH");
		System.out.println(list);
		 */
		 //STORINT HOMOGENIOUS ELEMENT:-
		/*
		 * 
		LinkedList <Integer>list =new LinkedList<Integer>();
		list.add(1);
		list.add(2);  
		list.add(3);
		list.add(4);
		System.out.println(list);
		
		 */
		/*
		 * VECTOR:- BEHIND THE SECEN IT ACTS LIKE AS A ARRRAY INITIALLY IT HAS ALLOCATED 10 SPACE  AND ADDING THE 11TH  ELEMENT RESULTS IN VECTOR ADDING 
		 * 10 MORE SPACES i.e 20 	SPACES TOTAL.
		 * 
		Vector <Integer>v=new Vector<>();
		System.out.println(v.size());
		System.out.println(v.capacity());
		for(int i=1;i<=10;i++) {
			v.add(i);
			System.out.println(v);
			System.out.println(v.capacity());
		}
		v.add(11);
		System.out.println(v);
		System.out.println(v.capacity());
		
		 */ 
		
		//STACK:-
		/*
		 * CHILD CLASS OF VECTOR
		 * METHODS ARE PUSH(),POP, PEEK, SEARCH,  EMPTY;
		 * 
		Stack <String> s=new Stack<>();
		s.push("a");
		s.push("b");
		s.push("c");
		System.out.println(s);
		 System.out.println(s.peek()); 
		 s.pop();
		 System.out.println(s);
		
		 System.out.println(s.search("z")); // element not found -1;
		 */
	}
	

}
 