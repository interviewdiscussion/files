/*
Given a string with parentheses, return a string with balanced parentheses by removing the fewest characters possible. You cannot add anything to the string.

Examples:

balance("()") -> "()"
balance(")(") -> ""
balance("(((((") -> ""
balance("(()()(") -> "()()"
balance(")(())(") -> "(())". 1point 3acres 璁哄潧
balance(")(())(") != "()()"
*/
// only need one of the result? 
// O(n) time; if consider the space that sb uses, O(n) space
public class Solution {
    public String balancedParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {//skip all invalid right parentheses
            char c = s.charAt(i);
            if (c == '(') {
                left++;
                sb.append(c);
            } else if (c == ')' && left > right) {
                right++;
                sb.append(c);
            } else if (c != ')') {
                sb.append(c);
            }
        }
        
        s = sb.toString();
        sb = new StringBuilder();
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {//skip all invalid left parentheses
            char c = s.charAt(i);
            if (c == ')') {
                right++;
                sb.append(c);
            } else if (c == '(' && right > left) {
                left++;
                sb.append(c);
            } else if (c != '(') {
                sb.append(c);
            }
        }
        return sb.reverse().toString();
    }
}