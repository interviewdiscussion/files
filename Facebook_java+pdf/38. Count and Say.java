O(n ^ n)
public class Solution {
    public String countAndSay(int n) {
        int i=1;
        String res="1";
        while(i<n){
            int count=0;
            StringBuilder sb=new StringBuilder();
            char c=res.charAt(0);
            for(int j=0;j<=res.length();j++){
                if(j!=res.length()&&res.charAt(j)==c){
                    count++;
                    continue;
                }else{
                    sb.append(count);
                    sb.append(c);
                    if(j!=res.length()){
                        count=1;
                        c=res.charAt(j);
                    }
                }
            }
            res=sb.toString();
            i++;
        }
        return res;
    }
}
count and say输入某个字符串要求输出下一个字符串。
    StringBuilder sb=new StringBuilder();
            char c=res.charAt(0);
            for(int j=0;j<=res.length();j++){
                if(j!=res.length()&&res.charAt(j)==c){
                    count++;
                    continue;
                }else{
                    sb.append(count);
                    sb.append(c);
                    if(j!=res.length()){
                        count=1;
                        c=res.charAt(j);
                    }
                }
            }
            res=sb.toString();