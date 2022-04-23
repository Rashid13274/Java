package interview_Problem;

public class PrimeFactors {

	

	// Function to print all the numbers
	static void printNumbers(int a[], int n, int x)
	{

		boolean flag = false;

		// Iterate for every element in the array
		for (int i = 0; i < n; i++)
		{

			int num = a[i];

			// Find the gcd
			int g = __gcd(num, x);

			// Iterate till gcd is 1
			// of number and x
			while (g != 1)
			{

				// Divide the number by gcd
				num /= g;

				// Find the new gcdg
				g = __gcd(num, x);
			}

			// If the number is 1 at the end
			// then print the number
			if (num == 1)
			{
				flag = true;
				System.out.print(a[i] + " ");
			}
		}

		// If no numbers have been there
		if (!flag)
			System.out.println("No number have been there");
	}

	static int __gcd(int a, int b)
	{
		if (b == 0)
			return a;
		return __gcd(b, a % b);
		
	}
	
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Drivers code
		int x = 60;
		int a[] = { 2, 5, 10, 7, 17 };
		int n = a.length;
		
		printNumbers(a, n, x);

	}

}
