## Idea
* Find two closed elemennt from the end
* If we find the previous is smaller than the second
* Then, we start from the end again, find the first number that is bigger than pevious, and swap the number and previous
* and sort every number after "second", second is included 
1，这个是找下一个O(n)
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int firstSmaller = -1;//scan from back to front, find the index of first num which is smaller than previous num
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmaller = i;
                break;
            }
        }
        if (firstSmaller == -1) {//if not found(nums is in descending order), reverse the whole array and return
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int firstLarger = -1;//scan from back to front, find the index of first num which is larger than firstSmaller num
        for (int i = nums.length - 1; i > firstSmaller; i--) {
            if (nums[i] > nums[firstSmaller]) {
                firstLarger = i;
                break;
            }
        }
        swap(nums, firstSmaller, firstLarger);//swap firstSmaller num with firstLarger num
        reverse(nums, firstSmaller + 1, nums.length - 1);//reverse the subarray after the original index of firstSmaller
    }
    
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left++] = nums[right];
        nums[right--] = temp;
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}
//1.Find the largest index k such that nums[k]<nums[k + 1]. If no such index exists, the permutation is sorted in descending
//order, just reverse it to ascending order and we are done. For example, the next permutation of [3, 2, 1] is [1, 2, 3].
//2.Find the largest index l greater than k such that nums[k] < nums[l].
//3.Swap the value of nums[k] with that of nums[l].
//4.Reverse the sequence from nums[k + 1] up to and including the final element nums[nums.size() - 1].

//那么是如何得到的呢，我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，然后我们再从后往前找第一个
//比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字转置一下即可，步骤如下：

// 1　　2　　7　　4　　3　　1
        ^
// 1　　2　　7　　4　　3　　1
                     ^
// 1　　3　　7　　4　　2　　1
        ^            ^
// 1　　3　　1　　2　　4　　7
            ^   ^    ^   ^
2，这个是找上一个
            previous permuation
public class Solution {
    public void previousPermuation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int firstLarger = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                firstLarger = i;
                break;
            }
        }
        if (firstLarger == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int firstSmaller = nums.length - 1;//cuz it may be the last index!!!
        for (int i = firstLarger + 1; i < nums.length - 1; i++) {
            if (nums[i] < nums[firstLarger] && nums[i+1] >= nums[firstLarger]) {
                firstSmaller = i;//nums.get(i+1) >= nums.get(firstLarger) !!!
                break;//find the last num that's smaller than firstLarger !!!
            }
        }
        swap(nums, firstLarger, firstSmaller);
        reverse(nums, firstLarger + 1, nums.length - 1);//remember to +1 !!!
    }
    
    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left++] = nums[right];
        nums[right--] = temp;
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
}
//1.Find the largest index k such that nums[k] > nums[k + 1]. If no such index exists, the permutation is sorted in ascending
//order, just reverse it to descending order and we are done. For example, the next permutation of [1, 2, 3] is [3, 2, 1].
//2.Find the largest index l smaller than k such that nums[k] > nums[l] and nums[k] <= nums[l+1]
//3.Swap the value of nums[k] with that of nums[l].
//4.Reverse the sequence from nums[k + 1] up to and including the final element nums[nums.size() - 1].

//那么如何得到的呢，我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变小，到了3时才增大的，然后我们再从3往后找最后一个
//比小的数字，是2，那么我们交换3和2，再把此时2后面的所有数字转置一下即可，步骤如下：

// 1　　3　　1　　2　　4　　7
        ^
// 1　　3　　1　　2　　4　　7
                ^
// 1　　2　　1　　3　　4　　7
        ^       ^
// 1　　2　　7　　4　　3　　1
            ^   ^    ^   ^
