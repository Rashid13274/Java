package Data_Structures;
import java.io.*;
	// REVERSE A LINKED LIST RECURSIEVLY:-
public class LinkedList05 {
	// NODE CLASS HAVING A DATA AND REFERENCE
static class Node{
	public int data;
	public Node next;
	public  Node(int data) {
		this.data=data;
		this.next=null;
	}
}
static class LinkedList{
	public Node head; 		// 	head node;
	
		// constructor function
	public LinkedList() {
		this.head=null;
}
		// INSERTING THE DATA INTO LINKED LIST
	public  void inserNode(int new_data) {
		Node node=new Node(new_data);
		if(this.head!=null) {
			node.next=head;
		}
		this.head=node;			// else  if head  is empty  head ==node;
	}
	
}
		// FUNCTION TO PRINT  LINKED LIST
static void  printLinkedList(Node node, String sep) throws IOException {
	while(node!=null) {
		System.out.println(String.valueOf(node.data)+sep);
		node=node.next;
	}
	
}

// reverse LinkedList function recursively:-
static Node reverse (Node head) {
	if(head==null) {
		return head;
	} // last node or only one node
	else if(head.next==null) {
		return head;
	}
	Node nextNode =head.next;
	head.next=null;
	Node rest= reverse(nextNode);
	nextNode.next=head;
	return rest;
}
	public static void main(String[] args) throws IOException {
	LinkedList llist=new LinkedList();
	llist.inserNode(2);
	llist.inserNode(3);
	llist.inserNode(4);
	System.out.println("given linked list:");
	printLinkedList(llist.head," ");
	System.out.println();
	System.out.println("reversed linked list");
	Node list1=reverse(llist.head);
	printLinkedList(list1, " ");

//		scannner.close();
	}

}
