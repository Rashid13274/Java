package newOne;
/* inner class : a class inside a class.
an inner class can have  member class and static member class.
AN Inner class  can:
-Acesss private member of the containing class.
-have a private access modifires.

*/
public class Outer_and_Inner_class {
//	MEMBER CLASS
	private int x;
	public Outer_and_Inner_class(int x) {
		this.x=x;
	}
	public int get() {
		return x;
	}
	public int setX(int x) {
		 return this.x=x;
	}

}
