package newOne;

public class Dog {
	public String name;
	public int age;
	
	
public Dog(String name, int age) {

	this.name=name;
	this.age =age;
	age2();
//	speak();

	}
public void speak() {
	System.out.println("i'm" +" "+ this.name+" and i am" + " "+ this.age + "years old");
	age2();
}
public int getAge() {
	 return this.age;
}
public void setAge(int age) {
	this.age=age;
}
private int age2() {
	return this.age +2;
}
}
