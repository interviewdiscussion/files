## Idea 
* Use sliding windows (two pointers : start and end)
* Move end pointer to find the first windows based on counter
* When found the windows, record the leftMin and length if it's shorted then current one
* Move start pointer util find an other character in T
* Go back to  move end to find another windows
    
test case:
S = "ADOBECODEBANC"
T = "ABC"
ADOBEC -> DOBECODEBA -> CODEBA -> ODEBANC -> BANC
    
O(n)T的size 并不影响
substring包含这个string的所有出现字母。做起来是一样的
public class Solution {    
    public String minWindow(String s, String t) {
        int[] cnt = new int[256];//char 有256个数
        for (char c : t.toCharArray()) cnt[c]++;
        
        int min = Integer.MAX_VALUE, from = 0, total = t.length();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)]-- > 0) total--;
            while (total == 0) {                    // total=0 means valid window
                if (i - j + 1 < min) {//try to update the min length
                    min = i - j + 1;
                    from = j;
                }
                if (++cnt[s.charAt(j++)] > 0) total++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }
}
//if we are given a set of dict(which means each char in dict appear once)
public class Solution {
    public String minWindow(String s, HashSet<Character> thash) {
        String res = "";
        int[] shash = new int[256];//shash records num of all chars appeared in dict that are in curr window
        int count = 0;//num of |effective| chars in curr window,while it equals t.length(), we will try to shink the window
        int min = s.length() + 1;
        for (int left = 0, right = 0; right < s.length();) {
            char r = s.charAt(right++);
            if (thash.contains(r)) {//if it's in the dict
                shash[r]++;//num of |valid| chars in curr window increases
                if (shash[r] == 1) {//if the num of valid char is <= what target needed
                    count++;
                }
                while (count == thash.size()) {
                    if (right - left < min) {//first try to update the min length and res
                        min = right - left;
                        res = s.substring(left, right);
                    }
                    char l = s.charAt(left++);
                    if (thash.contains(l)) {//if it's in the dict
                        shash[l]--;//num of |valid| chars in curr window decreses
                        if (shash[l] == 0) {//if the num of valid char is < what target needed
                            count--;
                        }
                    }
                }
            }
        }
        return res;
    }
}
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        String res = "";
        int[] thash = new int[256];//thash is a dict of target chars and their occurrences
        int[] shash = new int[256];//shash records num of all chars appeared in dict that are in curr window
        for (int i = 0; i < t.length(); i++) {
            thash[t.charAt(i)]++;//build the dict
        }
        int count = 0;//num of |effective| chars in curr window,while it equals t.length(), we will try to shink the window
        int min = s.length() + 1;
        for (int left = 0, right = 0; right < s.length();) {
            char r = s.charAt(right++);
            if (thash[r] != 0) {//if it's in the dict
                shash[r]++;//num of |valid| chars in curr window increases
                if (shash[r] <= thash[r]) {//if the num of valid char is <= what target needed
                    count++;
                }
                while (count == t.length()) {
                    if (right - left < min) {//first try to update the min length and res
                        min = right - left;
                        res = s.substring(left, right);
                    }
                    char l = s.charAt(left++);
                    if (thash[l] != 0) {//if it's in the dict
                        shash[l]--;//num of |valid| chars in curr window decreses
                        if (shash[l] < thash[l]) {//if the num of valid char is < what target needed
                            count--;
                        }
                    }
                }
            }
        }
        return res;
    }
}