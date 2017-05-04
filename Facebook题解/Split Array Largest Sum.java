// binary search: O(nlogm) time,n is num of numbers in array,m is the diff between Math.max(max, end/m) and total sum of nums
public class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long end = 0;//total sum in array(highest bound)
        int max = 0;//max num in array
        for (int i : nums) {
            end += i;
            max = Math.max(max, i);
        }
        long start = Math.max(max, end / m);//we at least this amount of bound to hold the total sum(lowest bound)
        //we start to use binary search to find the minimum largest sum of m subarrays
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (canSplit(nums, m, mid)) {//if we can split the array into <= m arrays wtih curr bound, try a lowerer bound
                end = mid;
            } else {
                start = mid;//if we need more subarrays than m arrays with curr bound, we need to increase the bound
            }
        }
        if (canSplit(nums, m, start)) {
            return (int)start;
        }
        return (int)end;
    }
    
    private boolean canSplit(int[] nums, int m, long bound) {
        int countArrays = 1;//this should be 1, cuz we at least need 1 array to hold all nums
        long currSum = 0;
        for (int i : nums) {
            currSum += i;
            if (currSum > bound) {//this means we need a new subarray to hold curr num so that each subarray's sum <= bound
                currSum = i;//new subarray sum
                countArrays++;//need one more subarray to hold nums
                if (countArrays > m) {//if need more subarrays to hold all nums so that all subarrays have sum that's <= bound
                    return false;//we will return false and find a higher bound
                }
            }
        }
        return true;//if num of subarrays needed is <= m, it means that curr bound is high enough, we can try a lower bound
    }
}
//我们首先来分析，如果m和数组nums的个数相等，那么每个数组都是一个子数组，所以返回nums中最大的数字即可，如果m为1，那么整个nums
//数组就是一个子数组，返回nums所有数字之和，所以对于其他有效的m值，返回的值必定在上面两个值之间，所以我们可用二分搜索法来做。
//用一个例子来分析，nums = [1, 2, 3, 4, 5], m = 3，我们将left设为数组中的最大值5，right设为数字之和15，然后我们算出中间数为10
//接下来是找出和最大且小于等于10的子数组的个数，[1, 2, 3, 4], [5]，可看到我们无法分为3组，说明mid偏大，所以我们让right=mid，
//然后我们再次进行二分查找，算出mid=7，再次找出和最大且小于等于7的子数组的个数，[1,2,3], [4], [5]，我们成功的找出了三组，
//说明mid还可以进一步降低，我们让right=mid，然后我们再次进行二分查找哦啊，算出mid=6，再次找出和最大且小于等于6的子数组的个数，
//[1,2,3], [4], [5]，我们成功的找出了三组，我们尝试着继续降低mid，我们让right=mid，然后我们再次进行二分查找哦啊，算出mid=5，
//再次找出和最大且小于等于5的子数组的个数，[1,2], [3], [4], [5]，发现有4组，此时mid太小了，应该增大mid，我们让left=mid+1，
//此时left=6，right=5，循环退出了，我们返回left即可