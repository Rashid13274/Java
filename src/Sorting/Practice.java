package Sorting;

//import java.util.HashMap;

//import java.util.Arrays;

public class Practice {

	public static void main(String[] args) {
		//COLLECTION INTERFACE : 1. LIST ,SET , QUEUE
		// LIST  ARRAY LIST , LINKED LIST  , STATC -> VECTOR 
		// SET HASHSET , LINKET SET , TREE SET , SORT SET.
		// PQ BQ  PBQ LBQ  
		// CURSORS: ENUMERATION , ITERATORS , LISTITERATORS
		// ArrayList<Object> arr1=new ArrayList<>();
		 // method or function:
		 // 1 add():
		 //2. addALL();
		 //3. addAll(): at specific position
		 //4. remove : 
		 //5. removeALL();
		 // 6. removeIf():
		 // 7. contains():
		 // 8. containsAll();
		 // 9. size();
		 //10: clone: returns a shallow copy of arraylist instance:
		 //11. get: 
		 //12. indexOf();
		 //13. isEmpty();
		 //14. retainsall():
		 //15. repalceAll():
		 //16. trimToSize(): to minimize the arrayList capacity.
		 
//		 arr1.add("md rashid");
//		 arr1.add(1);
//		 arr1.add("AGE: 23");
//		 ArrayList<Object> arr2=new ArrayList<>();
//		 arr2.add("rashid");
//		 arr2.add(2);
//		 arr2.add("AGE: 33");
//		 arr2.add("md rahaman");
//		 arr1.addAll(0, arr2);
		 
//		ListIterator<Object> l1=arr1.listIterator();
//		while(l1.hasNext()) {
//			Object o= l1.next();
//			System.out.println(o);
//			
//		}
		 
//		 Iterator<Object> obj=arr1.iterator();
//		 while(obj.hasNext()) {
//			 Object newObj=obj.next();
//			 System.out.println(newObj);
//		 }
//		 Vector<Object> v1=new Vector<>();
//		 v1.add("RAMAN");
//		 v1.add("suman");
//		 v1.add("RAMAN");
//		 v1.add("RAMAN");
//		 v1.add("RAMAN");
//		 v1.add(1);
//		 v1.add(2);
//		 v1.add(3);
//		 v1.add(4);
//		 Enumeration<Object> enum1=v1.elements();
//		 while(enum1.hasMoreElements()) {
//			 Object newEnum=enum1.nextElement();
//			 System.out.println(newEnum);
//		 }
		 
		 
//		 // SET:-
//		 HashSet<Object> h1=new HashSet<>();
//		 h1.add("md");
//		 h1.add("rashid");
//		 h1.add("mohammad rashid");
//		 h1.add(1);
//		 h1.add(2);
//		 h1.add(3);
//		 
//		 HashSet<Object> h2=new HashSet<>();
//		 h1.add(5);
//		 h1.add(6);
//		 h1.add(7);
//		 h1.add(8);
//		 h1.addAll(h2);
//		System.out.println(h1);
//		 
	
			
//	String inputString="apple";
//	characterCount(inputString);
//		
//		
//	}
//public static void frequency(String str) {
//	
//Map<Character,Integer> map=new HashMap<>();
//char[] charact=str.toCharArray();
//for(Character c:charact) {
//	if(map.containsKey(c)) {
//		map.put(c, map.get(c)+1);
//	}
//	map.put(c, 1);
//}
//// traverse  useing the set 
//for(Map.Entry<Character, Integer> entry:map.entrySet()) {
//System.out.println(entry.getKey()+" "+" "+entry.getValue());
//	
//	
//	
//	
//	
//
//static void characterCount(String inputString)
//{
//    // Creating a HashMap containing char
//    // as a key and occurrences as  a value
// HashMap<Character, Integer>();


// Converting given string to char array
//
//char[] strArray = inputString.toCharArray();
//
//// checking each char of strArray
//for (char c : strArray) {
//    if (charCountMap.containsKey(c)) {
//
//        // If char is present in charCountMap,
//        // incrementing it's count by 1
//        charCountMap.put(c, charCountMap.get(c) + 1);
//    }
//    else {
//
//        // If char is not present in charCountMap,
//        // putting this char to charCountMap with 1 as it's value
//        charCountMap.put(c, 1);
//    }
//}
//
//// Printing the charCountMap
//for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
//    System.out.println(entry.getKey() + " " + entry.getValue());
//}
	
//		int a[]= {2,3,4,1,1,4,57,8};
//		int [] newArr= arr(a);
//		System.out.println(Arrays.toString(newArr));
//}
//	// how to remove duplicate from array
//	public static int[] arr(int []a) {
//		Arrays.sort(a);
//		 int j=0;
//		 for(int i=0;i<a.length-2;i++) {
//			 if(a[i]!=a[i+1]) {
//				 a[j++]=a[i];
//			 }
//		 }
//		 a[j++]=a[a.length-1];
//		return a;
//		
//		int arr[]={7,8,9,1,2,3,4,5};
//		int start=0;
//		int end=arr.length-1;
//		int target =4;
//		binarySearch(arr,start,end ,target);
//		}
//	
//	
//
//public static int  binarySearch(int[]arr, int start, int end, int target) {
//	int mid=Math.round((start+end)/2);
//	while(start<=end) {
//		if(target==arr[mid]) {
//			return mid;
//		}
//		if(target>arr[mid]) {
//			start=mid+1;
//		} else if(target<arr[mid]) {
//				end=mid-1;;
//		}
//	
//} 
//	return -1;

	

	
	}

}
