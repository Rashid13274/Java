package onlineStudy4u;
// A simple  java program  to show multiple  type parameters in java  GENERIC .
// WE USE  <>  TO  SPECIFY  PARAMETERS TYPES.
class Test2 <T, U>{
	T obj1;
	U obj12;
	// constructor
	Test2(T obj1, U obj12){
		this.obj1=obj1;
		this.obj12=obj12;
	}
	// TO print obj1ect of T and U;
	public void print() {
		System.out.println(obj1);
		System.out.println(obj12);
	}
}
// driver class  to test  above
public class Generic_In_Java_01 {

	public static void main(String[] args) {
		Test2 <String,Integer> obj=new Test2<String, Integer>("QFQ",12);
		obj.print();
		

	}
	

}
	/*
	 * ADVANTAGE OF GENERIC:
	 * PROGRAM THAT USES  GENERIC  HAS GOT  MANY  BENIFITS  OVER NON  GENENIC  CODE.
	 * 1. CODE REUSE:
	 * 
	 * WE CAN  WRITE  A METHOD/ CLASS/ INTERFACE ONCE  AND USE  IT FOR ANY TYPE  WE WANT.
	 *  
	 *  2. TYPE SAFETY:
	 *  GENERIC MAKES  ERROR  TO APPEAR  COMPILE  TIME  THAN AT  RUN   TIME (IT IS ALWAYS BETTER  TO KNOW  PROBLEM IN YOUR CODE AT  A COMPILE  TIME RATHER THAN 
	 *  MAKING YOUR CODE   FAIL AT  RUN TIME. SUPPOSE  YOU WANT  TO  CREATE  AN ARRAYLIST THAT  STORES NAME OF THE STUDENNT  AND IF  BY MISTAKES  PROGRAMMER ADDS 
	 *  AN INTEGER INSTEAD OF STRING, THE COMPILER  ALLOWS IT .BUT  , WHEN  WE RETRIVE  THIS  DATA FROM ARRAYLIST , IT  CAUSE PROBLEM  AT RUNTIME.
	 *  SO  AT THE TIME  OF DEFINING  ARRAYLIST, WE CAN SPECIFY  THAT THIS  LIST  CAN TAKE ONLY STRING  OBJECT.
	 *  
	 *   like this   ArrayList<String> obj= new ArrayList<>(); 
	 */

