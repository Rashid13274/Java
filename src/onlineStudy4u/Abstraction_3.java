package onlineStudy4u;
abstract class Shape{
	String color;
	abstract double area();
	public  abstract  String toString();
	public Shape(String color) {
		System.out.println("shape constructor called");
		this.color=color;
	}
	public  String getColor() {
		return color;
	}
}
class Circle extends Shape{
	double radius;
	public  Circle(String color,double radius) {
		super(color);
		System.out.println("circle constructor called");
		this.radius=radius;
	}
	double area() {
		return Math.PI*Math.pow(radius, 2);
	}
	public String toString() {
		return "circle color is "+super.color+" and  area is "+area();
	}
}
class Rectangle extends Shape{
	double length;
	double width;
	public Rectangle(String color, double length, double width) {
		super(color);
		System.out.println("rectangle constructor called");
		this.length=length;
		this.width=width;
	}
	double area() {
		return length*width;
		
	}
	public String  toString() {
		return "rectangle  color is" + super.color+ "and the area is"+area();
	}
}
public class Abstraction_3 {

	public static void main(String[] args) {
	Shape s1= new Circle("red",2.2);
	Shape s2=new Rectangle( "yellow", 2, 4);
	 System.out.println(s1.toString());
	 System.out.println(s2.toString());
	}

}
