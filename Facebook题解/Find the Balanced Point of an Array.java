//find the balanced point of an array where the point's leftSum == rightSum
public class Solution {
    private static int balancedPoint(int[] a) {
        if (a == null || a.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        int leftSum = 0;
        for (int i = 0; i < a.length; i++) {
            sum -= a[i];//now sum becomes the rightSum
            if (leftSum == sum) {//if leftSum == rightSum, return this index
                return i;
            }
            leftSum += a[i];//update rightSum
        }
        return -1;
    }
}