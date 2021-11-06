package binary_Search_Problem;

public class Floor_oF_an_Element_in_Sorted_Array {

	public static void main(String[] args) {
		int arr[]= {1,2,3,4,8,10,10,12,14};
		int element=13;
		System.out.println(binarySearch(arr, element));  

	}
	public static  int binarySearch(int arr[], int element) {
		int start=0;
		int end=arr.length-1;
		int result=0;
		while(start<=end) {
			int mid=start+(end-start)/2;
			if(element==arr[mid]) return arr[mid];
			else if(element>=arr[mid]) {
				result=arr[mid];
				start=mid+1;
			}
			else if(element<arr[mid]) {
				end=mid-1;
				
			}
		
		}
		return result;
	}

}
