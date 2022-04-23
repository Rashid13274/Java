package onlineStudy4u;
interface Person{
	void display();
}
class Student implements  Person{
	 public void display() {
		System.out.println("this is  the dispaly method of the student  class");

}
class Lectureer implements Person{
	public void display() {
		System.out.println("this is the display  method of the lectures class");
	}
}
public static class Interface {
	

	public static void main(String[] args) {
		Person obj1=new Student();
		obj1.display();
		
	}
		/* Interface vs abstract  class
		 * 
		 * Abstract class  can have  any acess  specifier of members
		 * Interface  can have  only   public  member. we dom't use  public keyword. it is by default;
		 * 
		 * Abstract  class may  or may not  contain  abstract method;
		 * Inteface  can not  have  function   defination. we dont  use abstract  keyword. it is by default.
		 * 
		 *  Abstract  class  can have  static  or  non  static   members ;
		 *  InterFace  can have  only static  member variable. it is by default in interface.
		 * 
		 *ex:- 
		 *
		 *Interface Calculation{
		 * double  pI=3.14;
		 * int add(int a, int b);
		 * int  sub(int a , int b);
		 * }
		 * 
		 * 
		 * Abstract class Person{
		 * private  String name;
		 * public  void  setName(String n){
		 * name=n;
		 *  
		 *  public String getName(){
		 *  return name;
		 * 
		 *}
		 *
		 *ENCAPULATION VS  DATA ABSTRACTION
		 * Encapsulation  is data hiding ( information hiding) while  Abstraction is   details hiding  (implentation hiding);
		 * 
		 * while  encapulation groups  together  data  and method   that acts   upon  the data , data  abstraction
		 *   deals  with exposing   the interface  to the  users  and hiding  the detaols  of implemetntion;
		 *   
		 *   ADVANTAGES OF DATA ABSTRACTION:-
		 *   
		 *   IT Reduces  the complexcity  of viewing the thing ;
		 *    
		 *   Avoid  code  duplication  and increases  resuability
		 *    
		 *   helps  to increase  secutity  of an  application  or program  as only  impotant  details are provided  to the users;
		 *
		 * 
		 * 
		 */

	}

}
