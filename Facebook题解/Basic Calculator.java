public class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;//curr result
        int sign = 1;//sign: the previous sign for curr num. initialize it as 1 is important!
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int sum = c - '0';//sum up the whole num
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {//s.charAt(i + 1), not i + 1 !!!
                    sum = sum * 10 + s.charAt(i + 1) - '0';//see how to get the curr num
                    i++;
                }
                res += sum * sign;//add the sum to res
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);//sign is on the top
                res = 0;//update res to 0, which is the sum in the ( )
                sign = 1;
            } else if (c == ')') {
                res = res * stack.pop() + stack.pop();//first pop is the sign, second is the previous sum before ( )
            }
        }
        return res;
    }
}