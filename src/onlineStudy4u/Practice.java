package onlineStudy4u;
	abstract class Ab {
		 String color;

		public abstract double area();
	public	abstract String toStrings();
		
	public 	Ab ( String color) {
			this.color=color;
			System.out.println("Ab class is called");
		}
	}
public class Practice extends Ab{
	double  radius;
	
	public Practice(double radius, String color) {
		super(color);
		this.radius=radius;
		this.color=color;
		System.out.println("practice class is called");
	}
	public double area() {
		return Math.PI*Math.pow(radius, 2);
	}
	public  String toStrings() {
		return "color of area is red and araa of length is " + area();
	}
	
	public static void main(String[] args) {
	Ab  object1= new Practice(2, "red");
	System.out.println(	object1.toStrings());
	
	
	
	}
}
