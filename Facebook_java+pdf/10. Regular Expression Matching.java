遇到了就写第一个dp的方案
// dp, O(mn) time, O(mn) space:
// this can be optimized to O(n) space as well
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;//remember to initialize dp[0][0]: "" matches "" is true !!!
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {//j=1? before checking dp[i][j-2],we check p[j-1]=='*',and no p starts with '*'
                    dp[i][j] = dp[i][j - 2] //dp[i][j - 2]:pattern match 0 time; or(below) match more than 0 time
                    || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
//1.dp[i][j-2]:check if we can skip this 2-char pattern,or pattern is used by last char of s(so in both cases should skip it)
//2.else if pattern shouldn't be skipped(which mean it should match more char),continue to try match 1 char with this pattern
//note that we match s.charAt(i - 1) and p.charAt(j - 2) here, because j-1 is '*' and j-2 is the char we need to match
//&& dp[i-1][j]:also need to make sure that s.substring(i-1) (which is before s.charAt(j-1)) should be matched by the pattern
                } else {
                    dp[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i - 1][j - 1];
//if we have to match char i-1 & j-1,we also make sure that dp[i - 1][j - 1]:s.substring(0,i-1) & p.substring(0,j-1) matches
                }
            }
        }
        return dp[m][n];
    }
}
// dfs, O(2^n) time, O(n) space, n is length of p (each part can be matched or not matched)
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        if (p.length() == 0) {//if no pattern can be used to match s, check whether s is empty too
            return s.length() == 0;
        }
        if (p.length() == 1) {//if p only has one char, check whether s is also one char left, and then try to match them
            return s.length() == 1 && matchFirstChar(s, p);
        }
        if (p.charAt(1) != '*') {//if second char isn't '*',we have to match first char of both s&p,and try to match the rest
            return matchFirstChar(s, p) && isMatch(s.substring(1), p.substring(1));
        }
        return isMatch(s, p.substring(2)) || (matchFirstChar(s, p) && isMatch(s.substring(1), p));
    }
    //isMatch(s,p.substring(2)):check if we can skip this 2-char pattern,or pattern is used by last 1st char of s(so skip it)
    //if pattern shouldn't be skipped(which means it should match more char),continue to try match 1 char with this pattern
    
    private boolean matchFirstChar(String s, String p) {
        return s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
    }
}
public class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.isEmpty() || (p.charAt(0) != '.' && p.charAt(0) != s.charAt(0))) {
                return false;
            } else {
                return isMatch(s.substring(1), p.substring(1));
            }
        }
        //P.length() >=2并且第二个元素是*；
        while (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {  
            if (isMatch(s, p.substring(2))) { 
                return true;                     
            }                                    
            s = s.substring(1);
        }
    
        return isMatch(s, p.substring(2));
    }
}