package map;

import java.util.Hashtable;
import java.util.Map.Entry;

public class Map_Collection_new04 {

	public static void main(String[] args) {
/*
		JAVA HASHTABLE CLASS:-
			JAVA HASHTABLE CLASS IMPLEMENTS A HASHTABLE, WHICH MAPS KEYS TO VALUES.
			IT INHERITS DICTIONARY CLASS AND IMPLEMENTS THE MAP INTERFACE.
		
		POINTS TO REMEMBER:-
		
		1.) A HASHTABLE IS AN ARRAY OF A LIST. EACH LIST IS KNOWN AS A BUCKET. 
			THE POSITION OF THE BUCKET IS IDENTIFIED BY CALLING THE HASHCODE() METHOD. 
			A HASHTABLE CONTAINS VALUES BASED ON THE KEY.
			
		2.) JAVA HASHTABLE CLASS CONTAINS UNIQUE ELEMENTS.
			JAVA HASHTABLE CLASS DOESN'T ALLOW NULL KEY OR VALUE.
			
		3.)	JAVA HASHTABLE CLASS IS SYNCHRONIZED.
			THE INITIAL DEFAULT CAPACITY OF HASHTABLE CLASS IS 11 WHEREAS LOADFACTOR IS 0.75
			
			Hashtable class declaration:
			LET'S SEE THE DECLARATION FOR JAVA.UTIL.HASHTABLE CLASS.
			public class Hashtable<K,V> extends Dictionary<K,V> implements Map<K,V>, Cloneable, Serializable  
			

			
			
			
*/
		
		//EXAMPLE:1
		 Hashtable<Integer,String> hm=new Hashtable<Integer,String>();  
		  
		  hm.put(100,"Amit");  
		  hm.put(102,"Ravi");  
		  hm.put(101,"Vijay");  
		  hm.put(103,"Rahul");  
		  
		  for(Entry<Integer, String> mm: hm.entrySet()){  
		   System.out.println(mm.getKey()+" "+mm.getValue());  
		  }  
		  
		  //EXAMPLE:2
		  //Java Hashtable Example: remove()

		  Hashtable<Integer,String> map=new Hashtable<Integer,String>();        
		     map.put(100,"Amit");    
		     map.put(102,"Ravi");   
		     map.put(101,"Vijay");    
		     map.put(103,"Rahul");    
		     System.out.println("Before remove: "+ map);    
		       // Remove value for key 102  
		       map.remove(102);  
		       System.out.println("After remove: "+ map);  
		       
		       
		       
		       
		       // EXAMPLE:3
		       // Java Hashtable Example: putIfAbsent()
		       
		       Hashtable<Integer,String> map1=new Hashtable<Integer,String>();          
		       map1.put(100,"Amit");    
		       map1.put(102,"Ravi");   
		       map1.put(101,"Vijay");    
		       map1.put(103,"Rahul");    
		       System.out.println("Initial Map: "+map1);  
		       //Inserts, as the specified pair is unique  
		       map1.putIfAbsent(104,"Gaurav");  
		       System.out.println("Updated Map: "+map1);  
		       //Returns the current value, as the specified pair already exist  
		       map1.putIfAbsent(101,"Vijay");  
		       System.out.println("Updated Map: "+map1);
		   }     
		  
		  
	 
		
/*
		* DIFFERENCE BETWEEN HASHSET AND HASHTABLE:-
		 
		HASHMAP	HASHTABLE
		1) HASHMAP IS NON SYNCHRONIZED. IT IS NOT-THREAD SAFE AND CAN'T BE SHARED BETWEEN MANY THREADS WITHOUT PROPER SYNCHRONIZATION CODE.
			HASHTABLE IS SYNCHRONIZED. IT IS THREAD-SAFE AND CAN BE SHARED WITH MANY THREADS.
			
		2) HASHMAP ALLOWS ONE NULL KEY AND MULTIPLE NULL VALUES.	
		HASHTABLE DOESN'T ALLOW ANY NULL KEY OR VALUE.
		
		3) HASHMAP IS A NEW CLASS INTRODUCED IN JDK 1.2.
			HASHTABLE IS A LEGACY CLASS.
			
		4) HASHMAP IS FAST.
			HASHTABLE IS SLOW.
			
		5) WE CAN MAKE THE HASHMAP AS SYNCHRONIZED BY CALLING THIS CODEMAP M = COLLECTIONS.SYNCHRONIZEDMAP(HASHMAP);
			HASHTABLE IS INTERNALLY SYNCHRONIZED AND CAN'T BE UNSYNCHRONIZED.
			
		6) HASHMAP IS TRAVERSED BY ITERATOR.
			HASHTABLE IS TRAVERSED BY ENUMERATOR AND ITERATOR.
			
		7) ITERATOR IN HASHMAP IS FAIL-FAST.	
		ENUMERATOR IN HASHTABLE IS NOT FAIL-FAST.
		
		8) HASHMAP INHERITS ABSTRACTMAP CLASS.
			HASHTABLE INHERITS DICTIONARY CLASS.
*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	

}
