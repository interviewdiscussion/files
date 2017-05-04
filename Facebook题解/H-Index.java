public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int l = citations.length;
        int[] count = new int[l + 1];//count[i] is the num of papers that has i citations (note:count[l] means >= l citations)
        for (int i : citations) {
            if (i > l) {
                count[l]++;
            } else {
                count[i]++;
            }
        }
        int res = 0;//use res to record the num of papers that has at least i citations, if res>=i, it means i is the hIndex
        for (int i = l; i >= 0; i--) {
            res += count[i];
            if (res >= i) return i;//no need to check if other l−i papers have no more than i citations each,cuz they must be
        }//lower than or equal to i
        return 0;
    }
}