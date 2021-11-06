package binary_Search_Problem;
/*( BASED ON CEIL PROBLEMS ):-
 *  GIVEN A LIST OF  SORTED CHARACTERS CONSISTING  OF BOTH  UPPER AND LOWER CASE  ALPHABETS
 *  LIKE ['A', 'r' ,'b' 'C'].
 * 
 * INPUT : Letters =['D', 'J', 'K']
 * Key='B';
 * OUTPUT 'D'.
 * 
 * 
 * THE NEXT  GREATER CHARACTER IS  'B' is 'D'. since IT IS  THE SMALLEST  ELEMENT  FROM THE SET OF GIVEN 
 * LETTERS  , GREATER THEN B.
 * 
 * INPUT:   LETTERS =['h', 'n', 's']
 * key ='t';
 * OUTPUT: 'h';
 * 
 * */





public class Next_Alphabet_Problem {
	public static char  binary(char[] arr, char key ) {
		int N =arr.length;
		// if key is last element and we are searching further then it retrun first element.
	if(key>=arr[N-1]) {
		return arr[0];
	}
	int low=0;
	int high=N-1;
	int result=-1;
	// take the first element as low and rightmost element as high;
	while(low<=high) {
		// if this while condition does'nt satisfy simply return the first element.
		int mid=low+(high-low)/2;
		// if mid is greater than key (i.e mid is greater most  candidate key and rest will be on   left side 
		if(arr[mid]>key) {
			result =mid;
			high=mid-1;
		} else {
			low =mid+1;
			
		}
	}
	// return  the smallest  element 
	return arr[result];
	}

	public static void main(String[] args) {
		char[] arr= {'a', 'b', 'c', 'd','e', 'f','i'};
		char key= 'd';
		char  output =binary(arr, key);
		System.out.println(output);

	}

}
