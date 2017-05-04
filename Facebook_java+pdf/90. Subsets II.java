
## Idea(DFS)
* Sort input first. For exmple input 4 1 4 4, sort can avoid duplicate calculation because we mark duplicate by i & i-1 
* Start with an empty list
* use a varible "index" to maintain a position
* Create a for loop base on length of nums
* If i is not in current position, and n[i] == n[i-1], skip
* Every new recursion, we will stil calculate regardless of duplicate( cur i = index)
* So, we will generate result like [1,2,2] and [2,2 ]

## Visulization 
```
                 [1,2,2]
 
[]: initial  [1]:index=0  -- index = 0, i = 1, [2]  --- index = 0 ; i = 2, skip
					|                    |
					|                    |
					|              index = 2 , i = 2,[2,2]
					|             
					|
				 [1,2]: index=1 ---- index = 1 , i = 2  , n[1] = n[2], skip
				 	|
				 	|
				 [1,2,2]: index=2     
//recursive: O(C^K)=O(existOrNot ^ nums.length)=O(2^n) time, O(n) stack space
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            return res;
        }
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        helper(res, list, nums, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            if (i != index && (nums[i] == nums[i - 1])) {//nums[i] == nums[i - 1], not nums[index] == nums[index - 1] !!!
                continue;//we only need the result of first dup
            }
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

//iterative(dp-like): O((existOrNot ^ nums.length) * nums.length)=O((2^n) * n) time, O(1) space
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        Arrays.sort(nums);//need to sort and skip dups
        res.add(new ArrayList<>());//remember to add a empty list !!
        int begin = 0;
        for (int i = 0; i < n; i++) {//O(n) time
            if (i == 0 || nums[i] != nums[i - 1]) {
                begin = 0;//if curr num isn't a dup,update begin to 0,so we create new subsets based on all previous subsets
            }
            int size = res.size();//update size
            for (int j = begin; j < size; j++) {//O(2^n) time
                ArrayList<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(temp);
            }
            begin = size;//record the index of first subset created by curr num, so that if next num is a dup, we only create
            //new subsets that are based on subsets created by curr num
        }
        return res;
    }
}
//if nums[i]==nums[i - 1],we only create new subsets that are based on subsets created by nums[i - 1], excluding the subsets
//created by nums before nums[i - 1] (cuz we already did it at prev occurrence of this num, else we will create dup results)