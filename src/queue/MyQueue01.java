package queue;
//import queue.MyQueue;
public class MyQueue01 {

	public static void main(String[] args) {
	
		MyQueue<Integer> q= new MyQueue<Integer>();
		q.enQueue(10);
		q.enQueue(10);
		q.enQueue(10);
		q.enQueue(10);
		System.out.println(q);
		System.out.println(q.size());
		
	}

}
