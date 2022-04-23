package map;

import java.util.HashSet;
import java.util.Set;

public class Set_Collection {

	public static void main(String[] args) {
		Set <String> s1=new HashSet <>();
		s1.add("apple");
		s1.add("mango");
		s1.add("banana");
		s1.add("orange");
		
		Set <String> s2 =new HashSet<>();
		s2.add("mangos");
		s2.add("apple2");
		s2.add("any");
		s2.add("garment");
		s2.add("garment");
		s1.addAll(s2);
		s1.retainAll(s2);
		
		System.out.println(s1); 
		// [banana, orange, apple, mango]
		// it didn't give the result in order since hashset uses the functionality of hashfunction to store.
		

	}

}
/*
 * SET INTERFACE:-
 * THE SET INTERFACE OF THE JAVA  COLLECTION FRAMEWORK  PROVIDES  THE  FEATURES OF THE MATHEMATICAL SET IN THE JAVA. IT EXTENDS
 * THE COLLECTION  INTERFACE .UNLIKE THE LIST INTEFACE,SET CANT'T  CONTAIN DUPLICATE  ELEMENT.
 * 
 * IN ORDER TO  USE FUNCTIONALITIES OF THE SET  INTERFACE  ,WE CAN USE  THESE   CLASSES
 * 1. HashSet
 * 2. LinkedHashSet
 * 3. EnumSet
 * 4. TreeSet
 * 
 * THERE IS MAINLY TWO IMPORTANT SET   ARE USED HashSet and TreeSet.
 * 
 * Method of  Set:-
 * 
 * add(element)
 * addAll(Collection)
 * remove(element)
 * removeAll()
 * retainAll():- THIS METHOD IS USED FOR  INSERSECTION  BETWEEN  TWO SET;
 * clear();
 * contains()
 * containsAll()
 * isEmpty()
 * toArray()
 * 
 * OPERATION OF SETS:-	
 *  
 *  UNION:-   TO  GET  THE UNION OF TWO SETS  X AND Y, WE USE x.addALL(Y);
 *  
 *  INTERSETION:- TO THE GET THE INTERSECTION  OF TWO SETS X AND Y  , WE CAN USE X.RETAINall(y);
 *  
 *  SUBSET:-  TO CHECH IF X IS SUBSET  OF Y , WE CAN USE  Y.CONTAINSALL(X);
 * 
 *   
 */