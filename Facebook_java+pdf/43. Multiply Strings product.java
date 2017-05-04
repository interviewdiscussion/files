// O(mn) time, O(m+n) space
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "0";
        int m = num1.length(), n = num2.length();
        int[] digits = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {//calculate product from lower digits
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;//calculate the indices where the digits will be
                int sum = product + digits[p2];//calculate the sum
                digits[p1] += sum / 10;//add carries to digits[p1]
                digits[p2] = sum % 10;//notice this is =, not +=, cuz it has been added to sum
            }
        }
        StringBuilder res = new StringBuilder();
        for (int digit : digits) {
            if (!(res.length() == 0 && digit == 0)) res.append(digit);//skip the zero at front
        }
        return res.length() == 0 ? "0" : res.toString();//corner case like "0","0" -> [0, 0]
    }
}
Remember how we do multiplication?Start from right to left, perform multiplication on every pair of digits, and add them 
together. Let's draw the process! From the following draft, we can immediately conclude:
`num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]`

index 1     1 2 3
index 0       4 5
        ---------
              1 5
            1 0
          0 5
            1 2
index[1,2]0 8    index[i+j,i+j+1]=[1+0,1+0+1]=[1,2]
        0 4
        ---------
  index[0 1 2 3 4]
         p1 p2
<<<-------------- calculate from right to left

//https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation