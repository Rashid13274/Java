package Data_Structures;

public class LinkedList {
	// first node of any linked list is head so there is head;
	// all the operation and functionality for linked list  are performed in this class.
	Node head;
	// INSERT A NEW NODE AT FRONT OF LIST;
	
	public void push(int new_data) {
		Node new_node=new Node(new_data);
		new_node.next=head;
		head=new_node;
		//line 1&2 allocate the node and put in the data.
		//line 3  make next of new node  as head
		//move line 4 the head to point to the new node.
		
	}
	
	// INSERT A NEW NODE AFTER A GIVEN PREV NODE
	public void insertAfter( Node prev_node, int new_data) {
		
		//check if given node is null;
		
		if(prev_node==null) {
			System.out.println("the given previous node can't be null");
			return;
	}
		Node new_node=new Node(new_data);
		//make the next of new node as next of prev node.
		new_node.next=prev_node.next;
		// make  next of  prev node  as new node.
		prev_node.next=new_node;  
	}
	
	public void append(int new_data) {
		Node  new_node=new Node(new_data);
		// condition checking if our linked list is empty or not.
		if(head==null) {
			head=new Node(new_data);
			return;
		}
		new_node.next=null;
		// here we created a last  node for traversal. in linked list if we want to traverse we must need last node.
		// now  we want to add data or node at the end of linked list. so looping applied.
		// initially it was on head.
		
		//ADD THE NODE AT THE END OF LINKED LIST.
		
		Node last =head;
		while(last.next !=null) {
			last=last.next;
		}
		last.next=new_node;
	}
	
	
	// here we've created a node
	  class Node{
		int data;
		Node next;
		Node(int d){
			data= d;
			next = null;
		}
	}
	// here we want  to print linked list , so tnode node travese to each node until tnode get null
	//and finally at the end whole linkedlist  get printed.
	public  void printList() {
		Node tnode=head;
		while(tnode!=null) {
			System.out.println(tnode.data+" ");
			tnode=tnode.next;
		}
		
	}
//Insert node AT Nth position in Linked List:-
	public void  insertAtPosition(int  data, int position) {
		/*
		 * IF INSERT POSITION IS NEGATIVE  THEN THROW  
		 * IllegalArgumentException
		 */
		if(position<0) {
			throw new IllegalArgumentException("invalid value  of position ="+position);
		}
		Node newNode=new Node(data);
		if(position==0) {
			newNode.next=head;
			head=newNode;
			return;
		}
		// ELSE IF POSITION>0;
		
		Node temp=head;
		// Traversing the temp to n-1 position ;
		for(int i=0;i<position-1;i++) {
			temp=temp.next;
			/*
			 * IF NODE INSERTION IS POSITION IS GREATER  THEN NUMBER OF NODES THEN THROW  IllegalArgumentException
			 */
			if(temp==null) {
				throw new IllegalArgumentException("value of positon="+position+"is greater then number of nodes in the list");
			}
		}
		
		newNode.next=temp.next;
		temp.next=newNode; 
	}
	
	
	// GET THE SIZE OF  LINKED LIST
	int counter=0;
	public void size() {
		Node temp=head;
		while(temp!=null) {
			counter++;
			temp=temp.next;
		}
		System.out.println("size of linked list is"+" "+counter);
	}
	
	// SEARCH THE INTENDED NODE FROM THE LINKED LIST;
	
	public boolean searchNode(int data) {
		Node temp=head;
		if(temp==null) {
			System.out.println("stack is empty");
		}
		while(temp!=null) {
			if(temp.data==data)
				return true;
			temp=temp.next;
		}
		return false;
	}

	public static void main(String[] args) {
//		int position =2;
		
		LinkedList llist =new LinkedList();
		
		llist.push(1);
		llist.push(10);
		llist.push(49);
		llist.push(05);
		
		llist.push(17);
		llist.push(150);
		llist.push(4);
		llist.push(50);
		llist.push(51);
		boolean a=llist.searchNode(1);
		System.out.println(a);
		
		// llist.insertAfter(llist.head.next.next, 99);
//		llist.insertAtPosition(1000, 2);
//		llist.size();
//		llist.printList();
		
	}
 
}
