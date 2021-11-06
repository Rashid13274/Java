package binary_Search_Problem;

public class Peak_Element_In_Unsorted_Array {

	public static void main(String[] args) {
		int arr[]= {5,10, 13 ,14,20,15};
		System.out.println(binary(arr));
	}
	public static  int  binary(int arr[]) {
		int low=0;
		int N=arr.length;
		int high=arr.length-1;
		while(low<=high) {
			int mid=low+(high-low)/2;
			// CONDITION FOR CHECKING  ELEMENT  ONLY IN MIDDLE OF ARRAY 
			if(mid>0 && mid<arr.length-1) {
			 if(arr[mid]>arr[mid+1] && arr[mid]>arr[mid-1]) {
				return mid;
			 	}
			}
			// CONDITION WHERE TO MOVE LEFT OR RIGHT.
			else if(arr[mid-1]>arr[mid]) {
				high=mid-1;
			}else {
				low=mid+1;
			}
			
			// IF MID ELEMENT ARE AT BEGINING OF ARRAY.
			 if(mid==0) {
				if(arr[mid]>arr[mid+1]) {
					return mid;
				}else {
					 return mid+1;
				}
			 }
			 // IF MID ELEMENT ARE AT THE END OF ARRAY.
				else if(arr[N-1]>arr[N-2]) {
					return N-1;
				}else {
					 return N-2;
				}
			}
		return -1;
	}

}
