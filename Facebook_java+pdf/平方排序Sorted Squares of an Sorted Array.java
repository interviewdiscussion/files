最简单方法，360的简单版
public class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] sorted = new int[n];
        int i = 0, j = n - 1;
        int index = n-1;
        while (i <= j) {
            sorted[index--]= nums[i]*nums[i]>=nums[j]*nums[j]? nums[i]*nums[i++]:nums[j]*nums[j--];
        }
        return sorted;
    }
}
麻烦解法：
public class Solution {
   //return a list of sorted squares of a sorted array; two pointers, O(n) time and O(1) space
   private static int[] sortedSquares(int[] a) {
       if (a == null || a.length == 0) {
           return new int[0];
       }
       int[] res = new int[a.length];//we assume the squares will not be larger than 2^31 - 1
       int right = 0;
       while (right < a.length && a[right] < 0) {//right can be oob (== a.length) if all nums are negative
           right++;
       }
       int left = right - 1;//left is the index of first negative number (can be -1 if all nums are non-negative)
       for (int i = 0; i < res.length; i++) {
           int leftSquare = left >= 0 ? a[left] * a[left] : Integer.MAX_VALUE;//if either one is oob, it will becomes
           int rightSquare = right < a.length ? a[right] * a[right] : Integer.MAX_VALUE;//Integer.MAX_VALUE
           if (rightSquare <= leftSquare) {//smaller square is added
               res[i] = rightSquare;
               right++;//moving from center to right
           } else {
               res[i] = leftSquare;
               left--;//moving from center to left
           }
       }
       return res;
   }
}