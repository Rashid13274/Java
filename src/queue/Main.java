package queue;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		// this is implemented using built in collection framework. here like linked list;
		Queue <Integer> q=new LinkedList<>();
		q.add(12);
		q.add(13);
		q.add(14);
		q.add(15);
		q.add(16);
		System.out.println(q);
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.poll());
	}
		/*
		 * QUEUE INTERFACE:-
		 * THE QUEUE  INTERFACE  OF THE JAVA  COLLECTION  FRAMEWORK  PROVIDES  THE FUNCTIONALITY  OF THE  QUEUE DATA STRUCTURE . IT EXTENDS  THE  COLLECTION  INTERFACE.
		 * 
		 * IN ORDER TO   USE THE FUNCTONALITY  OF QUEUE  , WE NEED  TO USE  CLASSES THAT  IMPLEMENT IT :
		 * 1. ArrayDeque
		 * 2. LinkedList
		 * 3. Priority Queue
		 * 
		 * WORKING OF QUEUE DATA STRUCTURE:-
		 * IN QUEUES, ELEMENT ARE STORED  AND  ACCESSED  IN FIRST IN, AND FIRST OUT MANNER.  THAT IS , ELEMENT  ARE ADDDED FROM  THE BEHIND  AND REMOVED  FROM  THE FRONT.
		 * 
		 * METHOD OF QUEUE:-
		 * (THROWS EXCEPTION)
		 * add()
		 * remove()
		 * element()
		 * 
		 * (RETURN FALSE/ NULL) METHOD:-
		 * offer()
		 * poll()
		 * peek()
		 * 
		 */

}
