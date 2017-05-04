public class Solution {
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int shift = 1;
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            res += shift * (s.charAt(i) + 1 - 'A');
            shift *= 26;
        }
        return res;
    }
}