package onlineStudy4u;
// method overriding:-
class Parent{
	void print() {
		System.out.println("parent class");
	}
	}
class subclass1 extends Parent{
	void print() {
		System.out.println("subclass1");
	}
}
class subclass2 extends subclass1{
	void print() {
		System.out.println("subclass2");
		
	}
}

public class Polymorphism_1 {

	public static void main(String[] args) {
		Parent obj1=new subclass1(); // we are creating an  object using reference of parent class and storing  the obj1 to subclass1;
		obj1.print();
		obj1=new subclass2();
		obj1.print();
		obj1=new Parent();
		obj1.print();
		
		//subclass1
		//subclass2
		//parent class
		
		
		
		
		
		/*
		 * POLYMORPHISM: THE WORD POLY MEANS MANY AND MORPH MEANS FORM SO MANYFORMS.
		 * in JAVA  Polymorphism  is mainly  divided into  two types :
		 * 1. compile time polymorphism
		 * 2.rum time polymorphism / dynamic runtime;
		 * 
		 * COMPILE TIME POLYMORPHISM: 
		 * It is also known as static polymorphism. 
		 * this type  of polymorphism  is achieved  by  function overloading or OPERATOR OVERLOADING.
		 * ex:
		 * fun(int a);
		 * fun(int a, int b);
		 * fun(String a);
		 * fun(char name);
		 * 
		 * FUNCTION OVERLOADING:- By changing the no of parameter;
		 * ex:
		 * fun(int a);
		 * fun(int a, int b)
		 * fun(int a, int b,int c);
		 * 
		 * 
		 * OPERATOR Overloading:-- 
		 * java  also provides  option  to overload  operator . for example  , we can  make  the operator('+') for string class  to
		 * concatenate two strings . we  know that   this is  addition  operatoer  whose task  is to add  two operand .so a single  op
		 *operator  +  when placed  between integer  operand , adds  them  and when  placed  between  string  operands, concatenate.
		 *
		 *
		 *
		 *2. RUNTIME POLYMORPHISM:-
		 *It is also known as  DYNAMIC METHOD DISPATCH.
		 *IT is a process  in which  a function  call  to the  overridden  method is resolved  at runtime. 
		 *this type  of polymorphism is  achieved by method  overriding.
		 *
		 *Method overriding:
		 *it occurs when a  derived  class  has a definition  for one  of the member function  of the bases class  . that  base 
		 *function is said to be  overridden. 
		 *
		 */
// Method overriding:-
		
	}

}
