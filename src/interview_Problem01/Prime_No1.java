 package interview_Problem01;

/*JAVA PROGRAM TO PRINT ALL PRIME NO  SMALLER THAN OR EQUAL TO N. USING
 * SIEVE OR ERATOSTHENES.(ALGORITHAM)
 * 
 * */


public class Prime_No1 {
	 void all_PrimeNo(int n) {
		// CREATE A BOOLEAN ARRAY 
		// PRIME[0....N] AND 
		// INITIALIZE  ALL ENTRIES IT AS TRUE. A VALUE IN PRIME[I] WILL FINALLY FALSE IF 
		// I  IS NOT  A PRIME, ELSE TRUE.
		
		Boolean prime[]=new Boolean[n+1];
		for(int i=0;i<=n;i++) {
			prime[i]=true;
		}
			for(int p=2;p*p<=n;p++) {
				// if prime[p] is not changed , then it is  a prime.
				if(prime[p]==true) {
					// update all multiple of  p 
					for(int i=p*p;i<=n;i+=p) {
						prime[i]=false;
					}
				}
			}
			// print all prime numbers
			for(int i=2;i<=n;i++) {
				if(prime[i]==true) {
					System.out.print  (i+" ");
				}
			}
		}
	
	 public static void primeN(int  n) {
		 Boolean arr[]=new Boolean[n+1];
		 for(int i=0;i<n;i++) {
			 arr[i]=true;
		 }
		 // remove all the multiple of even and odd except 2, 3;
		 for(int p=2;p*p<=n;p++) {
			 if(arr[p]==true) {
				 for(int i=p*p; i<=n;i+=p) {
					 arr[i]=false;
				 }
			 }
			 }
			 
		 for(int i=0;i<=n;i++) {
			 if(arr[i]==true) {
				 System.out.print(i+" ");
			 }
		 }
	 }

	public static void main(String[] args) {
		int n=30;
		System.out.println("following are prime numbers");
//		
//		Prime_No1 g=new Prime_No1();
//		g.all_PrimeNo(n);
		primeN(n);
				
	
		
	}
	}
	


