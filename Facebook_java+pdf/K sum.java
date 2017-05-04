K SUM
    http://www.cnblogs.com/yuzhangcmu/p/4279676.html
* DP
* ksum[i][j][l]表示前j个元素里面取l个元素之和为i。
* 初始化：ksum[0][j][0] =1(j:0~n)，即前j个元素里面取0个元素使其和为0的方法只有1种，那就是什么都不取
* ksum[i][j][l]=ksum[i][j-1][l]+ksum[i-A[i-1]][j-1][l-1]
* i : sum, j is first j element, l elements that is used to calculate sum 
* 即前j个元素里面取l个元素之和为i由两种情况组成：第一种情况为不包含第i个元素，即前j－1个元素里取l个元素使其和为i，第二种情况为包含第i个元素，即前j－1个元素里取l－1个元素使其和为i-A[i-1]（前提是i-A[i-1]>=0）

## Test 
```
1,2,3  k = 2  target = 3

res[0][0][0] = 1

res[0][1][0] = 1

res[0][2][0] = 1

res[0][3][0] = 1
public int  kSum1(int A[], int k, int target) {
 8         // write your code here
 9         if (target < 0) {
10             return 0;
11         }
12         
13         int len = A.length;
14         
15         int[][][] D = new int[len + 1][k + 1][target + 1];
16         
17         for (int i = 0; i <= len; i++) {
18             for (int j = 0; j <= k; j++) {
19                 for (int t = 0; t <= target; t++) {
20                     if (j == 0 && t == 0) {
21                         // select 0 number from i to the target: 0
22                         D[i][j][t] = 1;
23                     } else if (!(i == 0 || j == 0 || t == 0)) {
24                         D[i][j][t] = D[i - 1][j][t];
25                         if (t - A[i - 1] >= 0) {
26                             D[i][j][t] += D[i - 1][j - 1][t - A[i - 1]];
27                         }
28                     }
29                 }
30             }
31         }
32         
33         return D[len][k][target];
34     }
public class Solution {
    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,5};
        Solution sol = new Solution();
        boolean res = sol.kSum(test,5,15000);
        System.out.print(res);
    }

    public boolean kSum(int num[], int k, int target) {
        if (num == null || num.length == 0) {
            return false;
        }
        helper(num,k,target,0,0);

        if(flag) return  true;
        else return false;
    }

    boolean flag = false;
    private void helper(int num[], int k, int target, int pos, int count) {
        if( count > k ) return;
        if( count == k ){
            if( target == 0 ){
                flag = true;
            }
            return;
        }
        for( int i = pos ; i < num.length; i ++){
            helper(num,k,target-num[pos],i,count+1);
        }

    }
}
Code ( Backtracking )


public class Solution {
    public static void main(String[] args) {
        int[] test = new int[]{1,2,3,4,5};
        Solution sol = new Solution();
        boolean res = sol.kSum(test,5,15000);
        System.out.print(res);
    }

    public boolean kSum(int num[], int k, int target) {
        if (num == null || num.length == 0) {
            return false;
        }
        helper(num,k,target,0,0);

        if(flag) return  true;
        else return false;
    }

    boolean flag = false;
    private void helper(int num[], int k, int target, int pos, int count) {
        if( count > k ) return;
        if( count == k ){
            if( target == 0 ){
                flag = true;
            }
            return;
        }
        for( int i = pos ; i < num.length; i ++){
            helper(num,k,target-num[pos],i,count+1);
        }

    }
}