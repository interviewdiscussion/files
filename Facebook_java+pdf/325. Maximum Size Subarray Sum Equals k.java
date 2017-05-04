//if there are positive and negative nums, we need to use a hashmap, O(n) time and space
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);//corner case, if sum == k (that is, subarray from 0 to i sums up to k)
        int max = 0;//this should be initialized to 0 cuz if the subarray DNE, we should return 0
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {//the keys in the map are the subarray sums before i
            sum += nums[i];
            if (map.containsKey(sum - k)) {// note it's sum - k, not k - sum; cuz sumAfter-sumBefore=k -> sumBefore=sumAfter-k
                max = Math.max(max, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {//put the curr sum after we check the sum - k
                map.put(sum, i);
            }
        }
        return max;
    }
}

//if all positive numbers,find whether a subarray equals k?use two pointers (sliding window) O(n)time O(1)space to solve it
public class Solution {
    public boolean subArraySum(int[] nums, int k) {
        if (nums == null) {
            return false;
        }
        int sum = 0;
        for (int left = 0, right = 0; left < nums.length; left++) {
            while (right < nums.length && sum < k) {
                sum += nums[right++];//remember to right++ !!!
            }
            if (sum == k) {
                return true;
            }
            sum -= nums[left];
        }
        return false;
    }
}
//compress the row or col to let it become a one-dimensional problem, so that we can use the algo above to solve it
//the code below uses O(m^2 * n) time and O(n) space,if m is larger than n,switch them in loops, so the real complexity is
//O(min(m^2*n, n^2*m)) time, O(n or m) space
public class Solution {
    public boolean subMatrixSum(int[][] nums, int k) {
        if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
            return false;
        }
        int m = nums.length;
        int n = nums[0].length;
        for (int i = 0; i < m; i++) {//O(m), i to m are rows that we want to compress
            int[] row = new int[n];//O(n) space, store the compressed rows
            for (int j = i; j < m; j++) {//O(m)
                for (int k = 0; k < n; k++) {//O(n)
                    row[k] += nums[j][k];
                }
                if (subArraySum(row, k)) {//O(n)
                    return true;
                }
            }
        }
        return false;
    }
}
//the process of algo above:
Loop1: Row1: 1, 2, 3 -> Row1+Row2: 5, 7, 9 -> Row1+Row2+Row3: 12, 15, 18
Loop2: Row2: 4, 5, 6 -> Row2+Row3: 11, 13, 15
Loop3: Row3: 7, 8 ,9

1 2 3
4 5 6
7 8 9

3 * 3 matrix, if we compress by rows, then it can be
compress 1 row
Row1: 1, 2, 3
Row2: 4, 5, 6
Row3: 7, 8 ,9

compress 2 rows
Row1+Row2: 5, 7, 9
Row2+Row3: 11, 13, 15

compress 3 rows
Row1+Row2+Row3: 12, 15, 18

For each m * n matrix, if we compress it by rows, it can be compressed by 1 row, 2 rows, 3 rows, ..., m - 1 rows, m rows. That is to say, there are  m + m -1 + m -2 + ... + 2 + 1 = m * (m + 1) / 2 = O(m^2) ways. For each way, we can reuse the linear solution of previous problem. Therefore, the time complexity is O(m ^ 2 * n). Or compressed by columns, it should be O(n ^ 2 * m). It depends which one is smaller...

// if all nums are positive nums, we can use two pointer(silding window, corner case is i==a.length && sum<target ->break)
// if the array has negative nums, we have to use extra space (hashmap)