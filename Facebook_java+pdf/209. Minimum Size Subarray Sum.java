public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length==0||nums==null) return 0;
        int j=0,sum=0,min=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            while(sum>=s){
                min=Math.min(min,i-j+1);
                sum-=nums[j++];
            }
        }
        return min==Integer.MAX_VALUE? 0:min;
    }
}
O(n)
    public boolean minSubArrayLen(int[] nums, int s)  {
        if( nums == null || nums.length == 0) return false;
        int pre = 0 , cur = 0 , sum = 0;
        while( cur < nums.length ){
            sum += nums[cur++];
            if( sum == s) return true;
            while(sum >= s){
                sum -= nums[pre++];
            }
        }
        return false;
    }v