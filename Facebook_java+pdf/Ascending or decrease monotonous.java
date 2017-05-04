判断一个序列是否是递增递减序列
    
    public static boolean ascendOrDecrease(int[] nums){
	    if(nums==null||nums.length==0||nums.length==1) return true;//nums.length==1 it should be belong to ascend and decrease
	    int left=0;
	    int right=nums.length-1;
	    if(nums[left]<nums[right]){
            //ascend:
            for(int i=1;i<nums.length;i++){
                if(nums[left++]>nums[i]){
                    return false;
                }
            }
	    }else{
            //decrease:
            for(int i=1;i<nums.length;i++){
                if(nums[left++]<nums[i]){
                    return false;
                }
            }
        }
        return true;
	}