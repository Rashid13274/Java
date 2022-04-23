package ArrayList_Practice;

import java.util.LinkedList;

public class LinkedList_Practice1 {

	public static void main(String[] args) {
//		LinkedList<Object>ll=new LinkedList <>();
//		ll.add(1);
//		ll.add("MD RASHID");
//		ll.add("deodha-madhya");
//		ll.add(847226);
//		ll.addFirst("roll no");
//		ll.add(3,"ks college darbhanga");
//		ll.remove(3);
//		System.out.println(ll);
//		
		
		
	     
		// CREATING LIST OF BOOKS
	    LinkedList<Book> list=new LinkedList<Book>();  
	    //Creating Books  
	    Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8);  
	    Book b2=new Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);  
	    Book b3=new Book(103,"Operating System","Galvin","Wiley",6);  
	    //Adding Books to list  
	    list.add(b1);  
	    list.add(b2);  
	    list.add(b3);  
	    //Traversing list  
	    for(Book b:list){  
	    System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);  
	    }  
		
		
	}

}
