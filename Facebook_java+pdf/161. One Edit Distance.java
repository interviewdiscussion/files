## Idea
* Corner case : s = t, s.length() == t.length() == 0, length differn is bigger than 1 -> return false
* Case 1 : abc & adc replace 1 character
* Case 2 : a b c d  & a c d , delete 1 character delete 1 from s 
* Case 3 : a b c & a e b c delete 1 from t
* Case 4 : a b c d & a b c 
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) { 
        	if (s.charAt(i) != t.charAt(i)) {
        		if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
        			return s.substring(i + 1).equals(t.substring(i + 1));
    			else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
    				return s.substring(i).equals(t.substring(i + 1));	        	
    			else // s is longer than t, so the only possibility is deleting one char from s
    				return t.substring(i).equals(s.substring(i + 1));
        	}
        }       
        //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
        return Math.abs(s.length() - t.length()) == 1;        
    }
}
只用char：
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        char[] cs=s.toCharArray();
        char[] ct=t.toCharArray();
        for(int i=0;i<Math.min(s.length(),t.length());i++){
            if(s.charAt(i)!=t.charAt(i)){
                if(s.length()==t.length()){
                    return helper(cs,ct,i+1,i+1);
                    //return s.substring(i+1).equals(t.substring(i+1));
                }else if(s.length()<t.length()){
                    return helper(cs,ct,i,i+1);
                    //return s.substring(i).equals(t.substring(i+1));
                }else{
                    return helper(cs,ct,i+1,i);
                    //return t.substring(i).equals(s.substring(i+1));
                }
            }
        }
        return Math.abs(s.length()-t.length())==1;
    }
    public boolean helper(char[] cs,char[] ct,int i,int j){
        int cslen=cs.length;
        int ctlen=ct.length;
        if((cslen-i)!=(ctlen-j)) return false;
        while(i<cslen&&j<ctlen){
            if(cs[i]!=ct[j]) return false;
            else{
                i++;
                j++;
            }
        }
        return true;
    }
}