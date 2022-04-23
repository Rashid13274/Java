package Data_Structures;

//import Data_Structures.LinkedList.Node;

public class DeletionLinkedList {
	Node head;
	
	public void append(int new_data) {
		Node  new_node=new Node(new_data);
		// condition checking if our linked list is empty or not.
		if(head==null) {
			head=new Node(new_data);
			return;
		}
		new_node.next=null;
		//ADD THE NODE AT THE END OF LINKED LIST.
		
				Node last =head;
				while(last.next !=null) {
					last=last.next;
				}
				last.next=new_node;
			}
	public void remove() {
		if(head==null) {
			return;
		}
		Node temp=head;
		Node previousToLastNode=null;
		while(temp.next!=null) {
			previousToLastNode=temp;
			temp=temp.next;
			
		}
		previousToLastNode.next=null;
	}
	
	public  void printList() {
		Node tnode=head;
		while(tnode!=null) {
			System.out.println(tnode.data+" ");
			tnode=tnode.next;
		}
		
	}
//	given a key  delates the first occurance of  key in linked list.
	
	/*void deleteNode(int key) {
		// STORE HEAD NODE
		Node temp=head; 
		Node prev =null;
		
		// IF HEAD NODE ITSELF HOLDS THE KEY TO BE DELETED. THEN--
		if(temp!=null && temp.data==key) {
			head= head.next; // change the head;
			return;
			
		}
		//SEARCH FOR  THE KEY TO BE DELETED. KEEP TRACK OF PREVIOUS NODE
		// AS WE NEED TO CHANGE  TEMP.NEXT
		while(temp!=null && temp.data!=key) {
			prev =temp;
			temp=temp.next;
		}
		// IF THE KEY WAS NOT PRESENT
		if(temp==null)
			return;
		// UNLINK THE NODE FROM THE LINKED LIST
		
	}*/
	

	class Node{
		int data ;
		Node next;
		Node(int d) {
			data=d;
			next=null;
			
		}
	}

	public static void main(String[] args) {
		DeletionLinkedList list1=new DeletionLinkedList();
		list1.append(11);
		list1.append(12);
		list1.append(13);
		list1.append(14);
//		list1.printList();
//		list1.deleteNode(13);
		list1.remove();
		list1.printList();



		
			

	}

}
