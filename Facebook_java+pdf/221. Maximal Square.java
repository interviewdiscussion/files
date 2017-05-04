// below is the O(mn) time and space dp solution
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];//dp[i][j] is the length of the side of the max square whose rightmost point is i,j
        for (int i = 0; i < m; i++) {//initialize boundary cuz we have dp[i - 1][j]
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 0; j < n; j++) {//initialize boundary cuz we have dp[i][j - 1]
            if (matrix[0][j] == '1') {
                dp[0][j] = 1;
                max = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;//we choose the min length
                    max = Math.max(max, dp[i][j]);//to form a square;eg.[0, 1] [1, 0] [1, 1]we can only pick min length+1
                }//                                                     [1, 1] [1, 1] [0, 1]
            }
        }
        return max * max;//max*max, not max !!! cuz max is the length of the side of the max square
    }
}
test case:
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

dp:
1 0 1 0 0
1 0 1 1 1
1 1 1 2 2
1 0 0 1 0
// below is the O(mn) time and O(m) space dp solution, only need to store one col
public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[m + 1];//use dp[0] to represent the out of boundary value of every col's top row
        int max = 0;
        int prev = 0;//use this to store dp[i - 1][j - 1]
        for (int j = 0; j < n; j++) {
            for (int i = 1; i <= m; i++) {
                int temp = dp[i];//store the value of last col before updating it, which is dp[i - 1][j - 1]
                if (matrix[i - 1][j] == '1') {//cuz here our i starts from 1, so matrix[i-1][j] is the original matrix[i][j]
                    dp[i] = Math.min(Math.min(dp[i - 1], dp[i]), prev) + 1;
                    max = Math.max(max, dp[i]);
                } else {
                    dp[i] = 0;//remember to add this !! 
                }
                prev = temp;//update prev, which becomes next row's dp[i - 1][j - 1]
            }
        }
        return max * max;//max*max, not max !!! cuz max is the length of the side of the max square
    }
}
//Let me explain the three values in the Math.min(Math.min(dp[i - 1], dp[i][j - 1]), prev) + 1:
//prev means dp[i - 1][j - 1] (cuz we save prev before we update last row's dp[i] value)
//dp[i] means dp[i][j - 1] (cuz it's last col's value before we updating it)
//dp[i - 1] means dp[i - 1][j] (when we use dp[i - 1], it has already been updated, so it's curr col's value of last row)
//btw, we don't need to update prev to 0 for every col's top row cuz dp[i-1=1-1=0] must be 0,
//so dp[1] must be 1 if matrix[i-1][j]=='1',no matter what value prev is
