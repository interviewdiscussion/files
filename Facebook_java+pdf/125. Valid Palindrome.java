public class Solution {
    public boolean isPalindrome(String s) {
        if(s.length()==0) return true;
        int start=0,end=s.length()-1;
        while(start<end){
            while(start<end&&!Character.isLetterOrDigit(s.charAt(start))) start++;
            while(start<end&&!Character.isLetterOrDigit(s.charAt(end))) end--;
            if(Character.toLowerCase(s.charAt(start))!=Character.toLowerCase(s.charAt(end))) return false;
            start++;
            end--;
        }
        return true;
    }
}
//如果数字归为其他字符，Character.isLetter, 大小写敏感，去掉toLowerCase
但数字被归为其他字符，忽略其他字符，大小写不敏感。双指针判断。问了一下时间复杂度。然后问了下test case，常规的“”， general过的和不过的给两个，再给特殊字符大写和数字
## Code : If the input is char[]
```
class myCode {
    public static void main (String[] args) throws java.lang.Exception {
        char[] test = {'a','!','a'};
        myCode sol = new myCode();
        boolean res = sol.isValid(test);
        System.out.print(res);
    }
    public boolean isValid(char[] c){
        if( c.length == 0 ) return true;
        int start = 0;
        int end = c.length - 1;
        char head, tail;
        while( start <= end ){
            head = c[start];
            tail = c[end];
            if( !Character.isLetterOrDigit(head) ) start++;
            else if ( !Character.isLetterOrDigit(tail)) end--;
            else{
                if(Character.toLowerCase(head) != Character.toLowerCase(tail)) return false;
                start++;
                end--;
            }
            
        }
        return true;
    }
}

```