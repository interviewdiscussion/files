// dfs with memorization: O(n^2) time and O(n^2) space; cuz we won't do repetitive calculations
// normal dfs will takes O(2^n) time, which gets TLE
public class Solution {
    private HashMap<String, List<String>> map = new HashMap<>();//memorize strings' ways of wordbreak
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return res;//if curr string or dict is empty
        }
        if (map.containsKey(s)) {//if curr string's ways of wordbreak has been calculated before, we can directly use it
            return map.get(s);
        }
        if (dict.contains(s)) {//to deal with " " for the last word(reach end), which can be directly added without adding " "
            res.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            String word = s.substring(0, i);
            if (!dict.contains(word)) {//if s.substring(0, i) is not a valid word
                continue;
            }
            List<String> temp = wordBreak(s.substring(i), dict);
            if (temp.isEmpty()) {//if s.substring(i) cannot be broken into any valid words
                continue;
            }
            for (String str : temp) {//if both parts of curr string can be broken into valid words, add them into res in order
                res.add(word + " " + str);//each word separated by " "
            }
        }
        map.put(s, res);//add curr string's ways of wordbreak into the map
        return res;//return all ways of breaking curr string
    }
}

//find all possible ways of breaking
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return res;
        }
        helper(res, s, dict, "", 0);
        return res;
    }
    
    private void helper(List<String> res, String s, Set<String> dict, String path, int index) {
        if (index == s.length()) {
            res.add(path.substring(0, path.length() - 1));
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (dict.contains(word)) {
                helper(res, s, dict, path + word + " ", i);
            }
        }
    }
}