package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Map_Collection_new01 {

	public static void main(String[] args) {
/*
	 * JAVA MAP INTERFACE:-A MAP CONTAINS VALUES ON THE BASIS OF KEY, I.E. KEY AND VALUE PAIR. 
	 * EACH KEY AND VALUE PAIR IS KNOWN AS AN ENTRY. A MAP CONTAINS UNIQUE KEYS.
	 * 
	 * A MAP IS USEFUL IF YOU HAVE TO SEARCH, UPDATE OR DELETE ELEMENTS ON THE BASIS OF A KEY.
	 * 
	 * A MAP CAN'T BE TRAVERSED, SO YOU NEED TO CONVERT IT INTO SET USING KEYSET() OR ENTRYSET() METHOD.
	 * 
	 * CLASS:-
	 * HASHMAP:	HASHMAP IS THE IMPLEMENTATION OF MAP, BUT IT DOESN'T MAINTAIN ANY ORDER.
	 * LINKEDHASHMAP: LINKEDHASHMAP IS THE IMPLEMENTATION OF MAP. IT INHERITS HASHMAP CLASS. IT MAINTAINS INSERTION ORDER.
	 * TreeMap:	TreeMap is the implementation of Map and SortedMap. It maintains ascending order.
	 * 
	 * 
	 * 
*/
		
		//HASHMAP:-
		
		//NON GENERIC:-
		Map<Integer, String> m1=new HashMap<Integer, String>();
		m1.put(1,"raman");
		m1.put(2, "naman");
		m1.put(3,"karan");
		m1.put(4, "madan");
		m1.put(5,"chaman");
		m1.put(6, "suman");
		m1.put(6,"param");
		//TRAVERSING THE MAP
		Set<Entry<Integer, String>> set =m1.entrySet(); //Converting to Set so that we can traverse 
		Iterator<Entry<Integer, String>> itr =set.iterator();
		while(itr.hasNext()) {
			 //Converting to Map.Entry so that we can get key and value separately  
			Entry<Integer, String> entry=itr.next();
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		
		System.out.println();
		
		  Map<Integer,String> map=new HashMap<Integer,String>();  
		  map.put(100,"Amit");  
		  map.put(101,"Vijay");  
		  map.put(102,"Rahul");  
		  //Elements can traverse in any order  
		  for(Entry<Integer,String> m:map.entrySet()){  
		   System.out.println(m.getKey()+" "+m.getValue());  
		  }  
		  System.out.println();
		  

	}

}
