1 2 3 4 1 1 1 5 2 7 8
j i             i
//recursive: O(n!) time, O(n) stack space
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper(res, list, nums, used);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {//if nums[i] is used, or nums[i] is a dup of last num and last num is not used
            if (used[i] || (i != 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                continue;//why use boolean used instead of using list.contains()?cuz we have dups and some dup cases are valid
            }
            used[i] = true;
            list.add(nums[i]);
            helper(res, list, nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}

public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        if(nums.length==0) return res;
        helper(nums,0,res);
        return res;
    }
    public void helper(int[] nums,int start,List<List<Integer>> res){
        if(start==nums.length){
            List<Integer> solu=new ArrayList<>();
            for(int num:nums) solu.add(num);
            res.add(solu);
            return;
        }
        for(int i=start;i<nums.length;i++){
            if(isUsed(nums,start,i)) continue;
            swap(nums,start,i);
            helper(nums,start+1,res);
            swap(nums,start,i);
        }
    }
    public void swap(int[] nums,int i,int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
    public boolean isUsed(int[] nums,int i,int j) {
        for(int x = i; x < j; x++)
            if(nums[x] == nums[j]) return true;
        return false;
    }
}
这是用hashset做的，不用boolean
// Time : o(n!)
// space :o(n^2) 
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res= new ArrayList<List<Integer>>();
        if(nums == null|| nums.length==0) return res;
        helper(res,nums,0);
        return res;
    }
    private void helper(List<List<Integer>> res,int[] nums, int pos){
        if(pos==nums.length){
            List<Integer> list = new ArrayList<Integer>();
            for(int num :nums){
                list.add(num);
            }
            res.add(list);
            return;
        }
        Set<Integer> used = new HashSet<Integer>();
        for(int i = pos ; i < nums.length ; i++){
            if(used.add(nums[i])){
                swap(nums,i,pos);
                helper(res,nums,pos+1);
                swap(nums,i,pos);
            }
        }
    }
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
//iterative: using the idea of creating next permutation: O(n!*n) time, O(1) space
public class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);//remember to sort !!!
        while (true) {//loop is O(n!) time, and create each perm needs O(n) time, so O(n!*n) time in total
            int firstSmaller = -1;//scan from back to front, find the index of first num which is smaller than previous num
            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    firstSmaller = i;
                    break;
                }
            }
            if (firstSmaller == -1) {//if not found(nums is in descending order), reverse the whole array and return
                reverse(nums, 0, nums.length - 1);
                res.add(createPerm(nums));
                return res;
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
            res.add(createPerm(nums));
        }
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
    
    private ArrayList<Integer> createPerm(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        return list;
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