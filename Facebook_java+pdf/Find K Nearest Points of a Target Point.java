public class Solution {
   static class Point implements Comparable<Point> {
       int x;
       int y;

       public Point(int x, int y) {
           this.x = x;
           this.y = y;
       }
   }

   //maxHeap solution, O(nlogk) time, O(k) space
   private static Point[] findKClosestPoints(Point[] points, int k, Point target) {
       if (points == null || points.length == 0 || k < 1 || k > points.length) {
           return points;
       }
       Comparator<ArrayList<Integer>> pointComparator = new Comparator<Point>(){
           @Override//compare point a and point b's distance from target
           public int compare(Point a, Point b) {
               int d1 = (a.x - target.x) * (a.x - target.x) + (a.y - target.y) * (a.y - target.y);
               int d2 = (b.x - target.x) * (b.x - target.x) + (b.y - target.y) * (b.y - target.y);
               return d2 - d1;
           }
       });
       Queue<Point> maxHeap = new PriorityQueue<>(k, pointComparator);
       for (Point point : points) {
           if (maxHeap.size() < k) {//put k points to heap
               maxHeap.offer(point);
               continue;
           }
           if (pointComparator.compare(maxHeap.peek(), point) < 0) {
               maxHeap.poll();//if maxHeap's point's distance is far from target than point
               maxHeap.offer(point);
           }
       }
       Point[] res = new Point[k];
       for (int i = k - 1; i >= 0; i--) {//it's index, so it should start from k - 1 !!!
           res[i] = maxHeap.poll();
       }
       return res;
   }

   //QuickSelect solution, average O(n) time(O(n + klogk) time if output is sorted), worst case O(n^2) time, O(1) space
   private static Point[] findKClosestPoints1(Point[] points, int k, Point target) {
       //for the quickSelect solution, if we don't have Double dis, we use getDis() to get the dis of points
       if (points == null || points.length == 0 || k < 1 || k > points.length) {
           return points;
       }
       int index = -1;
       int left = 0;
       int right = points.length - 1;
       while (true) {
           int pos = partition(points, left, right, target);
           if (pos + 1 == k) {
               index = pos;
               break;
           } else if (pos + 1 > k) {
               right = pos - 1;
           } else {
               left = pos + 1;
           }
       }
       Point[] res = new Point[k];
       if (index == -1) {
           return res;
       }
       for (int i = 0; i < k; i++) {
           res[i] = points[i];
       }
       //if the output should be sorted in acsending order, add the code below, which is O(klogk) time
       //Arrays.sort(res);
       return res;
   }

   private static int partition(Point[] points, int left, int right, Point target) {
       Random rand = new Random();
       int index = rand.nextInt(right - left + 1) + left;//careful here, remember to add + left !!!
       Point pivot = points[index];//get the pivot point with given random index
       double pDis = getDistance(pivot, target);//get the dis of pivot
       swap(points, left, index);//put pivot to position of left, index, not pivot !!
       int l = left + 1;//partition starts from left + 1
       int r = right;
       while (l <= r) {
           double lDis = getDistance(points[l], target);//get the dis of l
           double rDis = getDistance(points[r], target);//get the dis of r
           if (lDis > pDis && rDis < pDis) {//swap left's large one with right's small one
               swap(points, l, r);
           }
           if (lDis <= pDis) {
               l++;
           }
           if (rDis >= pDis) {
               r--;
           }
       }
       swap(points, left, r);//put the pivot back to the correct position
       return r;
   }

   private static double getDistance(Point p, Point target) {
       return (double)(p.x - target.x) * (double)(p.x - target.x) + (double)(p.y - target.y) * (double)(p.y - target.y);
   }

   private static void swap(Point[] points, int left, int right) {
       Point temp = points[left];
       points[left] = points[right];
       points[right] = temp;
   }
}