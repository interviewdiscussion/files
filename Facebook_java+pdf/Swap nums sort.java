给一个数组，至多允许swap数组里的任意两个数字一次，问是否可以通过swap实现数组排序。
    
第一种肯定对的解法：另复制一个数组然后排序，跟原来的比较，如果有两个以上数字不一样，不能，两个或两个以下，可以
第二种：从前往后扫，找到第一个不一样的，从后往前扫，找到第一个不一样的，交换，再从前往后扫，可以就可以了，不可以就不行了。
    
public class Solution {
    public swapSort {
        int c1=-1;
        int c2=-1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                c1=i-1;
                break;
            }
        }
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]>nums[i+1]){
                c2=i+1;
                break;
            }
        }
        int temp=nums[c1];
        nums[c1]=nums[c2];
        nums[c2]=temp;
        for(int i=0;i<nums.length;i++){
            if(nums[i]<nums[i-1]){
                return false;
            }
        }
        return true;
    }
}