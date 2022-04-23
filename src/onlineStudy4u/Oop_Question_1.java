  package onlineStudy4u;
  class Test{
	  int a,  b;
	  Test(int x, int y){
		  a=x;
		  b=x;
		  
	  }
	  void  swap() {
		  int temp=a;
		  a=b;
		  b=temp;
//		  System.out.println("a ="+a+"b is"+b);
	  }
  }
public class Oop_Question_1 {
public static void main(String[] args) {
		Test t=new Test(20,10);
		t.swap();
		System.out.println("a =" +t.a+ "b =" +t.b);
		
		/*
		 * class Test{
	  int a,  b;
	  Test(int x, int y){
		  a=x;
		  b=x;
		  
	  }
	  void  swap() {
		  int temp=a;
		  a=b;
		  b=temp;
//		  System.out.println("a ="+a+"b is"+b);
 * 		Test t=new Test(20,10);
		t.swap();
		System.out.println("a =" +t.a+ "b =" +t.b);
		
	  }
  }
		 */
	

		/*
		 * class A{
		 * 
			public A() {
				System.out.println("lv");
		}
			public A(String s) {
				System.out.println(s);
				
		}
			public A(char c) {
				System.out.println(c);
		}
		}
			public static void main(String[] args) {
			A a =new A('I');
			A b=new A();
			A c=new A("india");
			
			
		 // classical example of   methodoverloading.
		  output:- I
		  		   lv 
		  		  india
		 */
		
	
	
	

	}

}
