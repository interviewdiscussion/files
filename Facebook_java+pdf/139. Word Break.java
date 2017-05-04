## Idea 
* Dp , Dp[] is an array the contains boolean 
* dp[i] is true if ther a word in the dictionary that end at ith of s adnd is also true at the beginning of the word


```
s = "facebook"
words = ["face" , "book"]
dp
0 1 2 3 4 5 6 7 8 
t       t       t   
  f a c e 
         b o o k 
1,两种解法，写法不同
//O(lengthOfString * maxLength) time and O(lengthOfString) space
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int maxLength = getMaxLength(dict);
        boolean[] dp = new boolean[s.length() + 1];//dp[i]:whether 0 to i part of string can be segmented into words in dict
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {//O(n) * O(maxLength)
            for (int lastWordLength = 1; lastWordLength <= maxLength && lastWordLength <= i; lastWordLength++) {//<= i !!!
                if (dp[i - lastWordLength] && dict.contains(s.substring(i - lastWordLength, i))) {//need add s.substring !!!
                    dp[i] = true;//if string from 0 to i-lastWordLength is segmentable, and i-lastWordLength to i is in dict
                    break;//eg. 0 L E E T C O D E -> L(F),i++ -> E(F),LE(F),i++ -> E(F),EE(F),LEE(F),i++ -> .....,LEET(T)
                }         //    T F F F T F F F T
            }
        }
        return dp[s.length()];
    }
    
    private int getMaxLength(Set<String> dict) {
        int max = 0;
        for (String s : dict) {
            max = Math.max(max, s.length());
        }
        return max;
    }
}
public class Solution {
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] res=new boolean[s.length()+1];
        res[0]=true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(res[j]&&wordDict.contains(s.substring(j,i))){
                    res[i]=true;
                    break;
                }
            }
        }
        return res[s.length()];
    }
}

// find one possible way of breaking, backtracking:
//O(C^K)=O((lengthOfString/dictWordLength)^lengthOfString)=O(dictWordLength^lengthOfString) time,O(lengthOfString) stack space
public class Solution {
    public String wordBreak(String s, Set<String> dict) {
        String res = "";
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return res;
        }
        helper(res, s, dict, "", 0);
        return res;
    }
    
    private boolean helper(List<String> res, String s, Set<String> dict, String path, int index) {
        if (index == s.length()) {
            res.add(path.substring(1));//one solution found, add to res and return true
            return true;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (dict.contains(word) && helper(res, s, dict, path + " " + word, i)) {//if one solution found, return true
                return true;
            }
        }
    }
}

//return a possible way of breaking, dp:
//O(lengthOfString * maxLength) time and O(lengthOfString) space
public class Solution {
    public String wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null || dict.size() == 0) {
            return true;
        }
        int maxLength = getMaxLength(dict);
        boolean[] dp = new boolean[s.length() + 1];//dp[i]:whether 0 to i part of string can be segmented into words in dict
        int[] words = new int[s.length() + 1];//words[i]:index of the end of a dict's word s.substr(i,words[i])
        dp[0] = true;
        words[0] = -1;
        for (int i = 1; i <= s.length(); i++) {//O(n) * O(maxLength)
            for (int lastWordLength = 1; lastWordLength <= maxLength && lastWordLength <= i; lastWordLength++) {//<= i !!!
                if (dp[i - lastWordLength] && dict.contains(s.substring(i - lastWordLength, i))) {//need add s.substring !!!
                    dp[i] = true;//if string from 0 to i-lastWordLength is segmentable, and i-lastWordLength to i is in dict
                    words[i - lastWordLength] = i;//record the index of breaking position
                    break;//eg. 0 L E E T C O D E -> L(F),i++ -> E(F),LE(F),i++ -> E(F),EE(F),LEE(F),i++ -> .....,LEET(T)
                }         //    T F F F T F F F T
            }
        }
        if (!dp[s.length()]) {//remember to check dp[s.length()] first !!!
            return "";
        }
        StringBuilder sb = new StringBuilder(s.substring(0, words[0]));
        int start = words[0];
        while (start != s.length()) {
            sb.append(" " + s.substring(start, words[start]));
            start = words[start];//remember to update start !!!
        }
        return sb.toString();
    }
    
    private int getMaxLength(Set<String> dict) {
        int max = 0;
        for (String s : dict) {
            max = Math.max(max, s.length());
        }
        return max;
    }
}
//http://www.1point3acres.com/bbs/thread-207049-1-1.html
dic可能很大怎么办，我说建立字典树