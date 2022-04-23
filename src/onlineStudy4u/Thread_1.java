package onlineStudy4u;
  

/*
 * STEPS:
 * CREATING A THREAD
 * ATTACHING THE CODE THE THREAD
 * EXECUTION OF THREAD
 */
class A implements Runnable{
	public void run() {
		for (int i = 0; i <5; i++) {
			System.out.println("thread a" +i);
			
		}
	}
}
class B implements Runnable{
	public void run() {
		for(int i =0; i<5;i++) {
			System.out.println("thread B "+ i);
		}
	}
}
class C implements Runnable{
	public void run() {
		for(int i =0; i<5;i++) {
			System.out.println("thread C "+i);
		}
	}
}

public class Thread_1 {
	

	public static void main(String[] args) {
		Thread ob1=new Thread(new A());
		Thread ob2= new Thread(new B());
		Thread ob3=new Thread(new C());
		ob1.start();
		ob2.start();
		ob3.start();

	}

}

// here we've created three thread.
//Now we've to attach the code to thread class  passing the object  to parameterized constructor;
// now execute the thread  to do this  we have call  a function present in thread class. i.e start(); 
// since start() is instance funcion  so we can call it by ob1.start();


