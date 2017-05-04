public class Solution {
    
    int[] nums;
    Random rmd;
    public Solution(int[] nums) {
        this.nums=nums;
        this.rmd=new Random();
    }
    
    public int pick(int target) {
        int res=-1;
        int count=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=target){
                continue;
            }
            if(rmd.nextInt(++count)==0){//cuz possiblity of getting 0 equals to 1/count,so all indices have equal possibility
                res=i;
            }
        }
        return res;
    }
}
// {1,2,3,3,3} with target 3, you want to select the 3 on index 2,3,4 with a probability of 1/3 each.
// 2:probability of selection is 1* (1/2)(probability of 2nd 3 not getting 0) * (2/3)(probability of 3rd 3 not getting 0) =1/3
// 3:Its probability of selection is (1/2) * (2/3) = 1/3
// 4:Its probability of selection is just 1/3
// So they are each randomly selected.
random pick max number index with equal probability
public class Solution {
    int[] nums;
    Random rmd;
    public Solution(int[] nums) {
        this.nums=nums;
        rmd=new Random();
    }
    
    public int pick(int target) {
        int count=0;
        int res=-1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            if(max==Integer.MIN_VALUE||nums[i]>max){
                res=i;
                count=1;
                max=nums[i];
            }
            if(nums[i]==max){
                if(rmd.nextInt(++count)==0) res=i;
            }
        }
        return res;
    }
}