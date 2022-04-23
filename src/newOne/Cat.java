package newOne;

public class Cat extends Dog{
	public int food;
//	protected food;
//	protected means only you can use this varriable in this package

	public Cat(String name, int age ,int food) {
		super(name, age);
		this.food=food;
		
	}
	public Cat(String name, int age ) {
		super(name, age);
		this.food=50;
		
		
	}
	public Cat(String name ) {
		super(name, 0);
		
		
	}
	public void speak() {
		System.out.println("meow" + this.name+" "+this.age+"we eat"+this.food);
	}

}
