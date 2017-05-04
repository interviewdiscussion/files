//min insertions/removal of getting a palindrome with given string
//求出当前string和它reversed string的LCS(最长公共子序列问题)，然后用当前length减去LCS长度得出最少删除/插入字符回文了
public class Solution {
    private static int minRemovalPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        String rev = sb.reverse().toString();
        return n - longestCommonSubsequence(s, rev, n, n);
    }

    private static int longestCommonSubsequence(String a, String b, int a_len, int b_len) {
        int[][] dp = new int[a_len + 1][b_len + 1];
        for (int i = 1; i <= a_len; i++) {
            for (int j = 1; j <= b_len; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[a_len][b_len];
    }
}