backtracking 解法 O(3^n)
public class Solution {
    private String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    public List<String> letterCombinations(String digits) {
        List<String> res=new LinkedList<>();
        if(digits==null||digits.length()==0) return res;
        helper(res,digits,"",0);
        return res;
    }
    public void helper(List<String> res,String digits,String list,int level){
        if(level==digits.length()){
            res.add(list);
            return;
        }
        String letters=mapping[digits.charAt(level)-'0'];
        for(int i=0;i<letters.length();i++){
            helper(res,digits,list+letters.charAt(i),level+1);
        }
    }
}
iteration 解法
public class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.length()==0) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
}
// getNumericValue方法返回指定的Unicode字符表示的int值