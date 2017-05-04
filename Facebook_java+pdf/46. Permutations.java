//recursive: O(n!*n) time, O(n) stack space
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        //we can add a boolean[] visited here, so that reduce some time by not using list.contains()
        if(nums.length==0) return res;
        helper(res,nums,new ArrayList<>());
        return res;
    }
    public void helper(List<List<Integer>> res,int[] nums,List<Integer> list){
        if(list.size()==nums.length) res.add(new ArrayList<>(list));
        for(int i=0;i<nums.length;i++){
            if(list.contains(nums[i])) continue;//O(n)
            list.add(nums[i]);
            helper(res,nums,list);
            list.remove(list.size()-1);
        }
    }
}
//recursive: O(n!) time, O(n) stack space
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        
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
}

//iterative: O(n!*n) time, O(1) space
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(nums[0]));
        res.add(list);
        for (int i = 1; i < nums.length; i++) {//how many nums' permutations have been added
            int currSize = res.size();//curr num of permutations in res
            for (int j = 0; j < currSize; j++) {//add nums[i] based on all curr perm in res
                for (int pos = 0; pos < res.get(j).size(); pos++) {//add nums[i] to different index of every perm
                    List<Integer> temp = new ArrayList<>(res.get(j));
                    temp.add(pos, nums[i]);//pos=0,[1,2]->[3,1,2];pos=1,[1,2]->[1,3,2];
                    res.add(temp);
                }
                res.get(j).add(nums[i]);//add nums[i] at tail pos of prev perm,so that we don't need to create a new list
            }//(pos=2)[1,2]->[1,2,3];
        }
        return res;
    }
}
// the basic idea is, to permute n numbers, we can add the nth number into the resulting List<List<Integer>> from the n-1 numbers, in every possible position.
// For example, if the input num[] is {1,2,3}: First, add 1 into the initial List<List<Integer>> (let's call it "answer").
// Then, 2 can be added in front or after 1. So we have to copy the List<Integer> in answer (it's just {1}), add 2 in position 0 of {1}, then copy the original {1} again, and add 2 in position 1. Now we have an answer of {{2,1},{1,2}}. There are 2 lists in the current answer.
// Then we have to add 3. first copy {2,1} and {1,2}, add 3 in position 0; then copy {2,1} and {1,2}, and add 3 into position 1, then do the same thing for position 3. Finally we have 2*3=6 lists in answer, which is what we want.


//iterative: using the idea of creating next permutation: O(n!*n) time, O(1) space
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
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
