1, one pass partition
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = 0;
        int left = 0;
        int right = nums.length - 1;
        while (i <= right) {//should be i <= right, not i < nums.length !!!eg.[2, 2]; not i < right !!!eg.[1,0];
            if (nums[i] == 0) {
                swap(nums, i, left);
                left++;//left side of left pointer are all 0, between left & i are all 1
                i++;//i++ cuz we know what we swap from left pointer is either 0 or 1, i's left side are all 0 and 1
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, right);
                right--;//we can't i++ cuz we don't know what we swapped from right pointer, so we still need to check later
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
2,counting sort最暴力解法
//sort colors naive approach:use counting sort to record occurrences of 0,1,2,then second pass rewrites array with counters
//two/three pass stable counting sort, need O(n) space
public class Solution {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int[] count = new int[3];
        int[] temp = new int[nums.length];
        for (int i : nums) {
            count[i]++;
        }
        for (int i = 1; i < 3; i++) {
            count[i] += count[i - 1];//calculate the starting index of inserting, of each kinds of colors
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            int color = nums[i];
            int pos = --count[color];
            temp[pos] = color;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }
}
3,sort k colors
// naive:counting sort(O(n) time, need O(k) space, but can be stable if use same idea above)
// below:each time sort min&max, then sort middle part's min&max, until we sort all min&max, O(n) time, O(1) space
class Solution {
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length <= 1 || k == 1) {
            return;
        }
        int left = 0;
        int right = colors.length - 1;
        while (left < right) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int i = left; i <= right; i++) {
                max = Math.max(max, colors[i]);
                min = Math.min(min, colors[i]);
            }
            int i = left;
            while (i <= right) {
                if (colors[i] == min) {
                    swap(colors, i, left);
                    left++;
                    i++;
                } else if (colors[i] > min && colors[i] < max) {
                    i++;
                } else {
                    swap(colors, i, right);
                    right--;
                }
            }
        }
    }
    
    private void swap(int[] colors, int a, int b) {
        int temp = colors[a];
        colors[a] = colors[b];
        colors[b] = temp;
    }
}
4，isHigh(), isMid(), isLow()
//http://www.1point3acres.com/bbs/thread-209155-1-1.html
public class Solution {
    如果是给的string，化成char[] 再做
    //given three functions: isHigh(), isMid(), isLow()
    //sort 3 categories in descending order
    public void sortColors(int[] nums, int left, int right) {//we assume input left is 0 and right is nums.length - 1
        if (nums == null || nums.length <= 1) {
            return;
        }
        int i = left;
        while (i <= right) {//should be i <= right, not i < nums.length !!!eg.[2, 2]; not i < right !!!eg.[1,0];
            if (isLow(nums[i])) {
                swap(nums, i, left);
                left++;//left side of left pointer are all 0, between left & i are all 1
                i++;//i++ cuz we know what we swap from left pointer is either 0 or 1, i's left side are all 0 and 1
            } else if (isMid(nums[i])) {
                i++;
            } else {//isHigh(nums[i])
                swap(nums, i, right);
                right--;//we can't i++ cuz we don't know what we swapped from right pointer, so we still need to check later
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
5,getCategory
public class Solution {    
    //int getCategory(int n), outputs the category(1 to k) of given n
    //sort k categories in descending order
    public void sortKColors(int[] nums, int k) {//we assume input left is 0 and right is nums.length - 1
        if (nums == null || nums.length <= 1 || k <= 1) {
            return;
        }
        int left = 0;
        int right = nums.length - 1;
        int min = 1;
        int max = k;
        while (left < right) {
            int i = left;
            while (i <= right) {
                if (getCategory(nums[i]) == min) {
                    swap(nums, i, left);
                    left++;
                    i++;
                } else if (getCategory(nums[i]) > min && getCategory(nums[i]) < max) {
                    i++;
                } else {
                    swap(nums, i, right);
                    right--;
                }
            }
            min++;
            max--;
        }
    }
}
