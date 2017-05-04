## Idea 
* When rotating the array, there must be one half of the array that is still in sorted order.
* When doing binary search, we can make a judgement that which part is ordered and whether the target is in that range
* If yes, continue the search in that half, if not continue in the other half.
logn
public class Solution {
    public int search(int[] nums, int target) {
        int start=0,end=nums.length-1;
        while(start<end){
           int mid=(end-start)/2+start;
           if(target==nums[mid]) return mid;
           if(nums[mid]>nums[end]){
               if(target<nums[mid]&&target>=nums[start]) end=mid-1;
               else start=mid+1;
           }else{
               if(target>nums[mid]&&target<=nums[end]) start=mid+1;
               else end=mid-1;
           }
        }
        return nums[start]==target?start:-1;
    }
}