package random_Program;

//import java.util.Arrays;

//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Map.Entry;

// FIND NON REPEATING ELEMENT IN THE ARRAY WHERE EVERY ELEMENT IS REPEATED TWICE.
public class XOR_PROBLEM_01 {

	public static void main(String[] args) {
		
	int arr []= {5,4,3,4,1,5,3};
	nonRepeating(arr);
//	uniqueElement(arr);
		
		
	} 
	
	//ONE SOLUTION FOR THIS PROBLEM.
	/*
	public static void  uniqueElement(int arr[]) {
		Map<Integer,Integer> unique=new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			if(unique.containsKey(arr[i])) {
				unique.put(arr[i],unique.getOrDefault(arr[i], 0)+1);
			}else {
				
				unique.put(arr[i], 1);
			}
		}
		for(Entry<Integer, Integer>res:unique.entrySet()) {
			System.out.println(+res.getKey()+" "+" value is"+res.getValue() +"times in array");
		}
		*/
	
	
		// NAIVE OR BRUTE FORCE SOLUTION
		// SORT THE ARRAY AND USE  LOOP
	/*
	
  public static void nonRepeating(int arr[]) {
	  for(int i=0;i<arr.length;i++) {
		for(int j=0;j<arr.length;j++) {
			if(arr[i]>arr[j]) {
				int temp =arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
				
			}
		}
	  } 
	  System.out.println(Arrays.toString(arr));
	  
	  int count =0;
	  for(int i=0;i<arr.length;i++) {
		  for(int k=0;k<arr.length;k++) {
			  if(arr[i]==arr[k]) {
				  count++;
			  }
		  }
		  if(count==1) {
			  System.out.println(arr[i]);
		  }
		  count=0;
	  }
  }
  
  
  */
	
	// XOR METHOD
	public static void nonRepeating(int arr[]) {
		int res=0;
		for(int i=0;i<arr.length;i++) {
			 res=res^arr[i];
		}
		System.out.println(res);
	}
			
}


