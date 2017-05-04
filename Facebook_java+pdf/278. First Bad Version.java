/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
* lgN
第一种好
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int start=1,end=n;
        while(start<end){
            int mid=start+(end-start)/2;
            if(isBadVersion(mid)) end=mid;
            else start=mid+1;
        }
        return start;
    }
}
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        //if the boundary n is unknown, we can use 2^n (2^1,2^2...2^n) until we shrink the range into 2^n and 2^(n-1),
        //then we use binary search in the range to find the boundary, after boundary found, the rest is the same as below
        if (n < 1) {
            return -1;
        }
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isBadVersion(start)) {
            return start;
        }
        if (isBadVersion(end)) {
            return end;
        }
        return -1;
    }
}
