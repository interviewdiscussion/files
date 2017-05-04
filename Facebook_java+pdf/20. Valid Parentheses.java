public class Solution {
    public boolean isValid(String s) {
        if(s.length()==0||s==null) return true;
        Stack<Character> stack=new Stack<>();
        for(Character c:s.toCharArray()){
            if(c=='('){
                stack.push(')');
            }else if(c=='['){
                stack.push(']');
            }else if(c=='{'){
                stack.push('}');
            }else if(stack.isEmpty()||stack.pop()!=c){
                return false;
            }
        }
        return stack.isEmpty();
    }
}