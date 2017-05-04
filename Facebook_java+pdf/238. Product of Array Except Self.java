//O(n) time and O(1) space (if output space is not counted)
public class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] res = new int[nums.length];
        res[0] = 1;//initialization
        for (int i = 1; i < nums.length; i++) {//use res[i] to store the product of nums on the left of nums[i]
            res[i] = res[i - 1] * nums[i - 1];//update left product
        }
        int right = 1;//initialization
        for (int i = nums.length - 1; i >= 0; i--) {//use right to store the product of nums on the right of nums[i]
            res[i] *= right;//the final result will be equal to the product of left * right
            right *= nums[i];//update right
        }
        return res;
    }
}