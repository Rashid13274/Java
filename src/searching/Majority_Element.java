package searching;

/*
 * BOYER MOORE VOTING ALGORITHAM(MAJORITY ELEMENT):-
 * INPUT:[3,2,2,1,1,2,2,1];
 * OUTPUT:2
 */
public class Majority_Element {

	public static void main(String[] args) {
		int arr[]= {3,2,2,1,1,2,2,1};
		System.out.println(major(arr));
		}
	
	
		public static int major(int arr[]) { 
			
	int majorityELement=arr.length/2;
	for(int i=0;i<arr.length;i++) {
		int count=0;
		for(int j=0;j<arr.length;j++) {
			if(arr[i]==arr[j]) {
				count++;
			}
		}
		
			
			}
		
	return 10;
	}

}
		
