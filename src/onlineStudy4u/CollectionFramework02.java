  package onlineStudy4u;

//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.PriorityQueue;
//import java.util.TreeSet;

public class CollectionFramework02 {

	public static void main(String[] args) {
		/*
		 * SET:- CHILD INTERFACE OF  COLLECTION
		 * DUPLICATES ARE NOT  ALLLOWED.
		 * INSERTION  ORDER IS NOT PRESERVED.
		 * NO NEW METHOD , SO WE  HAVE  TO USE   THE  COLLECTION  INTEFACE METHOD.
		 * 
		 * 
		 * HASHSET:- 
		 * BASED ON HASH  TABLE DATA STRUCTURES. 
		 * DUPLICATES ARENOT  ALLOWED. INSERTION ORDER IS NOT  PRESERVED.
		 * HETROGENIOUS OBJECT ARE ALLOWED.
		 * NULL INSERTION  IS POSSIBLE.
		 * IMPLEMENTS "SERIALIZABLE: AND "CLONEABLE" INTERFACE But NOT   "RANDOM ACESS"  INTERFACE.
		 * HASHSET  IS BEST CHOICE  IF OUR  FREQUENT  OPERATION  IS SEARCH  OPERATION.
		 * 
		 * 
		 * 
		 * 
		HashSet <Object> h =new HashSet<>();
		h.add('g');
		h.add('c');
		h.add('d');
		h.add('z');
		h.add(null);
		System.out.println(h.add('d'));
		System.out.println(h.add(2));
		System.out.println(h.add(10));
		System.out.println(h.add("e"));
		System.out.println(h); // INSETION ORDER IS NOT  PRESERVED;
		
		OUTPUT:
			false // IT IS ALREADY PRESENT SO NOTHING NEW ADDED UP.
			true
			true  // IT IS NEW ELEMENT AND  ADD UP  i.e TRUE.
			true
			[null, 2, c, d, e, g, z, 10]
			
			
			
		   LinkedHash Set:-
		   CHILD  CLASS OF HASHSET.
		   EXACTLY  SAME AS  HASHSET, ONLY DIFFERENCE ARE DATA STRUCTURES IS LINKED LIST AND  INSERTION ORDER IS PRESERVED.
		   
		   
		LinkedHashSet <Object>h= new LinkedHashSet<>();
		h.add('g');
		h.add('a');
		h.add('m');
		h.add('e');
		h.add(null);
		System.out.println(h.add('c'));
		System.out.println(2);
		System.out.println(h.add('m'));
		System.out.println(h);
		
		OUTPUT:
			true
			2
			false
			[g, a, m, e, null, c]
			
			SORTED SET:-
			CHILD INTERFACE OF SET.
			 IF WE WANT  TO REPRESENT  A GROUP OF INDIVIDUAL OBJECT ACCORDING  TO  SOME  SORTING  ORDER  AND DUPLICATES ARE NOT ALLOWED THEN  WE SHOULD GO 
			 FOR SORTED SET.  
			 DEFAULT  SORTING ORDER IS --> ASCENDING ORDER.
			 FOR STRING ----> ALPHABETICALLY.
			 
			 
			 
			 TREE SET:-
			 DATA STRUCTURES:--> BALANCED  TREE.
			 DUPLICATES  NOT ALLOWED.
			 INSETTION ORDER ---> SORTED ORDER(ASCENDING);
			 HETROGENIOUS OBJECT ARE NOT  ALLOWED.
			 ITS IS BEST INSERTION FOR  ELEMENT  GETTING SORTED EACH TIME . ITS TIME COMPLEXCITY  IS O(1).
			 
			 
			 
			 
		TreeSet <Object> t=new TreeSet<>();
		t.add('z');
		t.add('c');
		t.add('d');
		t.add('a');
		t.add('f');
		System.out.println(t);   OUTPUT:[a, c, d, f, z]
				t.add("avdcde");
		t.add("vdcdkd");
		t.add("bdkdkd");
		t.add("ccdkdkd");
		System.out.println(t); output:   [avdcde, bdkdkd, ccdkdkd, vdcdkd]
		
		t.add(null);
		System.out.println(t); null pointer exception.
		t.add("apple");
		System.out.println(t); class cast exception   
		
		QUEUE:-
		CHILD INTERFACE  OF COLLECTION.
		NULL VALUES  ARE NOT ALLOWED.
		DUPLICATES VALUES  ARE ALLOWED.
		 HETROGENIOUS ELEMENT ARE ALLOWED.
		 THE QUEUE  INTERFACE  BASICALLY  ORDERS  THE ELEMENT  IN FIFO ORDER.
		 
		 PRIORITY QUEUE(PQ):-
		 IMPLEMENTATION  CLASS OF QUEUE.
		  THE ELEMENT  OF THE PRIORITY  QUEUE  ARE ORDERED ACCORDING  TO THEIR  NATURAL  ORDERING (SORTED ORDER).
		  NULL IS NOT  ALLOWED.
		   NOT THREAD SAFE.
		    USE PRIORITyBlocking QUEUE  IS SAME AS  PRIORITY  QUEUE , EXCEPT  IT IS THREAD  SAFE.
		    
		    
		    
		  
		
		
			 
		PriorityQueue <Object>pq =new PriorityQueue<>();
		pq.add("A");
		pq.offer("B");
		pq.offer("D");
		pq.offer("C");
		System.out.println(pq);  //[A, B, D, C]
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		OUTPUT:
			A
			B
			C
			D (NATURAL ORDER)
			 
		 */

	}

}
