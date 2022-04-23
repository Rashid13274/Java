package ArrayList_Practice;

import java.util.ArrayList;

public class ArrayList3 {

	public static void main(String[] args) {
		ArrayList<String> list1=new ArrayList<>();
		
		list1.add("jon");
		list1.add("doe");
		list1.add("mark");
		System.out.println("print all list1"+list1);
		System.out.println();
		
		ArrayList<String> list2=new ArrayList<>();
		
		list2.add("milan");
		list2.add("mack");
		 list1.addAll(list2);
		 System.out.println("print all list2"+list1);
		 
		 ArrayList<String> list3=new ArrayList<>();
		 
		 list3.add("henry");
		 list3.add("jarvis");
		 System.out.println("invoking the list3");
		 list1.addAll(1, list3);
		 System.out.println(list1);
		
		

	}

}
