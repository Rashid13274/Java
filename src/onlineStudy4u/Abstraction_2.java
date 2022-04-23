package onlineStudy4u;

public class Abstraction_2 {

	public static void main(String[] args) {
		/*
		 * ABSTRACT METHOD:-
		 * AN Abstract class can include  method  that certain  no implemention. these  method  are called  abstract  methods.
		 * 
		 * if a class  has any  abstract method  , wheather  declared  or inherted ,the  entire  class  must be  declard  abstract.
		 * 
		 * abstract method can only be implemented in abstract class.
		 * ex:-
		 * 		 abstract int num(int a ,int b)   that has no body.
		 * 
		 * A method  defined  abstract  must  always  be redefined  in the  subclass, thus  making  overriding  compulsory OR
		 * either make subclass  itself abstract. 
		 * ex:  correct the code:
		 * 
		 * class person {
		 * abstract void show(){
		 * }
		 * }
		 *  class Student extends  Person{
		 *  ....
		 *  }
		 *  class AbstrctExample{
		 *  public static void  main(string args[]){
		 *   Student s =new Student();
		 *  }
		 *  
		 *  
		 *  CORRECTED VERSION:- 1
		 *  
		 * abstract class person {
		 * abstract void show(){
		 * }
		 * }
		 *  abstract class Student extends  Person{
		 *  ....
		 *  }
		 *  class AbstrctExample{
		 *  public static void  main(string args[]){
		 *   Student s =new Student();
		 *  }
		 *  
		 *  CORRECTED VERSION:-2
		 *  
		 *  Eiher show fn must have body:
		 *  
		 *  abstract class person {
		 * abstract void show (){
		 * }
		 * }
		 *  class Student extends  Person{
		 *  show(){
		 *   some code....
		 *  }
		 *  }
		 *  class AbstrctExample{
		 *  public static void  main(string args[]){
		 *   Student s =new Student();
		 *   s.show();
		 *  }
		 *  
		 *  
		 * An abstract class  can have  parametrized  constructor  and default  constructor  is  always  present  in an abstract class.
		 * 
		 */

	}

}
