package onlineStudy4u;

 class Example{
	
		private String student_name;
		private int student_age;
		private int student_roll;
		
		
		public String getStudentName() {
			return student_name;
		}
		public void setStudent_name(String studentName) {
			this.student_name=studentName;
		}
		public int getStudent_Roll() {
			return student_roll;
		}
		public void setStudent_Roll(int roll) {
			this.student_roll=roll;
		}
		public int  getStudent_age() {
			return student_age;
		}
		public void setStudent_age(int age) {
			this.student_age=age;
		}
}
	
	
 public class Encapsulation {

	public static void main(String[] args) {
		Example student_1=new Example();
		student_1.getStudent_age();
//		student_1.setStudent_name   it can not be acessed since we're using the private keyword, so we can only access function.
		student_1.setStudent_age(21);
		student_1.setStudent_name("md rashid");
		student_1.setStudent_Roll(111);
		
		System.out.println("this is student_1 name, roll age"+ student_1.getStudentName() + student_1.getStudent_Roll()+student_1.getStudent_age());
		
		
		
		
		
		
		
/*
 * Encapsulation is defined  as the  wrapping  up of data under a single  unit . it is  the  mechanism  that binds  together  code 
   Other way  to think  about  encapsulation  is,  it is protective  shield  that prevents  the data  being  acessed by the code  outs
   outside the shield.
		
   Technically  in encapsulation  , the variable  or data  of a class  is hidden  from  any  other class  and can be  accessed  only 
	through  any member function  of own class in which  they are declared.
		
	as in encapsulation , the  data  in a class  is hidden  from  other  classes  using  the data  hiding concept  which  is achieved
	by making  the members  or method  of class  as private . 
		
		
	And the class is exposed  to the  end user  or the world  without  providing  any  details  behind  implemented  using  the abstraction
	concept  , so it is  also known a s combination  of data hiding  and abstraction. 
		
	Encapsulation: it can be achieved  by  declaring  all the  variable  in class  as  private  and writing  public  method  in the class
	to set  and get  the values  of variables.
		 */
		
	/*
	 * ADVANTAGES OF ENCAPSULATION:--
	 * DATA HIDING:-  THE user have no idea  about  the inner implementation of the class. it will  not be  visible  to the user  that how
	 * the class  is storing  in the  variable . he only knows  that we are passing   the value  to setter   method  and  variable
	 *  are getting initialized  with that values.
	 *  
	 *  INCREASE FLEXIBILITY:--  We can make   the variable   of the class  as read  only or write  only  depending on our  requirement .if we wish 
	 *  to make  the variable as read only  then  we have to omit   the setter  method like setName(), setAge(); etc  from only   the 
	 *  above code or if we wish   to make  the variable  as write  only  then  we have  to omit   the get  method  like getName()
	 *  getValue(); from above program. 
	 *  
	 *  Reusability:-  Encapsulation  also improves  the reusability  and  easy  to change with  new requirement. 
	 *  
	 * Testing  code is easy:-  encapsulation  code  is easy  to test   for unit  testing. 
	 */

	}

}
