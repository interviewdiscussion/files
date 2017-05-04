public class Solution {
    public int firstMissingPositive(int[] a) {
        if (a == null || a.length == 0) {
            return 1;
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            while (a[i] > 0 && a[i] <= n && a[a[i] - 1] != a[i]) {//we put all 0<a[i]<=n to a[a[i]-1], so that a[i] = i+1
                //while a[i] is a positive 0<a[i]<=n, and a[a[i] - 1] != a[i]
                //we continue to swap a[a[i] - 1] and a[i] until a[a[i] - 1] == a[i]
                int temp = a[a[i] - 1];//must first store a[a[i]-1],not a[i]!else a[i] has been changed when reading a[a[i]-1]
                a[a[i] - 1] = a[i];
                a[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;//should be n + 1, not n
    }
}
//each num is at most visited twice, so O(n) time