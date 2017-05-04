//O(logn) time, O(logn) stack space
public class Solution {
    public double myPow(double x, int n) {
        if (n < 0) {
            return 1.0 / pow(x, n);
        } else {
            return pow(x, n);
        }
    }
    
    private double pow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        } else {//this includes cases like n % 2 == 1 && n % 2 == -1 !!! so the order of conditionals cannot be changed
            return y * y * x;
        }
    }
}
eg. 2^2 = 2^1 * 2^1 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) = (1 * 1 * 2) * (1 * 1 * 2) = 4

eg. 2^3 = 2^1 * 2^1 * 2 = (2^0 * 2^0 * 2) * (2^0 * 2^0 * 2) * 2 = (1 * 1 * 2) * (1 * 1 * 2) * 2 = 8

//O(logn) time, O(1) space
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        long absN = Math.abs((long)n);//need to use long cuz Integer.MIN_VALUE
        double res = 1;
        while (absN > 0) {
            if (absN % 2 == 1) {
                res *= x;
            }
            x *= x;//x to the power of 2
            absN /= 2;//divide the power by 2
        }
        if (n > 0) {
            return res;
        }
        return 1.0 / res;
    }
}