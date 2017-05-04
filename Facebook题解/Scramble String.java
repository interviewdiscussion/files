// recursive: exponential complexity (O(2^n) time), O(n) stack space, O(256n) memory space?
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int[] map = new int[256];//if chars are all lower case letters, we can use 26 here, and [s1.charAt(i) - 'a'] below
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            map[s1.charAt(i)]++;
            map[s2.charAt(i)]--;
        }
        for (int i : map) {
            if (i != 0) {//if the occurrences of chars are different, return false
                return false;
            }
        }
        for (int i = 1; i < n; i++) {//should start from i = 1 !!! we only partition strings into two non-empty substrings !!!
            //s1.substring(i, i+k)&s2.substring(j, j+k) are scramble && s1.substring(i+k)&s2.substring(j+k) are scramble
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;//eg.s1=rgtae,s2=great, rg&gr isScramble, tae&eat isScramble
            }
            
            //s1.substring(i+k)&s2.substring(j,j+len-k) are scramble && s1.substring(i,i+k)&s2.substring(j+len-k) are scramble
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) && isScramble(s1.substring(i), s2.substring(0, n - i))) {
                return true;//eg.s1=rgtae,s2=eatgr, rg&gr isScramble, tae&eat isScramble
            }
        }
        return false;
    }
}

// dp: O(n^4) time, O(n^3) memory space
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int n = s1.length();//dp[i][j][len]:str starts from s1's i,& str starts from s2's j,both with len length,are scramble?
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {//initialize the dp[i][j][1],if two char are same, they are scramble
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);//string with len 1 means the char,if 2 chars are same,return true
            }
        }
        for (int len = 2; len <= n; len++) {//start from 2 cuz we have already initialize dp[i][j][len=1]
            for (int i = 0; i <= n - len; i++) {//we can start from any index i as long as a len length string can be included
                for (int j = 0; j <= n - len; j++) {
                    for (int k = 1; k < len; k++) {//k:the length of one of the partitioned string,the other's length is len-k
                        if ((dp[i][j][k] && dp[i + k][j + k][len - k]) || (dp[i + k][j][len - k] && dp[i][j + len - k][k])) {
                            dp[i][j][len] = true;
                        }
                    }
                }
            }
        }
        return dp[0][0][n];
    }
}
//dp[i][j][k] && dp[i + k][j + k][len - k]:
//s1.substring(i, i+k)&s2.substring(j, j+k) are scramble && s1.substring(i+k)&s2.substring(j+k) are scramble
//dp[i + k][j][len - k] && dp[i][j + len - k][k]:
//s1.substring(i+k)&s2.substring(j, j+len-k) are scramble && s1.substring(i, i+k)&s2.substring(j+len-k) are scramble
//if either one of these two is true, s1.substring(i, len) and s2.substring(j, len) are scramble