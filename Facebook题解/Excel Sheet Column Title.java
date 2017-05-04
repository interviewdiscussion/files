public class Solution {
    public String convertToTitle(int n) {
        if (n < 1) {
            return "";
        }
        String res = "";
        while (n != 0) {
            char c = (char)(--n % 26 + 'A');//need ()()
            res = String.valueOf(c) + res;
            n /= 26;
        }
        return res;
    }
}