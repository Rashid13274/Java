package onlineStudy4u;



public class Constructor_1 {
	 int a=10, b=20;
	 void sum() { //non static
		 System.out.println(a+b);
		 int a=30;
		 System.out.println(this.a+b);
		 System.out.println(a+b);
	 }

	public static void main(String[] args) {
		Constructor_1 obj= new Constructor_1();
		obj.sum();
		//30
		//30
		//50
		
		
	/*
	 * 
	types of constructor
pararmetrized , non parametrized , copy constructor
		A constructor  that accept parameter is callled parametrized construcotr.
		ex: Test(int x, int y){
			int s=x+y;
		}
		
		non parametrized constructor:
			A construcotor  that accepts  no parameter  is called  non-parametrized.
			ex: class Test{
			int a, b;
			Test(){
				a=20;
				b=30;
				  
				NOTE: IF  A CLASS HAS NO EXPLICIT  CONSTRUCTOR DEFINE  THE COMPILER WILL SUPLY DEFAULT Constructor.  a  construcotr  is  al
				always  declared  public beacause  , private  constructor  not called  outside  the class.
				
				Copy constructor:  if a constructor  is used  to copy  the initial  value  of the  instance  variable  of an object  to the 
				instance variable  of another  object is called  copy constructor.
				
				ex: 
					class Abc{
					 int a, b;
					 Abc(int x, int y){
						 a=x;
						 b=y;
						 
						 class Xyz{
							 pulic void main(string args[]) {
								 Abc obj1=new Abc(10,29);
								 Abc obj2=obj1;
							 }
						 }
						 static varriable: it contains static keyword;
											 they can acess without creating any object;
											 it maintans  single copy;
											 it is also called  as class varriable;
						    static keyword  can be used in :
						    	1. static varriable
						    	2. static function.
						    	3. static inner class
						    	   
						    	Note: you can write a static varriable in any function.
						    	ex: fun(){
						    	int a;
						    	static int b =20; like that;
						    	
						    	
						    	
						    	where is static  used  
						    	static Bank{
						    		ac_no;
						    		bal;
						    		static int interest rate:
	acess modifier 			class 			 subclass 			 package 			 anyclass					    			
						    			
	private					yes					no 			 		no 						no
	protected				yes	 				yes 				yes						no 
	public					yes 				yes					yes						yes
						    	}
						    }
						 
					
	 */
	
		 

	}

}
