public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=1) return nums.length;
        int count=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[count-1]){
                nums[count++]=nums[i];
            }
        }
        return count;
    }
}
//move the pointer and update the value only when the nums are different
//if it's an unsorted array,use hashset(one pass with an index,if !contains,update nums[index++])or sort(then use above algo)