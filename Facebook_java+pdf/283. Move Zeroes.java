//output is length of non-zero part;if we only care whether all non-zero are in length,don't care what nums are after length
//num of operations: num of zero
public class Solution {
    public int moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right&&nums[left] != 0) {
                left++;
            }
            while (left < right&&nums[right] == 0) {
                right--;
            }
            if (left < right) {
                nums[left++] = nums[right--];//we only write non-zero to left part
                //if we only care zero, only use nums[right--] = nums[left++] above, and return right
                /*
                nums[left++]=nums[right];
                nums[right--]=0;//前面是数，后面是0的情况这么写，但不是排序的了
                */
            }
        }
        return left;
    }
}
//solution 1: write (optimal, cuz operations reduced)
//num of operations: nums.length, if there are lots of non-zeros in array, use this
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int insert = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insert++] = nums[i];
            }
        }
        while (insert < nums.length) {
            nums[insert++] = 0;
        }
    }
}

//solution 2: swap
//num of operations: 2 * (num of non-zero), if there are lots of zeros in array, use this
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[j];
                nums[j++] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
// moveZero to front, moveOne to back, maintain the order of other non-zero and non-one elements
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int insert = 0;
        for (int i = 0; i < nums.length; i++) {//move all non-one to front
            if (nums[i] != 1) {
                nums[insert++] = nums[i];
            }
        }
        int temp = insert - 1;//save the position before all one
        while (insert < nums.length) {//fill one to back
            nums[insert++] = 1;
        }
        
        insert = temp;//skip all one at the back
        for (int i = insert; i >= 0; i--) {//move all non-zero to center (start from index before all one)
            if (nums[i] != 0) {
                nums[insert--] = nums[i];
            }
        }
        while (insert >= 0) {//fill zero to front
            nums[insert--] = 0;
        }
    }
}