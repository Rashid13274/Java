package onlineStudy4u;

//import java.io.IOException;


public class ExceptionalHandling {

	public static void main(String[] args) {
		/*
		 * EXCEPTIONAL HANDLING:-
		 *  THESE ARE  ANY ABNORMAL , UNEXPECTED  EVENT  OR EXTRA  ORDINARY  CODITIONS THAT MAY OCCOUR   AT RUNTIME   DUE TO   ANY  ERROR
		 *   LEADING   TO SUDDEN  TERMINATION   OF THE PROGRAM;
		 *   
		 *   ERROR:-  THE PROBLEM  IN PROGRAM   DUE TO   TYPING   MISTAKES  , WRONG  USE OF  SYMBOLS  MISSING   STEPS   , ETC  ARE
		 *   KNOWN AS   ERROR  OR BUGS.
		 *   
		 *   IT IS THREE TYPES :- 
		 *   1 SYNTAX ERROR
		 *   2  LOGICAL ERROR 
		 *   3. RUNTIME ERROR
		 *    
		 *   SOME COMMON EXCEPTION:- 
		 *   
		 *   ARTHMETIC  EXCEPTION
		 *    ArrayIndexOutOfBound EXCEPTION
		 *    StringIndexOutOfBound EXCEPTION
		 *    NegativeArraySize EXCEPTION
		 *    InputMismatch  EXCEPTION
		 *    NumberFormat EXCEPTION
		 *    ClassNotFound EXCEPTION
		 *    illegalArgument EXCEPTION
		 *    
		 *    TYPE OF EXCEPTION 
		 *     EXCEPTION ARE 2  TYPES :
		 *     1.  CHECKED  (COMPILE TIME  EXCEPTION)
		 *     2.  UNCHECKED (RUN TIME  EXCEPTION)
		 */
		
		/*
		 * try {
			System.out.println(1/0);
			
		}catch(Exception e) {
			System.out.println("infinity");
			System.out.println(e.getMessage());
		}
		System.out.println("last line");
		Exception is parent class  of  all the checked and unchecked exception
		 */
		
		/*
		 * try {
			System.out.println(1/0);
			
		}catch(NullPointerException e) {
			System.out.println("infinity");
			System.out.println(e.getMessage());
		}
		finally {
			System.out.println("first line");
		}
		System.out.println("last line");
		// here catch is not able to  catch  a thrown object since it can't hold another reference type of object. only parent class can hold   that  thrown object.
		 */  

		/*
		 *   try {
			System.out.println(1/0);
			
		}catch(NullPointerException e) {
			System.out.println("infinity");
			System.out.println(e.getMessage());
		}
		 catch (ArithmeticException e1){
			 System.out.println(e1.getMessage());
			 
		 }
		finally {
			System.out.println("first line");
		}
		System.out.println("last line");
		
	//first try block creating  an object  for the arithmetic class exception and  then  second catch block able to catch    the object and message printed.
		 */
		
		
		/*
		 * // OUR THROW  AND OUR CATCH
		 * 
		 * 
		Scanner sc=new Scanner(System.in);
		int balance=5000;
		int withrow=sc.nextInt();
		try {
			
			if(balance<withrow) {
				throw new ArithmeticException("insufficient balance");
			}
			else {
				balance=balance-withrow;
				System.out.println("avaiable balance:"+balance);
				System.out.println("transction sucessful");
				
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("program continues....");
		 */
		
		
		
		
		// USE OF 'THROWs' FOR UNCHECKED EXCEPTION
		/*
		try {
			throw new IOException();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		System.out.println("after exception");
		
		
		 * 
		output:- Null(beacause we're  getting null  message)
				after exception (this is  code  after the exception)
				 
		 */
}
}
