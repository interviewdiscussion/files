- Sliding windows ( two pointers :  left , right )
- Create a frequency array new int[256], freq[a] == 0 means  we haven't use "a" before
- Move right pointer
- Use a variable count to track the number of distinct characters,  if count > k, move left pointer
- Keep update max result 
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);
                num--;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
返回的是string：
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int num = 0, i = 0, res = 0;
        String ss="";
        for (int j = 0; j < s.length(); j++) {
            if (count[s.charAt(j)]++ == 0) num++;
            if (num > k) {
                while (--count[s.charAt(i++)] > 0);
                num--;
            }
            if(j-i+1>res){
                ss=s.substring(i,j+1);
                res=j-i+1;
            }
        }
        return ss;
    }
}