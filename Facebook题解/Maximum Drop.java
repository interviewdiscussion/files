public class Solution {
   //maximum drop(must be dropped from left to right, no dups in array, only 1 peak or 1 valley, V or A shape)
   private static int maxDrop(int[] a) {
       if (a == null || a.length < 2) {
           return 0;
       }
       int n = a.length;
       if (a[0] < a[1]) {
           return findPeak(a, 0, n - 1) - a[n - 1];//peak - right min
       } else if (a[0] > a[1]) {
           return a[0] - findValley(a, 0, n - 1);//left max - valley
       }
       return 0;
   }

   private static int findPeak(int[] a, int start, int end) {
       while (start + 1 < end) {
           int mid = start + (end - start) / 2;
           if (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) {
               start = mid;
           } else if (a[mid - 1] > a[mid] && a[mid] > a[mid + 1]) {
               end = mid;
           } else {
               return a[mid];
           }
       }
       if (a[start] > a[end]) {
           return a[start];
       }
       return a[end];
   }

   private static int findValley(int[] a, int start, int end) {
       while (start + 1 < end) {
           int mid = start + (end - start) / 2;
           if (a[mid - 1] < a[mid] && a[mid] < a[mid + 1]) {
               end = mid;
           } else if (a[mid - 1] > a[mid] && a[mid] > a[mid + 1]) {
               start = mid;
           } else {
               return a[mid];
           }
       }
       if (a[start] < a[end]) {
           return a[start];
       }
       return a[end];
   }
}