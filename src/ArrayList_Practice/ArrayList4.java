package ArrayList_Practice;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayList4 {

	public static void main(String[] args) {
	// REOMOVE THE ELEMENT IN THE ARRAY LIST:-
		ArrayList<Integer> val=new ArrayList<>();
		val.add(1);
		val.add(2);
		val.add(3);
		val.add(4);
//		val.remove(3);
//		System.out.println(val);
		
		ArrayList<Integer> val1=new ArrayList<>();
		val1.add(6);
		val1.add(7);
		val1.add(2);
		val1.add(3);
		val1.add(8);
		val1.add(9);
//		val.addAll(val1);
//		System.out.println(val);
//		val.removeAll(val1);
//		System.out.println(val);
		
		// REMOVE ALL THE ELEMENT IN THE ARRAYLIST:-
//		val.clear();
//		System.out.println(val);
		
		
//		 RETAIN method IN ARRAYLIST
		val.retainAll(val1);
		Iterator<Integer> itr=val.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		
		// RETAINALL MEANS I.E COMMON ELEMENT IN BOTH THE ARRAY LIST;
		
//		 ArrayList<String> al=new ArrayList<String>();  
//		  al.add("Ravi");  
//		  al.add("Vijay");  
//		  al.add("Ajay");  
//		  ArrayList<String> al2=new ArrayList<String>();  
//		  al2.add("Ravi");  
//		  al2.add("Hanumat");  
//		  al.retainAll(al2);  
//		  System.out.println("iterating the elements after retaining the elements of al2");  
//		  Iterator<String> itr1=al.iterator();  
//		  while(itr1.hasNext()){  
//		   System.out.println(itr1.next());  
//		  }  
		
		
		
		
		
		
		
		
		
		
		
		
		
      
	 
		
		

	}

}
