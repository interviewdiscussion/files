Run Length Encoding
Given an input string, write a function that returns the Run Length Encoded string for the input string.

For example, if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6”.
time: O(n)
public class Solution{
    public String runLength(String s){
        if(s==null||s.length()==0) return null;
        /* If all characters in the source string are different, 
        then size of destination string would be twice of input string.
        For example if the src is "abcd", then dest would be "a1b1c1d1"
        For other inputs, size would be less than twice.  */
        //但是我们用了stringbuilder就不需要管这个了
        StringBuilder sb=new StringBuilder();
        /* traverse the input string one by one */
        for(int i=0;i<s.length();i++){
            /* traverse the input string one by one */
            sb.append(s.charAt(i));
            int count=1;/* Count the number of occurrences of the new character */
            while(i+1<s.length()&&s.charAt(i)==s.charAt(i+1)){
                rlen++;
                i++;
            }
            sb.append(count);
        }
        String res=sb.toString();
        return res;
    }
}