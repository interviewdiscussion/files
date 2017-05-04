public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int start = 0, end = citations.length - 1, l = citations.length;
        while (start + 1 < end) {//start + 1 < end, not start < end !!!
            int mid = start + (end - start) / 2;
            if (l - mid <= citations[mid]) {//kind of like l - i, which means how many nums are from mid to l
                end = mid;//means l - mid papers have at least l - mid citations;use end = mid to find the largest(leftmost) l
            } else {
                start = mid;
            }
        }
        if (l - start <= citations[start]) {
            return l - start;
        }
        if (l - end <= citations[end]) {
            return l - end;
        }
        return 0;
    }
}
// O(logn) time