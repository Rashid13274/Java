package map;

import java.util.Map;
import java.util.Map.Entry;

//import jdk.nashorn.internal.runtime.regexp.joni.constants.Traverse;

import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Map_Collection_new03 {

	public static void main(String[] args) {
		// TREEMAP:-
/*
		JAVA TREEMAP CLASS IS A RED-BLACK TREE BASED IMPLEMENTATION. 
		IT PROVIDES AN EFFICIENT MEANS OF STORING KEY-VALUE PAIRS IN SORTED ORDER.
		
		THE IMPORTANT POINTS ABOUT JAVA TREEMAP CLASS ARE:
		
		JAVA TREEMAP CONTAINS VALUES BASED ON THE KEY. 
		IT IMPLEMENTS THE NAVIGABLEMAP INTERFACE AND EXTENDS ABSTRACTMAP CLASS.
		JAVA TREEMAP CONTAINS ONLY UNIQUE ELEMENTS.
		JAVA TREEMAP CANNOT HAVE A NULL KEY BUT CAN HAVE MULTIPLE NULL VALUES.
		JAVA TREEMAP IS NON SYNCHRONIZED.
		JAVA TREEMAP MAINTAINS ASCENDING ORDER.

		TREE MAP CLASS DECLARATION:-
		public class TreeMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K,V>, Cloneable, Serializable 
*/
		//EXAMPLE:1
		
		TreeMap<Integer,String> map=new TreeMap<>();
		map.put(9, "md");
		map.put(5, "rashid");
		map.put(1, "ansari");
		map.put(10, "rahman");
		for(Entry<Integer, String> b:map.entrySet()) {
			System.out.println(b.getKey()+" "+b.getValue());
		}
		System.out.println();
		
		// EXAMPLE:2
		//Java TreeMap Example: NavigableMap
		
		   NavigableMap<Integer,String> map1=new TreeMap<Integer,String>();    
		      map1.put(100,"Amit");    
		      map1.put(102,"Ravi");    
		      map1.put(101,"Vijay");    
		      map1.put(103,"Rahul");   
		      System.out.println("maintain the ascending order"+map1.descendingMap());
		      //Returns key-value pairs whose keys are less than or equal to the specified key.  
		      System.out.println("headMap: "+map1.headMap(102,true));  
		      //Returns key-value pairs whose keys are greater than or equal to the specified key.  
		      System.out.println("tailMap: "+map1.tailMap(102,true));  
		      //Returns key-value pairs exists in between the specified key.  
		      System.out.println("subMap: "+map1.subMap(100, false, 102, true));   
		  
		      
		      //EXAMPLE:3
		      
		      
		      SortedMap<Integer,String> map3=new TreeMap<Integer,String>();    
		      map3.put(100,"Amit");    
		      map3.put(102,"Ravi");    
		      map3.put(101,"Vijay");    
		      map3.put(103,"Rahul");    
		      //Returns key-value pairs whose keys are less than the specified key.  
		      System.out.println("headMap: "+map3.headMap(102));  
		      //Returns key-value pairs whose keys are greater than or equal to the specified key.  
		      System.out.println("tailMap: "+map3.tailMap(102));  
		      //Returns key-value pairs exists in between the specified key.  
		      System.out.println("subMap: "+map3.subMap(100, 102));    
		 
		      
		      //EXAMPLE:-4
		      //BOOK EXAMPLE:
		      
		      Book_Map_Collection_new03 b1=new Book_Map_Collection_new03(1,"ravan", "chaman",2002);
		      Book_Map_Collection_new03 b2=new Book_Map_Collection_new03(2,"ravan", "chaman",2002);
		      Book_Map_Collection_new03 b3=new Book_Map_Collection_new03(3,"ravan", "chaman",2002);
		      Book_Map_Collection_new03 b4=new Book_Map_Collection_new03(4,"ravan", "chaman",2002);
		      
		      
		      Map<Integer,Book_Map_Collection_new03> books=new TreeMap<>();
		      books.put(1,b1);
		      books.put(2,b2);
		      books.put(3,b3);
		      books.put(4,b4);
		      for(Map.Entry<Integer,Book_Map_Collection_new03>entri:books.entrySet()){
		    	  int key=entri.getKey();
		    	 System.out.println("DETAILS"+key);
		    	 Book_Map_Collection_new03 bb=entri.getValue();
		    	 System.out.println(bb.id+" "+bb.name+" "+bb.author+" "+bb.published_year);
		    	  
		    	  
		      }
	}

}
