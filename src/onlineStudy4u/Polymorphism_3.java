package onlineStudy4u;
/*RUNTIME  POLYMORPHISM, ALSO KNOWN AS  DYNAMIC POLYMORPHISM , REFERS TO THE TYPE OF  POLYMORPHISM  THAT HAPPENS AT  THE RUN TIME .
 * WHAT IT IS MEANS  IT CAN'T BE  DECIDED BY COMPILER. THEREFORE WHAT  SHAPE OR VALUES HAS TO BE  TAKEN DEPENDS  UPON  THE EXECUTION
 * .HENCE  THE NAME RUNTIME.
 * RUN TIME POLYMORPHISM IS ACHIVED IN JAVA  BY MEHTOD OVERRIDDEN.
 * */

class AnyVehicle{
	public void  move() {
		System.out.println("any vehicles should move");
	}

}
public class Polymorphism_3 extends AnyVehicle {
	public void move() {
		System.out.println("bike can move too");
	}
	
	public static void main(String[] args) {
		AnyVehicle vehicle=new Polymorphism_3();
		// in the above  statement , we're taking the reference of class Anyvehicle  and  storing the object vehicle in class polymorphism_3.
	vehicle.move();
	AnyVehicle vehicle1=new AnyVehicle();
	vehicle1.move();
	
//	bike can move too
//	any vehicles should move

	}

}
