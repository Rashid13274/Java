package onlineStudy4u;
// USING EXTENDING THE THREAD CLASS;
 class AA extends Thread{
	 public void run() {
		 for(int i=0;i<=10;i++) {
			 System.out.println("thread AA" + i);
		 }
	 }
 }

 class BB extends Thread{
	 public void run() {
		 for(int i=0;i<=10;i++) {
			 System.out.println("thread BB" + i);
		 }
	 }
 }

public class Thread2 {

	public static void main(String[] args) {
		AA obj1 =new AA();
		BB obj2=new BB();
		obj1.start();
		obj2.start();
	}

}
/*
 *  since AA is extending thread class . so willnot  Thread class have object sure it'll has. So  AA is extending the Thread class so 
 *  it has also start function. not like the runnable interface which has not  start function.
 *  this is how code will  created , attached and executed by calling start() function.
 *  earlier 
 */


