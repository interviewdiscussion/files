O(1) space, O(n) running time
public class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length==0) return 0;
        int maxpre=nums[0],minpre=nums[0];
        int max,min;
        int res=nums[0];
        for(int i=1;i<nums.length;i++){
            max=Math.max(Math.max(maxpre*nums[i],minpre*nums[i]),nums[i]);
            min=Math.min(Math.min(maxpre*nums[i],minpre*nums[i]),nums[i]);
            res=Math.max(max,res);
            maxpre=max;
            minpre=min;
        }
        return res; 
    }
}