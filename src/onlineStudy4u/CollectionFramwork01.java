package onlineStudy4u;

import java.util.Enumeration;
//import java.util.ArrayList;
//import java.util.Enumeration;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.Iterator;

public class CollectionFramwork01 {

	public static void main(String[] args) {
		/*
		 * THREE CURSORS IN JAVA:-
		 * IF WE WANT TO  RETRIVE OBJECT ONE BY ONE  FROM THE COLLECTION  , THEN  WE  SHOULD  GO FOR  CURSOURS.
		 * THERE ARE  THREE CURSORS IN JAVA:
		 * 1. ENUMERATION ---  READ OPERATION 
		 * 2. ITERATORS---  READ AND REMOVE
		 * 3. LISTITERATOR--- READ, REMOVE,  REPLACE, ADD NEW OBJECT.
		 */
		
		
//		  ENUMERATION :-
		  
		Vector <Integer>v =new Vector<>();
		for(int i=0;i<=10;i++) {
			v.addElement(i);
			System.out.println(v);
		}
		Enumeration<Integer> e=v.elements();// here Enumeration is class and e is object refrence , element is function through which things are fetching  from v.
		while(e.hasMoreElements()) {
			Integer I =(Integer)e.nextElement();
			if(I%2==0) {
				System.out.println(I+" ");
			}
		}
		
		System.out.println(v);
		 
		
//		 ITERATORS:-
		/*
		 * 
		ArrayList<Object> l=new ArrayList<>();
		for(int i=0;i<=10;i++) {
			l.add(i);
			System.out.println(l);
		}
		
		Iterator itr =(Iterator) l.iterator();
		while(itr.next()) {
		Integer n=(Integer) itr.next();
		
		System.out.println(n);
		 */
		
		
		// LISTETERATOR:-
		// IT IS MOST POWERFUL CURSOR BUT IT HAS ONE LIMITATION IT CAN ONLY BE USED LIST INTERFACE CLASSES.
		/*
		 * 
		LinkedList <Object>l=new LinkedList<>();
		l.add("nikhil");
		l.add("parkash");
		l.add("isCool");
		System.out.println(l);
			
		 
		ListIterator <Object>ltr=l.listIterator();  // here ltr will help loop the list. since we don't use for loop to iterate.
		while(ltr.hasNext()) {
			String s=(String)ltr.next();           // here ltr  fetches first element from the list i.e  nikhil
			if(s.equals("isCool"))				   // HERE IT MATCHES  THAT CURRENT VALUE OF S IS  EQUAL TO nikhil. (no) cursour will go next.
													// AND IT CHECKS  NEXT  AND SO ON .
				ltr.add("boy");
			if(s.equals("nikhil"))
				ltr.set("mr, nikhil");
			System.out.println(l);
			//[mr, nikhil, parkash, isCool, boy]
		}
		 */
		
		}
		
	}


