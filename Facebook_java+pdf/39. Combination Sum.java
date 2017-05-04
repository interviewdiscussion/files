## Idea 
* Backtracking 
* pass res, temp list , nums , remaining value and position to a helper function 
* Must avoid dulicpate compuatation 
* For example set [2, 3, 6, 7] and target 7
* Must avoid 7 - 2 -2 -2 -2 - 2 - 2 solution
* So, we mush set remain >= 0
* When remain = 0, add temp list  to res
* For backtracking, we add nums[i] to list, backtracking to current position( Only calculate nums after and in the position)
* Then track back : remove current nums[i] 

## Time 
* cn0 + cn1 + cn2 + cn3 + ... + cnn = 2^n
    
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        if(candidates.length==0) return res;
        helper(candidates,target,res,new ArrayList<>(),0);
        return res;
    }
    public void helper(int[] candidates, int target,List<List<Integer>> res,List<Integer> list,int start){
        if(target<0) return;
        if(target==0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=start;i<candidates.length;i++){
            list.add(candidates[i]);
            helper(candidates,target-candidates[i],res,list,i);
            list.remove(list.size()-1);
        }
    }
}