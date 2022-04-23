package newOne;
public class Dog_1 {
	public String name;
	public int age;
	protected static int count =0; // static is class based varriable;
public Dog_1(String name, int age) {
	this.name=name;
	this.age=age;
//	Dog_1.count+=1; // here we're counting no of dog instance we've created;
//	System.out.println(Dog_1.count);
	Dog_1.display();
	
}
 public static void display(){
	 System.out.println("welcome in static world");
	
}
// Dog_1.display();

}
