package ArrayList_Practice;

import java.util.ArrayList;
//import java.util.Collections;
//import java.util.ListIterator;
//import java.util.Iterator;

public class ArrayList1 {

	public static void main(String[] args) {
		ArrayList<String> list=new ArrayList<>();
//		num.add(1);
//		num.add(2);
//		num.add(3);
//		num.add(4);
//		num.add(5);
//		num.add(6);
//		System.out.println(num);
//		Iterator<Integer> itr=num.iterator();
//		while(itr.hasNext()) {
//			System.out.println(itr.next());
//		}
//		
		// ITRETING THROUGH FOREACH LOOP:-
		
//		list.add("zapple");
//		list.add("yball");
//		list.add("xcat");
//		list.add("fog");
//		Collections.sort(list);
//		
//		for(String alphabete : list){
//			System.out.println(alphabete);
//			
//		}
		
		
		
		// GET AND SET METHOD IN ARRAYlIST:-
		
//		list.add("zapple");
//		list.add("yball");
//		list.add("xcat");
//		list.add("fog");
//		list.add("rose");
//		list.add("lily");
//		list.add("sunFlower");
//		list.set(2, "lotus");
//		System.out.println(list.get(2));
		
		
//		Ways to iterate the elements of the collection in Java
//		There are various ways to traverse the collection elements:
//
//		By Iterator interface.
//		By for-each loop.
//		By ListIterator interface.
//		By for loop.
//		By forEach() method.
//		By forEachRemaining() method.
		
			
		
		// ITERTING USING  ListIterator Interface
//		
//		list.add("naman");
//		list.add("suman");
//		list.add("raman");
//		list.add("chaman");
//		list.add("aman");
//		ListIterator<String> list1=list.listIterator(list.size());
//		System.out.println("traversing the list1 using ListIterator  interface");
//		
//		 while(list1.hasPrevious())  
//         {  
//             String str=list1.previous();  
//             System.out.println(str);  
//               here list will iterate reversively
//         }  
		 
		 // ITERATING USING FOR LOOP
		 
		 
//		 System.out.println("Travsing the list through the for loop");
//		 for(int i=0;i<list.size();i++) {
//			 System.out.println(list.get(i));
//		 }
		
		
		// ITERATING USING FOR EACH (LAMBDA EXPRESSION):-
		
		System.out.println("iterating  the list  using forEach  ");
		list.add("naman");
		list.add("suman");
		list.add("raman");
		list.add("chaman");
		list.add("aman");
		list.forEach(a->{
			
			System.out.println(a);
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
