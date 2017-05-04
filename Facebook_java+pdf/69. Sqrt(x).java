public class Solution {
    public int mySqrt(int x) {
        if (x < 0) {
            return -1;
        }
        long start = 1;
        long end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid == x) {
                return (int)mid;
            } else if (mid * mid > x) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (end * end <= x) {
            return (int)end;
        }
        return (int)start;
    }
}

double 类型：如果精确到。000, *100000  最后除100
记得转换成long型，越界