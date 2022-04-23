package onlineStudy4u;
class One{
	public void a () {
		System.out.println("we");
	} 
}
class Two extends One{
	public void b() {
		System.out.println("lll");
	}
	
}
class three extends Two{
	public void  c() {
		System.out.println("one o one");
		
	}
}

public class Multi_level_Inheritance {
	public static void main(String args[]) {
		three g=new three();
		g.a();
		g.b();
		g.a();
	}

}

//same thing can be achieved using constructor function.
// checkout various type of  inheritance like hybrid , hiercharical and so on.
