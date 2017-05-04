public class Solution {
    int[] random;
    int[] origin;
    Random rand;//remember to set it to a global variable

    public Solution(int[] nums) {
        random = nums;
        rand = new Random(random.length);//create rand here instead of in shuffle() !!!
        origin = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            origin[i] = nums[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return origin;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < random.length; i++) {
            int j = rand.nextInt(random.length);
            swap(random, i, j);
        }
        return random;
    }
    
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
 
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */