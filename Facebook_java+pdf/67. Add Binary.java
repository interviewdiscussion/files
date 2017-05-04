//string做的时间复杂度是多少。我说n+(n-1)+...+1 = O(n^2)java string是immutable，所以需要更多时间复杂度
O(n)
public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            carry = sum / 2;
            res.append(sum % 2);//if don't use StringBuilder,we can use res=String.valueOf(sum%2)+res,then no need to reverse
        }
        if (carry == 1) {//remember to check whether carry != 0
            res.append(1);
        }
        return res.reverse().toString();//append&reverse,instead of inserting at front cuz sb is array-based,insert will be
    }//very time-consuming
}
//if we need to calculate hexadecimal or k decimal, carry = sum / k; res.append(sum % k), and use hash to map 'ABCDEF' to nums
//if we have zeros at the front of strings, and we only need one MS bit,first clear all zeros at the front, after calculaing,
//add '0' if no overflow, add '1' if overflow (length >= 32 or carry == 1)

// not using +-*/, only use bit manipulations
位运算做的：
public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int num1 = 0;
            int num2 = 0;
            if (i >= 0) {
                num1 = Integer.valueOf(String.valueOf(a.charAt(i--)));
            }
            if (j >= 0) {
                num2 = Integer.valueOf(String.valueOf(b.charAt(j--)));
            }
            int sum = carry ^ num1 ^ num2;//curr digit
            carry = (num1 & num2) | (num1 & carry) | (num2 & carry);
            res.append(sum);//if don't use StringBuilder,we can use res=String.valueOf(sum%2)+res,then no need to reverse
        }
        if (carry == 1) {//remember to check whether carry != 0
            res.append(1);
        }
        return res.reverse().toString();//append&reverse,instead of inserting at front cuz sb is array-based,insert will be
    }//very time-consuming
}
//if you don't want to modify the string in each loop, you may use stringbuilder or char array?