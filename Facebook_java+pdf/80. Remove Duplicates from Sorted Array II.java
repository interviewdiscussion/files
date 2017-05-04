public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=2) return nums.length;
        int count=2;
        for(int i=2;i<nums.length;i++){
            if(nums[i]!=nums[count-2]){
                nums[count++]=nums[i];
            }
        }
        return count;
    }
}