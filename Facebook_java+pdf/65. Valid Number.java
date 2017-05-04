public class Solution {
    public boolean isNumber(String s) {
        //e代表10的多少次方
        int start=0,end=s.length()-1;
        while(start<end&&s.charAt(start)==' ') start++;
        while(start<end&&s.charAt(end)==' ') end--;
        if(end-start==0&&(s.charAt(start)>'9'||s.charAt(start)<'0')) return false;//只有一个元素
        if((s.charAt(start)=='+'||s.charAt(start)=='-')){
            if(end-start==1&&s.charAt(end)=='.') return false;
            start++;
        } 
        int p=-1,e=-1;
        for(int i=start;i<=end;i++){
            char c=s.charAt(i);
                if(c=='e'){
                    if(e==-1) e=i;//看e是否出现过，出现过把index给e
                    else return false;
                }
                if(c=='.'){
                    if(p==-1) p=i;
                    else return false;
                }
                if(start==e||end==e) return false;
                if(p>e&&e!=-1) return false;
                if(start==p&&start+1==e) return false;
                if((c>'9'||c<'0')&&c!='.'&&c!='e'){
                    if((c=='+'||c=='-')&&i-1==e&&i!=end){}
                    else return false;
            }
        }
        return true;
    }
}