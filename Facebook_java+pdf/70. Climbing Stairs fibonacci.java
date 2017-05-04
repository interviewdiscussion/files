斐波那契数列维基百科O（1）可做
1.跟fibonacci number差不多，f(n)=f(n-1)+2*f(n-3)
public class Solution {
    public int climbStairs(int n) {
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }
 }
O(n):
private static int fib(int n){
		int n1 = 0;
		int n2 = 1;
		if( n == 0 ) return n1;
		if( n == 1 ) return n2;
		for( int i = 2 ; i <= n ; i++){
			int res = n1 + n2;
			n1 = n2;
			n2 = res;	
		}
		return n2;
	} 
2, 递归：时间复杂度2^n
时间复杂度证明：http://stackoverflow.com/questions/360748/computational-complexity-of-fibonacci-sequence
private static int getFibo(int i) {
    if(n == 0 ) return 0;
    if(n == 1) return 1;
    return getFibo(i - 1) + getFibo(i - 2);
 }
3,优化 用stack
public class Main {
    public static void climbstair(Stack<Integer> stack,int n){
        if(n>=1){
            stack.push(1);
            climbstair(stack,n-1);
            stack.pop();
        }
        if(n>=2){
            stack.push(2);
            climbstair(stack,n-2);
            stack.pop();
        }
        if(n==0){
            for(int i:stack){
                System.out.println("step"+i+"->");
            }
            System.out.println("finish");
        }
    }
    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        climbstair(stack,5);
    }
}
4,backtracking打印
    public static final List<int[]> stairClimbingRoutes(int[] strides, int steps) {

        // create the combination stack.
        // Longest possible combination is 1 step each time.
        int[] combination = new int[steps];
        int comblength = 0;

        List<int[]> results = new ArrayList<>();

        recurseRoute(steps, strides, combination, comblength, results);
        return results;
    }
    private static void recurseRoute(final int remaining, final int[] strides,
            final int[] combination, final int comblength, final List<int[]> results) {
        if (remaining < 0) {
            // this combination takes us too far.
            return;
        }
        if (remaining == 0) {
            // this combination is just right.
            results.add(Arrays.copyOf(combination, comblength));
            return;
        }
        // need to go further.
        for (int s : strides) {
            combination[comblength] = s;
            recurseRoute(remaining - s, strides, combination, comblength + 1, results);
        }

    }
    public static void main(String[] args) {
        for (int[] combination : stairClimbingRoutes(new int[] {1, 2}, 10)) {
            int check = 0;
            for (int s : combination) {
                check += s;
            }
            System.out.println("Actual " + check + " using " + Arrays.toString(combination));
        }

    }
5,magic number like:.
f(n) = f(n-1) + 2*f(n-3). 
follow up
f(n, k) = f(n - 1) + 2 * f(n - k)
    follow up:
    public static void countK(int n, int k) {
        int[] dp = new int[k];
        int j;
        for (int i = 0; i <= n; i++) {
            int tmp = dp[0];
            j = 0;
            for (; j < dp.length - 1; j++) {
                dp[j] = dp[j + 1];
            }
            if (i <= 0) dp[j] = 1;
            else if (i < k) dp[j] = 2 + dp[j - 1];
            else dp[j] = tmp * 2  + dp[j - 1];
        }
        System.out.print(dp[k - 1]);
    }