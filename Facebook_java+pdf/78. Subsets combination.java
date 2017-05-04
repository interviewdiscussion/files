//recursive: O(C^K)=O(existOrNot ^ nums.length)=O(2^n) time, O(n) stack space
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        //Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        helper(res, list, nums, 0);
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        res.add(new ArrayList<>(list));//add result here cuz we need [],and we don't need a return statement cuz it's in loop
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}

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
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {//if jth bit of i is 1,nums[j] exists in this combination;Note set doesn't have order
                if ((i & (1 << j)) != 0) {//don't use ==1 cuz this's only jth bit! eg.110 & 010=010=2,doesn't means it's 1 !! 
                    list.add(nums[j]);//also remember to add () for (i & (1 << j)) !!!!!!
                }
            }
            res.add(list);
        }
        return res;
    }
}
        // 1 << n is 2^n, which is the total number of combinations of states
        // each subset equals to an binary integer between 0 .. 2^n - 1
        // 0 -> 000 -> []
        // 1 -> 001 -> [1]
        // 2 -> 010 -> [2]
        // ..
        // 7 -> 111 -> [1,2,3]
## Follow up next() method 

import java.util.*;
class myCode {
    public static void main (String[] args) throws java.lang.Exception {
        int[] tes = {1,2,3};
        myCode sol = new myCode();
        List<List<Integer>> result = new ArrayList<>(sol.subsets(tes));
        for( List<Integer> list : result){
            for( int i = 0 ; i < list.size(); i++){
                System.out.print(list.get(i) + ",");
            }
            System.out.println();
        }
        System.out.println("Using next()");
        List<Integer> test1 = sol.next(result);
        for(int num : test1 ){
            System.out.print(num);
        }
        List<Integer> test2 = sol.next(result);
        for(int num : test2 ){
            System.out.print(num);
        }
        List<Integer> test3 = sol.next(result);
        for(int num : test3 ){
            System.out.print(num);
        }

    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add( new ArrayList<>());
        for( int i : nums ){
            int size = res.size();
            for( int j = 0 ; j < size ; j++){
                List<Integer> newList = new ArrayList<>(res.get(j));
                newList.add(i);
                res.add(newList);
            }
        }
        return res;
    }
    public List<Integer> next(List<List<Integer>> subset){
        List<Integer> res = subset.get(0);
        subset.remove(res);
        return res;
    }
}