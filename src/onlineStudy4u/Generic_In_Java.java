package onlineStudy4u;
// A SIMPLE  JAVA PROGRAM  TO  SHOW WORKING OF USER DEFINED GENERIC CLASS.
class Test1 <T>{
	// WE USE <>  TO SPECIFY PARAMETER TYPES.
	//AN  OBJECT  OF TYPE T IS DECLARED.
	T obj;
	Test1(T obj){
		this.obj=obj;  //  CONSTRUCTOR
	}
	public T getObject() {
		return this.obj;
	}
}

public class Generic_In_Java {

	public static void main(String[] args) {
		/*
		 * GENERIC IN JAVA:-
		 * GENERIC MEANS  PARAMETERIZED  TYPES. THE IDEA  IS TO  ALLOW TYPE(INTEGER, STRING, ... ETC). AND USER DEFINED TYPES TO BE  PARAMETER TO METHODS, CLASSES,
		 * AND INTERFACE. USING  GENERIC  , IT IS POSSIBLE  TO CREATE  CLASSES THAT WORKS  WITH  DIFFERENT DATA TYPES.
		 * AN ENTITY  SUCH AS CLASS, INTERFACE,OR METHOD  THAT OPERATES  ON A PARAMETERIZED TYPES IS CALLED A GENERIC ENTITY.
		 * 
		 * WHY GENERIC?
		 * THAT  TYPES SAFETY .
		 */
		
		
		// Driver Class To Test11 Above
		// INSTANCE OF  INTEGER TYPES
		Test1 <Integer> iObj=new Test1 <Integer>(18);
		System.out.println(iObj.getObject());
		Test1 <String> iObj1=new Test1<String>("hello world");
		System.out.println(iObj1.getObject());

	}

}
