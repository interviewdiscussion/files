//O(n^2) time in the worst case
public class Solution {
    int min=0,max=0;
    public String longestPalindrome(String s) {
        if(s.length()<2) return s;
        for(int i=0;i<s.length();i++){
            helper(s,i,i);
            helper(s,i,i+1);
        }
        return s.substring(min,max+1);
    }
    //extendPalindrome should be O(N),imagine the worst case i = mid,and the input is "aaaaaaa",left pointer keep moving until -1, right pointer keep move until s.length(),one by one,is linear.the complexity is O(N/2)~O(N).
    public void helper(String s,int i,int j){
        while(i>=0&&j<s.length()&&(s.charAt(i)==s.charAt(j))){
            i--;j++;
        }
        if(max-min<j-i-1){
            min=i+1;
            max=j-1;
        }
    }
}
//But I guess when each of the palindrome string centered at each character is short, we will have O(n*len) on average
—————————————————————————————————————————————————————————————————————————————————————————————————————————————
//O(n^2) time    另一种解法
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String res = "";
        int length = s.length();
        int max = 0;
        for (int i = 1; i <= length * 2 - 1; i++) {//i should start from 1 and end at length * 2 - 1
            int count = 1;//the center of the string should be counted as 1
            while (i - count >= 0 && i + count <= length * 2 && get(s, i - count) == get(s, i + count)) {
                count++;//boundary of i - count is 0, boundary of i + count is length * 2, which are all '#'
            }
            count--;//decrease 1 for the outer boundary
            if (count > max) {
                res = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }
        return res;
    }
    
    private char get(String s, int i) {
        if (i % 2 == 0) {
            return '#';
        } else {
            return s.charAt(i / 2);
        }
    }
}


//O(n^2) time, output the number of palindromic substrings, eg."aba" -> "a", "b", "a", "aba", which should return 4    输出有多少个回文
public class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int res = 0;
        for (int i = 1; i <= length * 2 - 1; i++) {//i should start from 1 and end at length * 2 - 1
            int count = 1;//the center of the string should be counted as 1
            while (i - count >= 0 && i + count <= length * 2 && get(s, i - count) == get(s, i + count)) {
                count++;//boundary of i - count is 0, boundary of i + count is length * 2, which are all '#'
            }
            res += count / 2;//calculate how many palindromic substring that is formed by expanding from s.charAt(i)
        }
        return res;
    }
    
    private char get(String s, int i) {
        if (i % 2 == 0) {
            return '#';
        } else {
            return s.charAt(i / 2);
        }
    }
}

//O(n^2) time, output the String of palindromic substrings, eg."aba" -> "a", "b", "a", "aba" 输出所有回文
public class Solution {
    public List<String> longestPalindrome(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        int length = s.length();
        for (int i = 1; i <= length * 2 - 1; i++) {//i should start from 1 and end at length * 2 - 1
            int count = 1;//the center of the string should be counted as 1
            while (i - count >= 0 && i + count <= length * 2 && get(s, i - count) == get(s, i + count)) {
                if (get(s, i - count) == '#') {
                    res.add(s.substring((i - count) / 2, (i + count) / 2));
                }
                count++;//boundary of i - count is 0, boundary of i + count is length * 2, which are all '#'
            }
        }
        return res;
    }
    
    private char get(String s, int i) {
        if (i % 2 == 0) {
            return '#';
        } else {
            return s.charAt(i / 2);
        }
    }
}