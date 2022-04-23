package Data_Structures;

//import Data_Structures.LinkedList03.Node;

//import Data_Structures.LinkedList03.Node;

public class LinkedList04 {
Node head; 


//GET THE MIDDLE NODE OF LINKED LIST;
public void middleNode() {
	Node temp=head;
	if(temp==null) {
		return ;
	}
	//  WE WILL ALWAYS TRAVERSING THE NODE  USING NODE NOT THE INTEGER;
	Node temp1=head;
	Node temp2=head.next;
	while(temp2!=null && temp2.next!=null) {
		temp1=temp1.next;
		temp2=temp2.next.next;
	}
	System.out.println(temp1.data);
}
	
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
	
	// display the  list;
		 public void print() {
			 Node tnode =head;
			 while(tnode!=null) {
				 System.out.println(tnode.data);
				 tnode=tnode.next;
				 
			 }
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
			public int  reverseLinkedlist() {
				Node  temp=head;
				if(temp==null|| temp.next==null) {
					return 0;
				}
				Node prev, save;
				prev=save=null;
				while(temp!=null) {
					save=temp.next;
					temp.next=prev;
//					now slide the  prev, node by one
					prev=temp;
					temp=save;
				}
			
				return temp.data;
			
			}
			
			
	public static void main(String[] args) {
		LinkedList04 l=new LinkedList04();
		l.addAtFront(1);
		l.addAtFront(12);
		l.addAtFront(13);
//		l.addAtFront(14);
//		l.addAtFront(15);
//		l.addAtFront(12);
//		l.addAtFront(13);
//		l.addAtFront(14);
//		l.addAtFront(15);
//		l.middleNode();
//		System.out.println(l.reverse() ;
		l.reverseLinkedlist();
		l.print();
		

	}

}
