// products of subsets of primes: (no dups in nums)
public class Solution {
    public List<Integer> subsets(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        helper(res, nums, 1, 0);
        //Arrays.sort(res); if res should be sorted, add this
        return res;
    }
    
    private void helper(List<Integer> res, int[] nums, int product, int index) {
        //if we only need to print the results, we don't need res, we can use this:
        // if (product != 1) {
        //     System.out.print(product + " ");
        // }
        if (product != 1) {
            res.add(product);
        }
        for (int i = index; i < nums.length; i++) {
            product *= nums[i];
            helper(res, nums, product, i + 1);
            product /= nums[i];
        }
    }
}

//products of subsets of primes: (no dups in nums)
//iterative: O((existOrNot ^ nums.length) * nums.length)=O((2^n) * n) time, O(1) space
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        //Arrays.sort(nums); no need to sort
        for (int i = 0; i < (1 << n); i++) {//2^n kinds of state 
            int product = 1;
            for (int j = 0; j < n; j++) {//if jth bit of i is 1,nums[j] exists in this combination;Note set doesn't have order
                if ((i & (1 << j)) != 0) {//don't use ==1 cuz this's only jth bit! eg.110 & 010=010=2,doesn't means it's 1 !! 
                    product *= nums[j];//also remember to add () for (i & (1 << j)) !!!!!!
                }
            }
            res.add(product);
        }
        return res;
    }
}

// products of subsets of primes: (have dups in nums)
public class Solution {
    public List<Integer> subsets(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);//to avoid dups, we need this, or you can directly use hashset though
        helper(res, nums, 1, 0);
        //Arrays.sort(res); if res should be sorted, add this
        return res;
    }
    
    private void helper(List<Integer> res, int[] nums, int product, int index) {
        //if we only need to print the results, we don't need res, we can use this:
        // if (product != 1) {
        //     System.out.print(product + " ");
        // }
        if (product != 1) {
            res.add(product);
        }
        for (int i = index; i < nums.length; i++) {
            if (i != index && nums[i] == nums[i - 1]) {
                continue;//to avoid dups, we need this
            }
            product *= nums[i];
            helper(res, nums, product, i + 1);
            product /= nums[i];
        }
    }
}


// products of subsets of nums: (no dups or have dups in nums are the same)
public class Solution {
    public List<Integer> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        HashSet<Integer> res = new HashSet<>();
        helper(res, nums, 1, 0);
        return new ArrayList<>(res);
        //Arrays.sort(res); if res should be sorted, create a new list and add this, then return it
    }
    
    private void helper(HashSet<Integer> res, int[] nums, int product, int index) {
        //if we only need to print the results, we don't need res, we can use this:
        // if (product != 1) {
        //     System.out.print(product + " ");
        // }
        if (product != 1 && !res.contains(product)) {
            res.add(product);
        }
        for (int i = index; i < nums.length; i++) {
            product *= nums[i];
            helper(res, nums, product, i + 1);
            product /= nums[i];
        }
    }
}