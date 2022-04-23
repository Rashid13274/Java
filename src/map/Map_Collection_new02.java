package map;


import java.util.HashMap;
import java.util.Map;

public class Map_Collection_new02 {

	public static void main(String[] args) {
		// HASHMAP:-
		
/*		POINTS TO REMEMBER
		JAVA HASHMAP CONTAINS VALUES BASED ON THE KEY.
		JAVA HASHMAP CONTAINS ONLY UNIQUE KEYS.
		JAVA HASHMAP MAY HAVE ONE NULL KEY AND MULTIPLE NULL VALUES.
		JAVA HASHMAP IS NON SYNCHRONIZED.
		JAVA HASHMAP MAINTAINS NO ORDER.
		THE INITIAL DEFAULT CAPACITY OF JAVA HASHMAP CLASS IS 16 WITH A LOAD FACTOR OF 0.75.
*/
		
		// JAVA MAP:COMPARING BY KEY .
		
		Map<Integer,String> map1=new HashMap<Integer,String>();          
	      map1.put(100,"Amit");    
	      map1.put(101,"Vijay");    
	      map1.put(102,"Rahul");   
	      //Returns a Set view of the mappings contained in this map        
	      map1.entrySet()  
	      //Returns a sequential Stream with this collection as its source  
	      .stream()  
	      //Sorted according to the provided Comparator  
	      .sorted(Map.Entry.comparingByKey())  
	      //Performs an action for each element of this stream  
	      .forEach(System.out::println);  
	      System.out.println();
	      
	      Map<Integer,String> map2=new HashMap<Integer,String>(); 
	      map2.put(1,"md");
	      map2.put(02, "rashid");
	      map2.put(03, "ansari");
	      
	      // JAVA MAP:COMPARING BY KEY IN DESCENDING ORDER.
	      //map2.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(System.out::println);
	      
	      
	      // JAVA MAP: COMPARING BY VALUE 
	     // map2.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
	      
	      // JAVA MAP: COMPARING BY VALUE IN DESCENDING ORDER
//	      map2.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
	    
	      
	      
	 }  

	}


