 package Data_Structures;

public class LinkedList03 {
	Node head; // A  head node;
	
	// INSERT THE NODE AT FRONT 
	public void addAtFront(int new_data) {
		Node newNode=new Node(new_data);
		if(head==null) {
			head=newNode;
	}else {
		newNode.next=head;
		head=newNode;
	}
	}
	
	// INSERT THE NODE AT END 
	public void  AddNodeAtLast(int new_data) {
		Node newNode=new Node(new_data);
		if(head==null) {
			head=newNode;
		}else {
			Node temp=head;
			while(temp.next!=null) {
				temp=temp.next;
			}
				temp.next=newNode;
		}
	}
	
	
	//GET THE FIRST NODE OF LINKED LIST
	public void firstNode() {
		if(head==null) {
			System.out.println("linked list is empty");
		}else {
			
			Node temp=head; // temp NODE IS CREATED FOR TRAVERSING THE NODE TO GET THE EXACT NODE;
			System.out.println(temp.data+"First Node or first data");
		}
	}
	// display the  list;
	 public void print() {
		 Node tnode =head;
		 while(tnode!=null) {
			 System.out.println(tnode.data);
			 tnode=tnode.next;
			 
		 }
	 }
	 // Get the Last Node of Linked List
	 public void getLastNode() {
		 Node temp =head;
		 while(temp.next!=null) {
			 temp=temp.next;
		 }
		 System.out.println(temp.data+" "+"this is our last node");
	 }
	// A Node and a pointer  that will point to next pointer.
		class Node{
			int data;
			Node next;
			public Node(int data) {
				this.data=data;
				next=null;
			}
			
		}

	public static void main(String[] args) {
		LinkedList03 l=new LinkedList03();
//		l.addAtFront(1);
//		l.addAtFront(2);
//		l.addAtFront(3);
//		l.addAtFront(11);
//		l.addAtFront(222);
//		l.addAtFront(31);
		l.AddNodeAtLast(1);
		l.AddNodeAtLast(2);
		l.AddNodeAtLast(3);
//		
//		l.print();
//		l.firstNode();
		l.getLastNode();

	}

}
