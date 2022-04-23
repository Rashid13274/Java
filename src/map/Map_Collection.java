package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;



public class Map_Collection {

	public static void main(String[] args) {
		Map <String, Integer> m1= new HashMap<>();
		m1.put("apple",2);
		m1.put("banana", 3);
		m1.put("ornage", 4);
		m1.put("mango",24);
		m1.put("apple", 10);
		m1.put("name", null);
		m1.putIfAbsent("name", 0);
//		m1.remove("apple");
		
	
//		System.out.println(	m1.get("applew"));
//		System.out.println(m1.getOrDefault("apple", 12));
//		System.out.println(m1.keySet());  [banana, apple, ornage, name, mango]
//		System.out.println(m1.values());  [3, 10, 4, 0, 24]
//		System.out.println(m1.entrySet()); [banana=3, apple=10, ornage=4, name=0, mango=24]
		
		Set<Entry<String, Integer>> Entries=m1.entrySet();  // here we can read set of entry of string and integer and assigning it to m1 entryset();
		for(Entry<String,Integer> entry: Entries) {			// here we looping foreach()
			entry.setValue(entry.getValue()*10);			// here we setting the value of entries multiplication of 10;
		}
		System.out.println(m1);
	}

}

//MAP INTERFACE:-
/*
 * IN JAVA , ELEMENTS OF MAP ARE STORED IN KEY/VALUE PAIRS. 
 * KEYS ARE UNIQUE VALUES ASSOCIATED  WITH INDIVIDUALS VALUES.
 * A  MAP  CANNOT  CONTAINS  DUPLICATES VALUES, AND EACH KEY IS ASSOCIATED WITH SINGLE VALUES.
 * 
 * 
 * METHOD OF MAP:-
 * 
 * put(k,v):- inserts  the association  of  a key  and values  into the map. if the key is always present the new values replaces  the old values.
 * 
 * putAll():- inserts  all the  entries  from the specified  map  to this  map. 
 * 
 * putIfAbsent(k,v):-   inserts the association  if the key  is not already  associated  with the  values v
 * 
 * get(k):- returns the values  associated  with the specified  key K . if  the key  is not found , it returns null.
 * 
 * getOrDefalult(k, default value):-  returns the values  associated   with the specified   key K  if the  key is not  found it return null.
 * 
 * containsKey(k):- returns boolean values .
 * 
 * containsValue(v):- check if specified  value v is  present  in the  map or not.
 * 
 * replace(k, v):-  replace  the value  of the key  k with  the new  specified  value v.
 * 
 * 
 * INTERNAL WORKING OF  HASHMAP IN JAVA:-
 * HashMap uses an array table to store its key value pair. each  elemenet in the array holds  the head  of a linkedlist to avoid collosion. the hash of  
 * every key is  calculated  and the element is placed  in the array  using the hash function .
 * 
 * THE DEFAULT  CAPACITY IS KEPT  AT 16  AND LOAD  FACTOR AT 0.75. IT MEANS  DEFAULT SIZE OF ARRAY TABLE  IS 16 AND 
 * LOAD FACTOR IS USED WHEN OUR TABLE AT CAPACITY OF 75%  SO IT BASICALLY DOUBLE THE ARRAY TABLE.	
 */
