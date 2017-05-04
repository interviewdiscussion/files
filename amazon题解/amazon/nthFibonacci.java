public class nthFibonacci{
	public static void main(String[] args){

	}
    

    //recursion
    //time exponential, space O(n)
	public static int fib1(int n){
		if(n <= 1){
			return n;
		}
		return fib(n-1) + fib(n-2);
	}
    

    //dynamic programming
    //time O(n), space O(n)
	public static int fib2(int n){
		int[] f = new int[n+1];
		f[0] = 0;
		f[1] = 1;

		for(int i=2; i<=n; i++){
			f[i] = f[i-1] + f[i-2];
		}

		return f[n];
	}

	//optimize space use
	//time O(n) space O(1)
	public static int fib3(int n){
		int temp1 = 0;
		int temp2 = 1;
		int temp = 0;

		if(n == 0){
			return temp1;
		}

		for(int i=2; i<=n; i++){
			temp = temp1 + temp2;
			temp1 = temp2;
			temp2 = temp;
		}
		return temp2;
	}
}