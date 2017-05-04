//O(n) time, O(unique nums) space
public class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashSet<Integer> set = new HashSet<>();
        int max = 0;
        for (int i : nums) {
            set.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(down)) {
                set.remove(down);//remember to remove
                down--;
            }
            int up = nums[i] + 1;
            while (set.contains(up)) {
                set.remove(up);//remember to remove
                up++;
            }
            max = Math.max(max, up - down - 1);//should be -1
        }
        return max;
    }
}