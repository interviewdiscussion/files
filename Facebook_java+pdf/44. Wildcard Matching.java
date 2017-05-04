public class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0;
        int pp = 0;
        int match = 0;
        int star = -1;
        while (sp < s.length()) {
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {//if two chars matched
                sp++;
                pp++;
            } else if (pp < p.length() && p.charAt(pp) == '*') {//if we meet a '*', we will record it and try to skip it first
                star = pp;
                match = sp;//record the index of last char we have matched
                pp++;
            } else if (star != -1) {//if we have a '*' matched
                pp = star + 1;//for each fail of matching, we will move the pp back to star + 1
                match++;//we have to use '*' to match that char in s
                sp = match;//update sp
            } else {//if curr char in p doesn't match char in s, and we don't have '*' to match, return false
                return false;
            }
        }
        //after all chars in s are matched, we should check whether all chars in p are matched
        while (pp < p.length() && p.charAt(pp) == '*') {//check remaining chars in p, if char is '*', we can skip it
            pp++;
        }
        return pp == p.length();
    }
}
//O(s * p) time