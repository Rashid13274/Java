package map;

import java.util.HashSet;
import java.util.Set;

public class Map_Collection1 {

	public static void main(String[] args) {
		 	Pen pen1=new Pen(10,"blue");
		 	Pen pen2=new Pen(10,"blue");
//		 	System.out.println(pen1==pen2);
		 	System.out.println(pen1.equals(pen2)); //  generally "false" since  both the object are stored  in diffent location of memory.
		 										   //due to  default implementaion if it is not poining the same refernce then it is other thing. 
		 	
		 	Set<Pen> pens =new HashSet<>();  // here we created a Pen set of HashSet(it stored only unique value),<Pen>	in between Pen class is used.
		 	pens.add(pen1);						// still both are diffent 
		 	pens.add(pen2);
//		 	System.out.println(pens);
	}

}

	class Pen {
	int price;
	 String color;
	Pen(int price, String color){
		this.price=price;
		this.color=color;
	}
	
	// SO CHANGING THIS DEFAULT BEHAVIOUR EQUALS AND HASHCODE COMES IN THE PICTURES, BASICALLY WE WANT TWO PENS ARE DIFFERENT WHEN BOTH 
	// PENS ARE DIFFERNT AT  PRICE AND COLOR.
	//firstly we generate  a hashcode of an object. using the hash code we get information on which location  the object will come and then on this location
	// we compare  using  equals  with another object.
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + price;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pen other = (Pen) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	
	
}
	
	
	
			

/*   hashCode() and equals() method:- these method have been defined in object class  which is parent  class for java  object. for this  reason . 
 *   all java  objects inherit  a default implementation  of these method.
 *   
 *   NOTE:- IF YOU ARE OVERRIDING THE EQUALS THEN HASHCODE ALSO BE OVERRIDE. SO BASICALLY BOTH ARE USED ALTOGETHER.
 *   
 *   Object class  defined hashCode() method like this:
 *   public  int hashCode(){
 *   //todo  return the hashcode
 * }
 * 
 * Object  class  defined  equals() method  like this :
 * public  boolean  equals(Object obj){
 * return this == obj;
 * }
 * 
 * 
 * THE CONTRACT:-
 * 
 * THE CONTRACT BETWEEN HASHCODE AND  EQUALS IS :-
 * 1. IF TWO OBJECTS ARE EQUALS  , THEN  THEY MUST HAVE  THE SAME HASHCODE.
 * 2. IF TWO OBJECT HAVE SAME HASHCODE , THEY MAY OR MAYNOT BE  EQUALS. 
 * 
 * BEST PRACTICES:-
 * 
 * 1. ALWAYS USE SAME  ATTRIBUTES OF AN OBJECT  TO GENERATE  hashcode() and equal() BOTH.
 * 2. equals() must be  consistant (if the object are not modified , then  it must keep returning  the same  value)
 * 3.  whenever  a.equals(b) , then a  hashcode() must  be same  as b.hashcode().
 * 4. if you  override  one , then you  should  override other.
 */