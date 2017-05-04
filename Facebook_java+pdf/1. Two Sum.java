//input is unsorted
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map=new HashMap<>();
        int[] res=new int[2];
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                res[0]=map.get(target-nums[i]);
                res[1]=i;
            }else{
                map.put(nums[i],i);
            }
        }
        return res;
    }
}
//two sum with duplicate numberO(n^2)
public ArrayList<int[]> findNumbersThatSumToTarget(int[] arr, int target) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        List<int[]> res = new ArrayList<int[]>();
        for(int i=0; i<arr.length; i++){
                if(!map.containsKey(arr)){
                        Set<Integer> set = new HashSet<Integer>();
                        set.add(i);
                        map.put(arr, set);
                }else{
                        map.get(arr).add(i);
                }

                if(map.containsKey(target-arr)){-google 1point3acres
                        for(Integer j: map.get(target-arr)){
                                if(j!=i){
                                        int[] item = new int[2];
                                        item.add(i);
                                        item.add(j);
                                        res.add(item);
                                }
                        }
                }
        }
//input is sorted
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}