在HashMap中，它的put操作的时间复杂度是多少？最块肯定是O(1),最慢肯定是O(n)
// randomly return one of the maximal elements' indices
public class Solution {
    int[] nums;
    Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        rand = new Random();//the seed of random can be nothing
    }
    
    public int pick(int target) {
        int max = Integer.MIN_VALUE;
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max == Integer.MIN_VALUE || nums[i] > max) {//this means we need to update max, res, and count
                max = nums[i];
                res = i;
                count = 1;
            } else if (nums[i] == max) {
                if (rand.nextInt(++count) == 0) {//remember to ++count
                    res = i;
                }
            }
        }
        return res;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */