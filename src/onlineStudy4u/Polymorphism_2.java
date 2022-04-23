package onlineStudy4u;
/*COMPILE TIME POLYMORPHISM: COMPILE TIME POLYMORPHISM ALSO KNOWN AS  STATIC  POLYMORPHISM , REFERS TO TYPE OF  POLYMORPHISM HAPPENS AT RUNTIME.
 * WHAT IT MEANS IS THAT COMPILER DECIDES WHAT SHAPES OR VALUE  HAS TO BE TAKEN BY ENTITY IN THE PICTURES.
 * COMPILE POLYMORPHISM IS ACHIEVED BY METHOD OVERLOADING.
 * */

public class Polymorphism_2 {
//	first method with name add
	public  int add(int x,int y) {
		return x+y;
	}
//	second  method with name add
	public  int add(int x,int y,int z) {
		return x+y+z;
	}
//	third  method with name add
	public  int add(double x,int y) {
		return (int)x+y;
	}
//	forth method with name add
	public  int add(int x,double y) {
		return  x+(int)y;
	}

	public static void main(String[] args) {
		
		Polymorphism_2 demo=new Polymorphism_2();
		// in below statement , the compiler looks at the argument types and decides  call a method.
		System.out.println(demo.add(2,3));
		System.out.println(demo.add(2,3.4));
		System.out.println(demo.add(2.4,3));
		System.out.println(demo.add(2,3,4));
	}
	// IN THE ABOVE EXAMPLE THERE ARE FOUR  VERSION OF ADD METHOD . THE FIRST ADD METHOD TAKES TWO PAREMETERS WHILE SECOND 
	// TAKES THREES PARAMETERS, AND THIRD AND FORTH AND SO ON.
	// THE COMPILER LOOKS AT THE  METHOD SIGNATURES  AND DECIDES WHICH METHOD  TO INVOKE FOR PARTICULAR  METHOD CALL.

}
