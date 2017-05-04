怎么优化输出？ 小哥解释后才明白：譬如说一个用户调用这个函数，想查找一组数是否是存在答案中。HashSet
1,two pointer O(n^2) time, O(1) space if not consider sorting's stack usage
    public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i==0||(i>0&&nums[i]!=nums[i-1])){
                int lo=i+1,hi=nums.length-1,sum=0-nums[i];
                while(lo<hi){
                    if(nums[lo]+nums[hi]==sum){
                        //返回true
                        res.add(Arrays.asList(nums[i],nums[lo],nums[hi]));
                        while(lo<hi&&nums[lo]==nums[lo+1]) lo++;
                        while(lo<hi&&nums[hi]==nums[hi-1]) hi--;
                        lo++;hi--;
                    }else if(nums[lo]+nums[hi]<sum) lo++;
                    else hi--;
                }
            }
        }
        //返回false
        return res;
    }
}
2，hashmap做
    * Use hashmap to store value and frequency
    public List<List<Integer>> threeSum(int[] n){
        Arrays.sort(n);
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0 ; i < n.length; i++){
            if( !map.containsKey(n[i])){
                map.put(n[i],1);
            } else {
                map.put(n[i], map.get(n[i]) + 1 );
            }
        }
        for( int i = 0 ; i < n.length ; i ++){
            for( int j = i + 1 ; j < n.length ; j++){
                int rest = 0 - n[i] - n[j];
                int count = 0;
                if(n[i] == rest) count++;
                if(n[j] == rest) count++;
                if(map.containsKey(rest) && map.get(rest) > count && rest >= n[j]){
                    res.add(Arrays.asList(n[i],n[j],rest));
                }
                while( j < n.length - 1 && n[j] == n[j+1]) j++;    
            }
            while( i < n.length -1 && n[i] == n[i+1] ) i++;
        }
        return res;
    }
3,数组里的值能重复使用怎么办？用了类似combination sum的方法递归求解。也可以用下面的方法解
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<>();
        if(candidates.length==0) return res;
        helper(candidates,target,res,new ArrayList<>(),0,3);
        return res;
    }
    public void helper(int[] candidates, int target,List<List<Integer>> res,List<Integer> list,int start,int level){
        if(level<0) return;
        if(target==0&&level==0){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i=start;i<candidates.length;i++){
            list.add(candidates[i]);
            helper(candidates,target-candidates[i],res,list,i,level-1);
            list.remove(list.size()-1);
        }
    }
}
// if each num can be used for any times(results should still be unique):
// sort and two pointers: O(n^2) time, O(1) space if not consider sorting's stack usage
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {//i < nums.length if each num can be used for any times
            if (i != 0 && nums[i] == nums[i - 1]) {//skip duplicated 3sum results
                continue;
            }
            int left = i;//start from i if each num can be used for any times
            int right = nums.length - 1;
            while (left <= right) {//left <= right if each num can be used for any times
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {//remember to skip dups after adding result
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {//remember to skip dups after adding result
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}

-------------------------------------------------------------------------------------------------------------
不知道是啥
// hashtables without sort (dups may exist): O(n^2) time, O(n) space
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();//use this to store keys of combinations of 3nums that have been added to res
        for (int i : nums) {//counting sort, store the nums and their occurrences
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }
        for (int i : nums) {//first num
            map.put(i, map.get(i) - 1);//used num should occurrence - 1, and so that we can avoid dups created
            if (map.get(i) == 0) {
                map.remove(i);
            }
            for (int j : map.keySet()) {//second num
                int k = 0 - i - j;//third num
                if (!map.containsKey(k) || (k == j && map.get(k) == 1)) {//if k not found, or k==j and it only occurs once
                    continue;
                }
                
                //if we only need to determine whether 3sum exist, we can just return true here and skip the code below !!
                
                String key = getKey(i, j, k);//use this key to determine whether it's a duplicated result
                if (!set.contains(key)) {//if this combination of three nums hasn't been added to res
                    res.add(new ArrayList<>(Arrays.asList(i, j, k)));
                    set.add(key);//remember to add it into set
                }
            }
        }
        return res;
    }
    
    private String getKey(int i, int j, int k) {//why only care min&max?cuz when target is fixed,target-min-max is fixed too!
        int min = Math.min(Math.min(i, j), k);//min, min !!!
        int max = Math.max(Math.max(i, j), k);//max, max !!!
        return String.valueOf(min) + "@" + String.valueOf(max);
    }
}

// hashtables without sort (if no dups in nums and each num can only be used once): O(n^2) time, O(n) space
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        HashSet<Integer> vals = new HashMap<>();
        for (int i : nums) {
            if (!vals.contains(i)) {
                vals.add(i);
            }
        }
        for (int i : vals) {//first num
            for (int j : vals) {//second num
                int k = 0 - i - j;//third num
                // if (i == j || i == k || j == k) {//if two of three are the same, skip
                //     continue;
                // }
                if (!vals.contains(k)) {//if k not found
                    continue;
                }
                res.add(new ArrayList<>(Arrays.asList(i, j, k)));
            }
        }
        return res;
    }
}

// if each num can be used for any times(results should still be unique):
// hashtables without sort: O(n^2) time, O(n) space
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        HashSet<Integer> vals = new HashSet<>();
        for (int i : nums) {
            if (!vals.contains(i)) {
                vals.add(i);
            }
        }
        for (int i : vals) {//first num
            for (int j : vals) {//second num
                int k = 0 - i - j;//third num
                if (!vals.contains(k)) {//if k not found
                    continue;
                }
                res.add(new ArrayList<>(Arrays.asList(i, j, k)));
            }
        }
        return res;
    }
}
