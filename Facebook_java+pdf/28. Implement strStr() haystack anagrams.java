public class Solution {// O(n^2)
    public int strStr(String haystack, String needle) {
        if(needle.length()==0) return 0;
        for(int i=0;i<=haystack.length()-needle.length();i++){
            if(haystack.substring(i,i+needle.length()).equals(needle)) return i;//substringO(N)
        }
        return -1;
    }
}
// o(n*h)
public int strStr(String haystack, String needle) {
  for (int i = 0; ; i++) {
    for (int j = 0; ; j++) {
      if (j == needle.length()) return i;
      if (i + j == haystack.length()) return -1;
      if (needle.charAt(j) != haystack.charAt(i + j)) break;
    }
  }
}
//find the first index in haystack that starts with an anagram of needle
//assume only lowercase letters in strings
//O(mn) time, O(m) space
public class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= m - n; i++) {//we use m - n to reduce time; should be <=, not < !
            String key = createKey(haystack, i, n);
            if (!map.containsKey(key)) {
                map.put(key, i);
            }
        }
        String target = createKey(needle, 0, n);
        if (map.containsKey(target)) {
            return map.get(target);
        }
        return -1;
    }
    
    private String createKey(String s, int start, int length) {
        int[] count = new int[26];//see this as O(1) space
        for (int i = 0; i < length; i++) {//O(n) time
            count[s.charAt(start + i) - 'a']++;
        }
        String key = "";
        for (int j = 0; j < count.length; j++) {//see this as O(1) time
            if (count[j] != 0) {
                key += String.valueOf(count[j]) + String.valueOf((char)('a' + j));
            }
        }
        return key;
    }
}
//http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=130978&fromuid=109727