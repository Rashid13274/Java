package onlineStudy4u;



public class FindTheOutput {
	public static void main(String[] args) {
	/*1.

		String s1="apple";
		String s2="apple";
		System.out.println("s1==s2 is"+ s1==s2);
		  string is getting concatanating with s1 and then it compare it to s2; output: false */
		
		
	/*	System.out.println('j'+'a'+'v'+'a');  ascii code added output:418*/
		
		/* 3.  int $_=5;
		System.out.println($_);   no error ; output  5*/
		
	/*4.	int x=1,y=2,z=3;
		x=y=z=5;
		x-=y*=z%=2;
		System.out.println(x+" " +y+" "+z); here arthmetic operation perform based on order  of operator: output: 0 5 1*/
		
	/*5. int x=5;
		x=x++ * 2 +3 * --x;
		System.out.println(x);  here increment and decrement have higher order than '*' '/' output :25*/
		
		/*6
		  int a=0,b=30,c=40;
		a= --a + c++ +b;
		System.out.println(a) output: 69;		
		
		 */
	/*6.
	 * 	int a=5,b=10;
		a=++a *a++%b--;
		System.out.println(a);
	 
	 */
		/*7.
		 
		 int n=90;
		short x=10;
		char ch='z';
		n=x+ch+n;
		System.out.println(n);
	 here ascii code is added to int n; output:222 something;
		 */
		
		/*8
		 * System.out.println(1000+200>=1000?(500<300?"yes":(20>10?true:false)):"no"); output: true
		 */
		
		/*9.
		System.out.println("ABC\r\r PQ");
		System.out.println("ABC\r\b PQ");
		System.out.println("ABC\t\n PQ");
		
		
		 * here \r replace first two letter of ABC with PQ and print PQC
		 */
		
		/*
		
		System.out.println(20&2);
		System.out.println('a'|'c');
		System.out.println(15>>3);
		System.out.println(~20);
		
		in first binary and operation is performed
		in second binary or operation is performed  then converted into decimal;
		in third three position is shifted  
		in forth is  not operator.
		
		
		 */
		
//		 TO CONVERT  TEMPERATURE  FROM FORENHEIT TO CELCIUS[C=5/9(F-32)].
		/*
		Scanner sc=new Scanner(System.in);
		System.out.println("enter tempreture in forenheit");
		double F=sc.nextDouble();
		double C=5.0/9*(F-32);
		System.out.println("tempreture  in forenheit"+F);
		System.out.println("tempreture in celceus"+C);
				
		
		 */
	
//		to find out the biggest  number  out of three  number using ternary operator
		
		/*
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the  numbers");
		int a =sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		int d=((a>b && a>c)?a:(b>c)?b:c);
		System.out.println("biggest number is " +d );
		
		 */
		
//		Take the input  from the users cp and  sp  , and calculate  the profit/loss %
		/*
		 
		Scanner sc=new Scanner (System.in);
		System.out.println("enter the cost price");
		float cp=sc.nextFloat();
		System.out.println("enter the  selling price");
		float sp=sc.nextFloat();
		double res=(cp>sp)?((cp-sp)/cp*100.0):((sp-cp)/cp*100.0);
		System.out.println(res);
		
		 */
		
//		find the output of quaderatic equation
		/*
		 
		 System.out.println("ENTER THE INPUT A, B, C OF QUADERATIC EQUATION");
		 Scanner sc=new Scanner(System.in);
		 int a=sc.nextInt();
		 int b=sc.nextInt();
		 int c=sc.nextInt();
		 double D= Math.sqrt((b*b)-(4*a*c));
		 double r1=-(-b+D)/2*a;
		 double r2=(-b+D)/2*a;
		  System.out.println(" the output of quaderatic equacation " +r1 +" "+r2);
		 
				 
		 
		 
		 */
		
		
		
		
		
		
		
		
		
		
		

		 
		
	}

}
