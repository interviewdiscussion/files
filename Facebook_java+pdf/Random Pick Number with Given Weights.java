//random return number according to weight: return a random number from (0,...,n-1) with given weights
public class Solution {
    private static int randomNumber(int[] weights) {
        if (weights == null || weights.length == 0) {
            return 0;
        }
        int n = weights.length;
        for (int i = 1; i < n; i++) {
            weights[i] += weights[i - 1];//[1,2,4,5,1,3] -> [1,3,7,12,13,16]
        }
        Random rand = new Random();
        int num = rand.nextInt(weights[n - 1]);//num is from 0 to 15
        return binarySearch(weights, 0, n - 1, num);//returned value is from 0 to n-1
    }

    private static int binarySearch(int[] weights, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (target < weights[mid]) {//find the leftmost mid that target < weights[mid]; eg.target=10,weight[mid]=12
                end = mid;
            } else {
                start = mid;
            }
        }
        if (target < weights[start]) {//leftmost !!
            return start;
        }
        return end;
    }
}