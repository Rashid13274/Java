package ArrayList_Practice;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedList_Practice2 {

	public static void main(String[] args) {
/*
	1)	 ArrayList internally uses a dynamic array to store the elements.	
		 LinkedList internally uses a doubly linked list to store the elements.
		 
	2) 	Manipulation with ArrayList is slow because it internally uses an array.
	 	If any element is removed from the array, all the bits are shifted in memory.
	 	Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list,
	 	 so no bit shifting is required in memory.
	 	 
	3)  An ArrayList class can act as a list only because it implements List only.	
		LinkedList class can act as a list and queue both because it implements List and Deque interfaces.
		
	4) ArrayList is better for storing and accessing data.
	LinkedList is better for manipulating data.

*/
		
		// here list is the interface through which these linked list and 
		// arraylist are  defined. so nothing new.
		 List <String>al=new ArrayList<>();//creating arraylist    
		  al.add("Ravi");//adding object in arraylist    
		  al.add("Vijay");    
		  al.add("Ravi");    
		  al.add("Ajay");    
		    
		 List <String> al2=new LinkedList<>();//creating linkedlist    
		  al2.add("James");//adding object in linkedlist    
		  al2.add("Serena");    
		  al2.add("Swati");    
		  al2.add("Junaid");    
		    
		  System.out.println("arraylist: "+al);  
		  System.out.println("linkedlist: "+al2);  
		 }   
	}


