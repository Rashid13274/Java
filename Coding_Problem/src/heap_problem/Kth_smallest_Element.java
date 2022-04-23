package heap_problem;

/*
    Input: arr[] = {5, 20, 10, 7, 1}, N = 5, K = 2
	Output: 5
	Explanation: In the given array, the 2nd smallest element is 5. Therefore, the required output is 5.
 */

import java.util.Comparator;
import java.util.PriorityQueue;
class CustomComparator implements Comparator<Integer> {
	 
    public int compare(Integer number1, Integer number2) {
        int value =  number1.compareTo(number2);
       
        // elements are sorted in reverse order
        if (value > 0) {
            return -1;
        }
        else if (value < 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

public class Kth_smallest_Element {

	public static void main(String[] args) {
	int []arr= {7,10,4,3,20,15};
	int n=arr.length;
	int k=0;
	System.out.println(kthSmallest(arr,n,k));

	}
	public static Integer  kthSmallest(int []arr, int n, int k) {
		PriorityQueue<Integer> pq=new PriorityQueue<>(new CustomComparator());
		for(int i=0;i<n;i++) {
			pq.add(arr[i]);
			if(pq.size()>k) {
				pq.remove();
			}
		}
		return pq.peek();
	}

}
